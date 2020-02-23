package shop.mapper;

import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

import shop.bean.cart.CartItemDO;
import shop.bean.order.OrderItemInfo;
import shop.bean.product.ProductVO;

@Mapper
public interface ProdMapper {

	Optional<ProductVO> getProduct(int prodId);
	List<String> getProdStateList(List<Integer> prodIdList);
	int getCountOfProdId(List<Integer> prodIdList);
    Optional<CartItemDO> getProdForCart(int skuId);

    List<OrderItemInfo> getOrderItemInfo(List<Integer> prodIdList);
//	int addProd(Product product);
//	int changeProdState(int prodId,String state);
//	int deleteProdIntroImg(int prodId);
//	Optional<String> getPrdoName(int prodId);
//	Optional<String> getProdState(int prodId);

}
