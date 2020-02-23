package shop.bean.order;

public class OrderItemVO {
	private String prodName;
	private double prodPrice;
	private int num;
	private String skuValueList;
	private String prodImg;

	public OrderItemVO() {
		super();
	}

	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public double getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(double prodPrice) {
		this.prodPrice = prodPrice;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getSkuValueList() {
		return skuValueList;
	}
	public void setSkuValueList(String skuValueList) {
		this.skuValueList = skuValueList;
	}

	public String getProdImg() {
		return prodImg;
	}

	public void setProdImg(String prodImg) {
		this.prodImg = prodImg;
	}
}
