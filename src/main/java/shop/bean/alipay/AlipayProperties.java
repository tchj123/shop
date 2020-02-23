package shop.bean.alipay;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "alipay")
public class AlipayProperties {

	private String URL;
	private String APP_ID;
	private String APP_PRIVATE_KEY;
	private String FORMAT;
	private String CHARSET;
	private String ALIPAY_PUBLIC_KEY;
	private String SIGN_TYPE;
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getAPP_ID() {
		return APP_ID;
	}
	public void setAPP_ID(String aPP_ID) {
		APP_ID = aPP_ID;
	}
	public String getAPP_PRIVATE_KEY() {
		return APP_PRIVATE_KEY;
	}
	public void setAPP_PRIVATE_KEY(String aPP_PRIVATE_KEY) {
		APP_PRIVATE_KEY = aPP_PRIVATE_KEY;
	}
	public String getFORMAT() {
		return FORMAT;
	}
	public void setFORMAT(String fORMAT) {
		FORMAT = fORMAT;
	}
	public String getCHARSET() {
		return CHARSET;
	}
	public void setCHARSET(String cHARSET) {
		CHARSET = cHARSET;
	}
	public String getALIPAY_PUBLIC_KEY() {
		return ALIPAY_PUBLIC_KEY;
	}
	public void setALIPAY_PUBLIC_KEY(String aLIPAY_PUBLIC_KEY) {
		ALIPAY_PUBLIC_KEY = aLIPAY_PUBLIC_KEY;
	}
	public String getSIGN_TYPE() {
		return SIGN_TYPE;
	}
	public void setSIGN_TYPE(String sIGN_TYPE) {
		SIGN_TYPE = sIGN_TYPE;
	}

}
