package shop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;

import shop.bean.alipay.AlipayProperties;

@Configuration
public class AlipayConfig {
	
	@Autowired
	private AlipayProperties prop;

	@Bean
	public AlipayClient alipayClient() {
		AlipayClient alipayClient = new DefaultAlipayClient(prop.getURL(),prop.getAPP_ID(),prop.getAPP_PRIVATE_KEY(),
				prop.getFORMAT(),prop.getCHARSET(),prop.getALIPAY_PUBLIC_KEY(),prop.getSIGN_TYPE());
		return alipayClient;
	}
	
}
