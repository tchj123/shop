package shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.bean.cart.CartItemDO;
import shop.bean.cart.CartItemVO;
import shop.mapper.CartMapper;
import shop.service.CartService;
import shop.service.ProdService;
import shop.util.RedisUtil;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartMapper cartMapper;
	@Autowired
	ProdService prodService;
	@Autowired
	RedisUtil redis;

	/**
	 * TODO：关于购物车商品失效问题。目前为了效率，暂定10分钟购物车里的商品状态更新一次。
	 * @return
	 */
	@Override
	public List<CartItemVO> getAllCartItemList(int userId) {
		return cartMapper.getAllCartItemList(userId);
	}

	@Override
	public List<CartItemVO> getCartItemList(int userId, List<Integer> cartItemIdList) {
		return cartMapper.getCartItemList(userId, cartItemIdList);
	}

	@Override
	public String addProduct(CartItemDO item) {
		int prodId=prodService.getProdId(item.getSkuId());
		item.setProdId(prodId);
		//如果购物车已经存在相同商品了，则直接增加其数量
		int cartId=cartMapper.getCartItemId(item.getUserId(),item.getSkuId()).orElse(-1);
		if(cartId!=-1)
			return cartMapper.addNum(item.getUserId(),cartId,item.getNum())==1?"success":"fail";
		//否则，添加商品到购物车
		return cartMapper.addProduct(item)==1?"success":"fail";
	}

	@Override
	public String deleteProduct(int userId, int cartId) {
		return cartMapper.deleteProduct(userId, cartId)==1?"success":"fail";
	}

	@Override
	public String  changeNum(int userId, int cartId, int num) {
		return cartMapper.changeNum(userId, cartId, num)==1?"success":"fail";
	}

	@Override
	public String deleteProductList(int userId, List<Integer> cartIdList) {
		return cartMapper.deleteProductList(userId, cartIdList)== cartIdList.size()?"success":"fail";
	}


}
