package shop.service;

import shop.bean.alipay.AlipayInfo;

public interface AlipayService {
	public String pay(AlipayInfo alipayInfo);
	public String queryPayByOrderNo(String orderNo);
	public String queryPayByAlipayTradeNo(String alipayNo);	
	public String queryPay(String orderNo,String alipayNo);
	boolean isPaySuccess(String orderNo, String alipayNo);
}
