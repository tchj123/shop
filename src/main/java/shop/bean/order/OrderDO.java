package shop.bean.order;

public class OrderDO {
	private int orderId;
	//订单编号
	private String orderNo;
	private int userId;
	private String address;
	private String telephone;
	private String name;
	//订单状态
	private OrderState state;
	//商品总数
	private int totalNum;
	//商品总价
	private double prodAmount;
	//订单总价
	private double orderAmount;
	//支付方式
	private String payChannel;
	public OrderDO() {
	}

	public OrderDO(String orderNo, int userId, String address, String telephone, String name, OrderState state,
				   int totalNum, double prodAmount, double orderAmount, String payChannel) {
		super();
		this.orderNo = orderNo;
		this.userId = userId;
		this.address = address;
		this.telephone = telephone;
		this.name = name;
		this.state = state;
		this.totalNum = totalNum;
		this.prodAmount = prodAmount;
		this.orderAmount = orderAmount;
		this.payChannel = payChannel;
	}

	public String getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}

	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public OrderState getState() {
		return state;
	}
	public void setState(OrderState state) {
		this.state = state;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public double getProdAmount() {
		return prodAmount;
	}
	public void setProdAmount(double prodAmount) {
		this.prodAmount = prodAmount;
	}
	public double getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
