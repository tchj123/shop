package shop.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import shop.bean.product.Sku;
import shop.bean.product.SkuProperty;

@Mapper
public interface SkuMapper {
	int reduceStock(int skuId,int num);
	Optional<Integer> selectStockForUpdate(int skuId);
	List<Integer> getPriceList(List<Integer> skuIdList);
	Optional<Integer> getProdId(int prodId);
    List<Sku> getSkuList(int prodId);
	List<Integer> getSkprIdList(int sku);
	List<SkuProperty> getSkuPropertyList(int prodId);
}
