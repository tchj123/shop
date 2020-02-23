package shop.bean.cart;

public class CartItemVO {
	private int userId;
	private int cartId;
	private String prodName;
	private String prodState;
	private int price;
	private int prodId;
	private int skuId;
	private String skuValueList;
	private int num;

	public CartItemVO() {};



	public CartItemVO(int userId, int cartId, String prodName, String prodState, int price, int prodId, int skuId, String skuValueList, int num) {
		super();
		this.userId = userId;
		this.cartId = cartId;
		this.prodName = prodName;
		this.prodState = prodState;
		this.price = price;
		this.prodId = prodId;
		this.skuId = skuId;
		this.skuValueList = skuValueList;
		this.num = num;
	}

	public CartItemVO(int userId,  int prodId, int skuId, int num) {
		this.userId=userId;
		this.prodId=prodId;
		this.skuId=skuId;
		this.num=num;
	}


	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public int getSkuId() {
		return skuId;
	}
	public void setSkuId(int skuId) {
		this.skuId = skuId;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getProdState() {
		return prodState;
	}

	public void setProdState(String prodState) {
		this.prodState = prodState;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getSkuValueList() {
		return skuValueList;
	}

	public void setSkuValueList(String skuValueList) {
		this.skuValueList = skuValueList;
	}
}
