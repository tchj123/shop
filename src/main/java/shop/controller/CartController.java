package shop.controller;


import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import shop.bean.cart.CartItemDO;
import shop.bean.cart.CartItemVO;
import shop.bean.user.User;
import shop.service.CartService;
import shop.service.ProdService;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	CartService cartService;
	@Autowired
	ProdService prodService;

	@RequestMapping("")
	public ModelAndView cart(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("cart");
		User user=(User) session.getAttribute("user");
		List<CartItemVO> cartItemList=cartService.getAllCartItemList(user.getUserId());
		mav.addObject("cartItemList",cartItemList);
		return mav;
	}

	@RequestMapping("/addProduct")
	@ResponseBody
	public String addProduct(int skuId,int num, HttpSession session) {
		User user = (User) session.getAttribute("user");
		CartItemDO item=new CartItemDO(user.getUserId(),skuId,num);
		return cartService.addProduct(item);
	}

	@RequestMapping("/changeNum")
	@ResponseBody
	public String changeNum(int cartId, int num, HttpSession session) {
		User user = (User) session.getAttribute("user");
		return cartService.changeNum(user.getUserId(),cartId, num);
	}

	@RequestMapping("/deleteProduct")
	@ResponseBody
	public String deleteProduct(int cartId, HttpSession session) {
		User user = (User) session.getAttribute("user");
		return cartService.deleteProduct(user.getUserId(),cartId);
	}


}
