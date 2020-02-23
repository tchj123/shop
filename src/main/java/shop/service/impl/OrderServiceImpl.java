package shop.service.impl;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.bean.alipay.AlipayInfo;
import shop.bean.alipay.AlipayNotify;
import shop.bean.cart.CartItemVO;
import shop.bean.order.*;
import shop.bean.user.Address;
import shop.bean.Page;
import shop.mapper.OrderMapper;
import shop.service.*;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private ProdService prodService;
	@Autowired
	private UserService userService;
	@Autowired
	private AlipayService alipayService;
	@Autowired
	private CartService cartService;
	private SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	private static int onePageNum=10;
	/**
	 * 
	 */
	@Override
	public String createOrder(int userId, List<CartItemVO> cart, int addrId, String payChannel) {


		//校验购物车是否为空
		if(cart==null || cart.isEmpty())
			throw new RuntimeException("商品列表为空");

		//获取商品id集合
		List<Integer> prodIdList=cart.stream().map(CartItemVO::getProdId).distinct().collect(Collectors.toList());
		//校验商品是否存在
		if(!prodService.hasProdId(prodIdList))
			throw new RuntimeException("商品不存在");

		//获取商品信息
		List<OrderItemInfo> oiiList=prodService.getOrderItemInfo(prodIdList);

		//校验商品状态(是否可售)
		//TODO:获取商品信息时就获取状态，不要分开获取
		List<String> stateList=prodService.getProdState(prodIdList);
		for(int i=0;i<stateList.size();i++)
			if(!stateList.get(i).equals("on sale"))
				throw new RuntimeException("商品"+prodIdList.get(i)+"不可售");
		//校验用户地址是否合法
		Address address;
		//如果用户地址id不合法，或者获取地址失败，抛出异常
		if(addrId<0 || (address=userService.getAddress(userId,addrId))==null)
			throw new RuntimeException("收货地址不存在");
		String addr=address.getProvince()+" "+address.getCity()+" "+address.getArea()+" "+address.getStreet();
		String telephone=address.getTelephone();
		String name=address.getName();

		
		//订单和订单详情对象	
		OrderDO order;
		List<OrderItemDO> orderItemList=new LinkedList<>();
		
		//订单编号规则:时间（精确到秒）+8位随机数
		String orderNo=df.format(new Date())+(int)(Math.random()*100000000);
		OrderState state=OrderState.UNPAID;
		int totalNum=0;
		double prodAmount=0;
		double totalAmount=0;

		//扣库存时需要用到的参数
		List<Integer> skuIdList=new LinkedList<>();
		List<Integer> numList=new LinkedList<>();

		for(int i=0;i<cart.size();i++) {
			OrderItemInfo oii=oiiList.get(i);
			//获取数量，价格
			CartItemVO item=cart.get(i);
			int num=item.getNum();
			totalNum+=num;
			int prodPrice=cart.get(i).getPrice();
			prodAmount+=num*prodPrice;
			
			skuIdList.add(item.getSkuId());
			numList.add(num);
			
			//生成订单详情
			OrderItemDO orderItemDO=new OrderItemDO(orderNo,item.getProdId(),oii.getName(),num,item.getSkuId(),oii.getSkuValueList(),prodPrice, payChannel);
			orderItemList.add(orderItemDO);
		}
		//TODO:设计优惠券系统
		totalAmount=prodAmount;
		
		//生成订单对象
		order=new OrderDO(orderNo,userId,addr,telephone,name,state,totalNum,prodAmount,totalAmount, payChannel);

		//尝试下单
		tryCreateOrder(skuIdList,numList,order,orderItemList);

		//订单创建成功，删除购物车中相应的商品
		List<Integer>cartIdList=cart.stream().map(CartItemVO::getCartId).collect(Collectors.toList());
		cartService.deleteProductList(userId, cartIdList);
		return orderNo;
	}
	
	@Transactional(rollbackFor=Exception.class)
	protected void tryCreateOrder(List<Integer> skuIdList, List<Integer> numList, OrderDO order, List<OrderItemDO> itemList) {
		//扣减库存
		prodService.reduceStock(skuIdList,numList);
		
		//插入订单
		if(orderMapper.insertOrder(order)==0)
			throw new RuntimeException("创建订单失败");
		
		//订单插入成功，往订单详情里面插入订单号
		for(OrderItemDO item:itemList)
			item.setOrderId(order.getOrderId());
		
		//插入订单详情
		if(orderMapper.insertOrderItem(itemList)!=skuIdList.size())
			throw new RuntimeException("创建订单详情失败");
				
	}
		
	@Override
	public String createOrder(int userId,int skuId,int num,int addrId,String payChannel) {
		int prodId=prodService.getProdId(skuId);
		//商品不存在
		if(prodId==-1)
			throw new RuntimeException("商品不存在");
		//获取商品名字
		String prodName=prodService.getProdName(prodId);
		//将所需参数封装到CartItem对象中
		CartItemVO cartItem=new CartItemVO(userId,prodId,skuId,num);
		List<CartItemVO> cart=new LinkedList<CartItemVO>();
		cart.add(cartItem);
		return createOrder(userId,cart,addrId,payChannel);
	}
	
	@Override
	public Page<OrderVO> getOrderList(int userId, OrderState orderState, int page) {
		//获取总页数
		int count=orderMapper.getCountOfOrder(userId,orderState);
		int totalPage=count/onePageNum;
		totalPage=totalPage==0?1:totalPage;
		Page<OrderVO> pageBean=new Page<>();
		pageBean.setTotalPage(totalPage);
		pageBean.setCurretnPage(page);
		//校验请求的页数有没有超过总页数
		if(page<=totalPage)
			pageBean.setData(orderMapper.getOrderVOList(userId,orderState,page,onePageNum));
		return pageBean;
	}

	@Override
	public OrderDO getOrderDetail(int userId,String orderNo) {
		return orderMapper.getOrderDO(userId, orderNo).orElse(new OrderDO());
	}

	@Override
	public boolean orderPaied(AlipayNotify alipayNotify) {

		//校验订单状态，去重
		if(!orderMapper.getOrderState(alipayNotify.getOut_trade_no()).equals(OrderState.UNPAID))
			return true;

		//校验支付的数据
		boolean hasOrder=orderMapper.hasOrder(alipayNotify.getOut_trade_no(),alipayNotify.getTotal_amount());
		if(!hasOrder)
			return false;

		//支付成功，更新订单状态
		orderMapper.orderPaied(alipayNotify.getOut_trade_no(),alipayNotify.getTrade_no(),OrderState.UNDELIVERED);
		return true;
	}

	@Override
	public boolean orderDelivered(String orderNo, String deliveryChannel, String deliveryNo) {
		
		return false;
	}

	@Override
	public boolean orderReceived(String orderNo,int userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean orderCommented(String orderNo,int userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cancelOrder(String orderNo,int userId) {
		//检查订单是否处于可以取消的状态
		OrderState state=orderMapper.getOrderState(orderNo);
		if(!state.equals(OrderState.UNPAID)||!state.equals(OrderState.UNDELIVERED))
			throw new RuntimeException("订单不可取消，订单状态为："+state.name());
		
		return orderMapper.cancelOrder(orderNo,userId,OrderState.CANCELD)==1;
	}

	@Override
	public boolean closeOrder(int userId,String orderNo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean orderRefund(String orderNo,int userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteOrder(String orderNo,int userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AlipayInfo getPayInfoForAlipay(int userId,String orderNo) {
		AlipayInfo alipayInfo=new AlipayInfo();
		int orderAmount=orderMapper.getOrderAmount(userId,orderNo).orElse(-1);
		if(orderAmount==-1)
			throw new RuntimeException("获取订单金额失败");
		alipayInfo.setTotal_amount(orderAmount);
		return alipayInfo;
	}


}
