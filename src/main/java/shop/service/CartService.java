package shop.service;

import java.util.List;

import shop.bean.cart.CartItemDO;
import shop.bean.cart.CartItemVO;

public interface CartService {

	List<CartItemVO> getAllCartItemList(int userId);
	List<CartItemVO> getCartItemList(int userId,List<Integer> cartItemIdList);
	String addProduct(CartItemDO item);
	String deleteProduct(int userId,int cartId);
	String deleteProductList(int userId,List<Integer> cartIdList);
	String changeNum(int userId,int cartId,int num);
}
