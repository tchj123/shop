package shop.bean.product;

public class Sku {
	private int skuId;
	private int price;
	private int stock;
	private int skuValue;
	private String imgSrc;

	public int getSkuId() {
		return skuId;
	}

	public void setSkuId(int skuId) {
		this.skuId = skuId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}

	public int getSkuValue() {
		return skuValue;
	}

	public void setSkuValue(int skuValue) {
		this.skuValue = skuValue;
	}
}
