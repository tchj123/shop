package shop.bean.alipay;

import java.util.Date;

public class AlipayNotify {
	private String out_trade_no;
	private String app_id;
	private Double total_amount;
	private String trade_no;

	public AlipayNotify() {
	}

	public AlipayNotify(String out_trade_no, String app_id, Double total_amount, String trade_no) {
		this.out_trade_no = out_trade_no;
		this.app_id = app_id;
		this.total_amount = total_amount;
		this.trade_no = trade_no;
	}

	public String getApp_id() {
		return app_id;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	public Double getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(Double total_amount) {
		this.total_amount = total_amount;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}
}
