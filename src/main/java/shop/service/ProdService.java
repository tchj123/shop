package shop.service;

import shop.bean.cart.CartItemDO;
import shop.bean.order.OrderItemInfo;
import shop.bean.product.ProductVO;
import shop.bean.product.Sku;

import java.util.List;
import java.util.Map;


public interface ProdService {
	public String getProdName(int prodId);
	public String getProdState(int prodId);
	public Map<String,Sku> getSkuMap(int prodId);
	public List<String> getProdState(List<Integer> prodIdList);
	public int getProdId(int skuId);
	public boolean hasProdId(int prodId);
	public boolean hasProdId(List<Integer> prodIdList);
	public List<Integer> getPrice(List<Integer> skuIdList);
	boolean reduceStock(List<Integer> skuIdList, List<Integer> numList);
	ProductVO getProduct(int prodId);
    Sku getSku(int prodId,List<Integer> skprIdList);
    List<OrderItemInfo> getOrderItemInfo(List<Integer> prodIdList);
}
