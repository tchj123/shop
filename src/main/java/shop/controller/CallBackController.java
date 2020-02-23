package shop.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import shop.bean.alipay.AlipayNotify;
import shop.bean.alipay.AlipayProperties;
import shop.service.OrderService;
import shop.util.ObjectAnalyzer;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;

@Controller
@RequestMapping("/callback")
public class CallBackController {
	@Autowired
	OrderService orderService;
	@Autowired
	AlipayProperties properties;

	@RequestMapping("/returnPage")
	public ModelAndView returnPage(String trade_no,String out_trade_no) {
		//记录支付成功的日志
		out.println("用户支付完成，跳转返回界面");
		out.println("支付订单编号："+trade_no);
		out.println("订单编号："+out_trade_no);

		//返回成功页面
		ModelAndView mav=new ModelAndView();
		mav.setViewName("returnPage");
		mav.addObject("payNo",trade_no);
		mav.addObject("orderNo",out_trade_no);
		return mav;
	}
	
	@RequestMapping(value="/notifyPage",method=RequestMethod.POST)
	@ResponseBody
	public String notifypage(HttpServletRequest request) throws AlipayApiException {
		out.println("支付宝异步通知");

		//获取请求参数map
		Map<String,String[]> parameterMap=request.getParameterMap();
		Map<String,String> paramsMap=new HashMap<>();
		for(Map.Entry<String,String[]> entry:parameterMap.entrySet())
			paramsMap.put(entry.getKey(),entry.getValue()[0]);

		//对请求参数进行校验
		boolean signVerified = AlipaySignature.rsaCheckV1(paramsMap, properties.getALIPAY_PUBLIC_KEY(), properties.getCHARSET(), properties.getSIGN_TYPE()); //调用SDK验证签名
		//校验app_id是否正确
		signVerified=signVerified&&paramsMap.get("app_id").equals(properties.getAPP_ID());
		if(!signVerified)
			return "failure";

		//通知订单中心已支付
		String out_trade_no=paramsMap.get("out_trade_no");
		String app_id=paramsMap.get("app_id");
		Double total_amount=Double.valueOf(paramsMap.get("total_amount"));
		String trade_no=paramsMap.get("trade_no");
		AlipayNotify alipayNotify=new AlipayNotify(out_trade_no,app_id,total_amount,trade_no);
		boolean result=orderService.orderPaied(alipayNotify);

		return result?"success":"failure";
	}
}
