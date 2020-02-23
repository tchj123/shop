package shop.bean.order;

import shop.bean.Property;
import shop.bean.product.SkuProperty;

import java.util.List;
import java.util.Map;

public class OrderItemInfo {
	private String name;
	private String prodImg;
	private String skuValueList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public void setProdImg(String prodImg) {
		this.prodImg = prodImg;
	}

	public String getSkuValueList() {
		return skuValueList;
	}

	public void setSkuValueList(String skuValueList) {
		this.skuValueList = skuValueList;
	}
}
