package shop.bean.product;

import shop.bean.Property;

import java.util.List;
import java.util.Map;

public class ProductVO {
	private int prodId;
	private String name;
	//商品状态
	private String state;
	//sku属性map
	private Map<String,List<SkuProperty>> skuPropertyMap;
	private List<Property> propertyList;
	private int minPrice;
	private int maxPrice;
	//商品略缩图
	private String prodImgSrc;
	//商品大图
	private List<String> prodImg;
	//商品介绍图片
	private List<String> introImg;

	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getProdImgSrc() {
		return prodImgSrc;
	}
	public void setProdImgSrc(String prodImgSrc) {
		this.prodImgSrc = prodImgSrc;
	}
	public List<String> getProdImg() {
		return prodImg;
	}
	public void setProdImg(List<String> prodImg) {
		this.prodImg = prodImg;
	}

	public List<Property> getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(List<Property> propertyList) {
		this.propertyList = propertyList;
	}

	public List<String> getIntroImg() {
		return introImg;
	}
	public void setIntroImg(List<String> introImg) {
		this.introImg = introImg;
	}

	public Map<String, List<SkuProperty>> getSkuPropertyMap() {
		return skuPropertyMap;
	}

	public void setSkuPropertyMap(Map<String, List<SkuProperty>> skuPropertyMap) {
		this.skuPropertyMap = skuPropertyMap;
	}

	public int getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}

	public int getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}
}
