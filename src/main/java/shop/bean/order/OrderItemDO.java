package shop.bean.order;


public class OrderItemDO {
	private int orderId;
	private String orderNo;
	private int prodId;
	private String prodName;
	private int prodNum;
	private int skuId;
	private String skuValueList;
	private double prodPrice;
	private String payChannel;
	
	public OrderItemDO() {
	}
	public OrderItemDO(String orderNo, int prodId, String prodName, int prodNum, int skuId, String skuValueList,
					   double prodPrice, String payChannel) {
		this.orderNo = orderNo;
		this.prodId = prodId;
		this.prodName = prodName;
		this.prodNum = prodNum;
		this.skuId = skuId;
		this.skuValueList = skuValueList;
		this.prodPrice = prodPrice;
		this.payChannel = payChannel;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public int getProdNum() {
		return prodNum;
	}
	public void setProdNum(int prodNum) {
		this.prodNum = prodNum;
	}
	public int getSkuId() {
		return skuId;
	}
	public void setSkuId(int skuId) {
		this.skuId = skuId;
	}
	public String getSkuValueList() {
		return skuValueList;
	}
	public void setSkuValueList(String skuValueList) {
		this.skuValueList = skuValueList;
	}
	public double getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(double prodPrice) {
		this.prodPrice = prodPrice;
	}

	public String getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}
}
