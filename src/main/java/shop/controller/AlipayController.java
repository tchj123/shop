package shop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.bean.alipay.AlipayInfo;
import shop.bean.alipay.AlipayProperties;
import shop.bean.user.User;
import shop.service.AlipayService;
import shop.service.OrderService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/pay/alipay")
public class AlipayController {
	@Autowired
	AlipayService alipayService;
	@Autowired
	OrderService orderService;

	@RequestMapping("")
	@ResponseBody
	public String pay(String orderNo, HttpSession session) {
		User user= (User) session.getAttribute("user");
		AlipayInfo alipayInfo =orderService.getPayInfoForAlipay(user.getUserId(),orderNo);
		alipayInfo.setOrderNo(orderNo);
		alipayInfo.setSubject(user.getUsername()+"的订单");
		return alipayService.pay(alipayInfo);
	}



	@RequestMapping("/queryPayByOrderNo")
	@ResponseBody
	public String queryPayByOrderNo(String orderNo){
		return alipayService.queryPayByOrderNo(orderNo);
	}
	
	@RequestMapping("/queryPayByAlipayTradeNo")
	@ResponseBody
	public String queryPayByAlipayTradeNo(String alipayTradeNo) {
		return alipayService.queryPayByAlipayTradeNo(alipayTradeNo);
	}	
	
	@RequestMapping("/queryPay")
	@ResponseBody
	public String queryPay(String outTradeNo,String tradeNo){
		return alipayService.queryPay(outTradeNo, tradeNo);
	}
}
