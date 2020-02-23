package shop.service;

import java.util.List;

import shop.bean.Page;
import shop.bean.alipay.AlipayInfo;
import shop.bean.alipay.AlipayNotify;
import shop.bean.cart.CartItemVO;
import shop.bean.order.OrderDO;
import shop.bean.order.OrderState;
import shop.bean.order.OrderVO;

public interface OrderService {


	String createOrder(int userId, List<CartItemVO> cartItemList, int addrId, String payChannel);
	
	public String createOrder(int userId,int skuId,int num,int addrId,String payChannel);
	/**
	 * 获取指定状态的订单列表
	 */
	Page<OrderVO> getOrderList(int userId, OrderState orderState, int page);

	/**
	 * 获取订单详情
	 */
	OrderDO getOrderDetail(int userId,String orderNo);


	boolean orderPaied(AlipayNotify alipayNotify);

	/**
	 * 订单发货后调用
	 */
	boolean orderDelivered(String orderNo, String deliveryChannel, String deliveryNo);

	/**
	 * 订单签收后调用
	 */
	boolean orderReceived(String orderNo,int userId);

	/**
	 * 订单评论后调用
	 */
	boolean orderCommented(String orderNo,int userId);

	/**
	 * 取消订单，用户调用
	 */
	boolean cancelOrder(String orderNo,int userId);

	/**
	 * 关闭订单，订单发起一段时间后未支付调用
	 */
	boolean closeOrder(int userId,String orderNo);

	/**
	 * 订单退款
	 */
	boolean orderRefund(String orderNo,int userId);

	/**
	 * 删除订单记录
	 */
	boolean deleteOrder(String orderNo,int userId);

	AlipayInfo getPayInfoForAlipay(int userId,String orderNo);
}
