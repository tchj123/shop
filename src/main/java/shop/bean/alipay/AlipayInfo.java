package shop.bean.alipay;

public class AlipayInfo {
	private String orderNo;
	private int total_amount;
	private String subject;
	public AlipayInfo() {}
	public AlipayInfo(String orderNo, int total_amount, String subject) {
		super();
		this.orderNo = orderNo;
		this.total_amount = total_amount;
		this.subject = subject;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public int getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(int total_amount) {
		this.total_amount = total_amount;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}

}
