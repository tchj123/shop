package shop.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import shop.bean.Page;
import shop.bean.cart.CartItemVO;
import shop.bean.user.Address;
import shop.bean.user.User;
import shop.bean.order.OrderDO;
import shop.bean.order.OrderState;
import shop.bean.order.OrderVO;
import shop.service.CartService;
import shop.service.OrderService;
import shop.service.UserService;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private CartService cartService;
	@Autowired
	private UserService userService;

	@RequestMapping("/orderCenter")
	public ModelAndView getOrderPage(HttpSession session){
		ModelAndView mav=new ModelAndView();
		User user= (User) session.getAttribute("user");
		mav.setViewName("orderCenter");
		return mav;
	}
	@GetMapping("/buy")
	public ModelAndView getCreateOrderPage(int skuId,int num){
		ModelAndView mav=new ModelAndView();
		mav.setViewName("createOrder");
		return mav;
	}

	@RequestMapping("/createOrderPage")
	@ResponseBody
	public ModelAndView CreateOrderPage(@RequestBody List<Integer> cartItemIdList,HttpSession session){
		ModelAndView mav=new ModelAndView();
		mav.setViewName("/createOrder");

		User user=(User)session.getAttribute("user");
		List<CartItemVO> cartItemList=cartService.getCartItemList(user.getUserId(),cartItemIdList);
		mav.addObject("cartItemList",cartItemList);
		//获取收货地址
		List<Address> addrList=userService.getAddressList(user.getUserId());
		mav.addObject("addrList",addrList);
		//计算订单总额
		int totalAmount=0;
		for(CartItemVO item:cartItemList)
			totalAmount+=item.getNum()*item.getPrice();
		mav.addObject("totalAmount",totalAmount);

		return mav;
	}

	@RequestMapping("/createOrder")
	@ResponseBody
	public String createOrder(@RequestParam("cartIdList[]")List<Integer> cartIdList, @RequestParam()int addrId,@RequestParam()String payChannel, HttpSession session) {

		User user = (User) session.getAttribute("user");
		String orderNo;
		int userId=(int)((User) session.getAttribute("user")).getUserId();
		List<CartItemVO> cart=cartService.getCartItemList(userId,cartIdList);
		try {
			orderNo = orderService.createOrder(userId,cart, addrId,payChannel);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			return "fail";
		}

		return orderNo;
	}

	@RequestMapping("/getOrderList")
	@ResponseBody
	public Page<OrderVO> getOrderList(OrderState state, int page, HttpSession session) {
		User user = (User) session.getAttribute("user");
		int userId = user.getUserId();
		return orderService.getOrderList(userId, state, page);
	}

	@RequestMapping("/getOrderDetail")
	@ResponseBody
	public OrderDO getOrderDetail(String orderNo, HttpSession session) {
		User user = (User) session.getAttribute("user");
		int userId = user.getUserId();
		return orderService.getOrderDetail(userId, orderNo);
	}

	@RequestMapping("/orderDelivered")
	@ResponseBody
	public boolean OrderDelivered(String orderNo, String deliveryChannel, String deliveryNo) {
		return orderService.orderDelivered(orderNo, deliveryChannel, deliveryNo);
	}

	@RequestMapping("/orderReceived")
	@ResponseBody
	boolean orderReceived(String orderNo, HttpSession session) {
		User user = (User) session.getAttribute("user");
		int userId = user.getUserId();
		return orderService.orderReceived(orderNo,userId);
	};

	@RequestMapping("/orderCommented")
	@ResponseBody
	boolean orderCommented(String orderNo, HttpSession session) {
		User user = (User) session.getAttribute("user");
		int userId = user.getUserId();
		return orderService.orderCommented(orderNo,userId);
	};

	@RequestMapping("/cancelOrder")
	@ResponseBody
	boolean cancelOrder(String OrderNo, HttpSession session) {
		User user = (User) session.getAttribute("user");
		int userId = user.getUserId();
		return orderService.cancelOrder(OrderNo,userId);
	}

	@RequestMapping("/closeOrder")
	@ResponseBody
	boolean closeOrder(int userId,String OrderNo) {
		return orderService.closeOrder(userId,OrderNo);
	};

	@RequestMapping("/deleteOrder")
	@ResponseBody
	boolean deleteOrder(String OrderNo, HttpSession session) {
		User user = (User) session.getAttribute("user");
		int userId = user.getUserId();
		return orderService.deleteOrder(OrderNo,userId);
	}

}
