package shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;

import shop.bean.alipay.AlipayInfo;
import shop.service.AlipayService;
import shop.util.ObjectAnalyzer;

@Service
public class AlipayServiceImpl implements AlipayService {

	@Autowired
	AlipayClient alipayClient;
	
	@Override
	public String pay(AlipayInfo alipayInfo) {
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();// 创建API对应的request
		alipayRequest.setReturnUrl("http://www.tchj123.com/callback/returnPage");
		alipayRequest.setNotifyUrl("http://www.tchj123.com/callback/notifyPage");// 在公共参数中设置回跳和通知地址
		alipayRequest.setBizContent("{" + "    \"out_trade_no\":\""
				+ alipayInfo.getOrderNo() + "\","
				+ "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," 
				+ "    \"total_amount\":"+ alipayInfo.getTotal_amount() + ","
				+ "    \"subject\":\"" + alipayInfo.getSubject()
				+ "\"" + "  }");// 填充业务参数
		String form = "";
		try {
			AlipayTradePagePayResponse response = alipayClient.pageExecute(alipayRequest);
			form = response.getBody(); // 调用SDK生成表单
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return form;
	}
	
	@Override
	public String queryPayByOrderNo(String orderNo) {
		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
		request.setBizContent("{" 
				+ "\"out_trade_no\":\"" + orderNo 
				+ "\"" + "  }");
		try {
			AlipayTradeQueryResponse response = alipayClient.execute(request);
			if (response.isSuccess()) {
				System.out.println("调用成功");
			} else {
				System.out.println("调用失败");
			}
			return response.getBody();
		} catch (AlipayApiException e) {
			throw new RuntimeException("AlipayApiException");
		}

	}

	@Override
	public String queryPayByAlipayTradeNo(String alipayNo) {
		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
		request.setBizContent("{" 
				+"\"trade_no\":\""+alipayNo+"\"" 
				+"  }");
		try {
			AlipayTradeQueryResponse response = alipayClient.execute(request);
			if(response.isSuccess()){
			System.out.println("调用成功");
			} else {
			System.out.println("调用失败");
			}
			return response.getBody();
		}
			catch(AlipayApiException e) {
				throw new RuntimeException("AlipayApiException");
			}
	}
	
	@Override
	public String queryPay(String orderNo, String alipayNo) {
		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
		request.setBizContent("{" + "\"out_trade_no\":\"" + orderNo + "\"," 
				+ "\"trade_no\":\"" + alipayNo + "\","
				+ "\"org_pid\":\"2088101117952222\"," 
				+ "      \"query_options\":[" 
				+ "        \"TRADE_SETTLE_INFO\""
				+ "      ]" + "  }");
		try {
			AlipayTradeQueryResponse response = alipayClient.execute(request);
			if (response.isSuccess()) {
				System.out.println("调用成功");
			} else {
				System.out.println("调用失败");
			}
			return response.getBody();
		} catch (AlipayApiException e) {
			throw new RuntimeException("AlipayApiException");
		}
	}
	
	@Override
	public boolean isPaySuccess(String orderNo, String alipayNo) {
		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
		request.setBizContent("{" + "\"out_trade_no\":\"" + orderNo + "\"," 
				+ "\"trade_no\":\"" + alipayNo + "\","
				+ "\"org_pid\":\"2088101117952222\"," 
				+ "      \"query_options\":[" 
				+ "        \"TRADE_SETTLE_INFO\""
				+ "      ]" + "  }");
		try {
			AlipayTradeQueryResponse response = alipayClient.execute(request);
			if (response.isSuccess()) {
				System.out.println("调用成功");
			} else {
				System.out.println("调用失败");
			}
			return response.getOutTradeNo().equals(orderNo)&& response.getTradeNo().equals(alipayNo);
		} catch (AlipayApiException e) {
			throw new RuntimeException("AlipayApiException");
		}
	}

}
