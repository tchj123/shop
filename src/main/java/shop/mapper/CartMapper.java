package shop.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import shop.bean.cart.CartItemDO;
import shop.bean.cart.CartItemVO;

@Mapper
public interface CartMapper {
	List<CartItemVO> getAllCartItemList(int userId);
	List<CartItemVO> getCartItemList(int userId, List<Integer> cartItemIdList);
	int addProduct(CartItemDO cart);
	int deleteProduct(int userId,int cartId);
	int deleteProductList(int userId,List<Integer> skuIdList);
	int changeNum(int userId,int cartId,int num);
	int addNum(int userId,int cartId,int num);
	int reduceNum(int userId,int cartId,int num);
	Optional<Integer> getCartItemId(int userId, int skuId);
}
