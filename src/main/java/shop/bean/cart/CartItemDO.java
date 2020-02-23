package shop.bean.cart;

public class CartItemDO {
	private int userId;
	private int cartId;
	private int prodId;
	private int skuId;
	private int num;

	public CartItemDO() {};



	public CartItemDO(int userId,int skuId, int num) {
		super();
		this.userId = userId;
		this.skuId = skuId;
		this.num = num;
	}

	public CartItemDO(int userId, int i, int prodId, int skuId, int num) {
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
}
