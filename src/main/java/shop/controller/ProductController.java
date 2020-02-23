package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import shop.bean.product.ProductVO;
import shop.bean.product.Sku;
import shop.service.ProdService;

import java.util.List;

@Controller
public class ProductController {
	@Autowired
	private ProdService prodService;
	
//	@RequestMapping("/product")
//	public ModelAndView getProduct(int productId) {
//		ModelAndView mav=new ModelAndView();
//		mav.addObject(prodService.getProduct(productId));
//		mav.setViewName("product");
//		return mav;
//		
//	}		
	
	@RequestMapping("/product")
	public ModelAndView product() {
		ModelAndView mav=new ModelAndView();
		ProductVO prod=prodService.getProduct(1);
		mav.addObject("prod",prod);
		mav.setViewName("product");
		return mav;
		
	}

	@RequestMapping("/getSku")
	@ResponseBody
	public Sku getSku(@RequestParam("prodId")int prodId, @RequestParam("skprIdList")List<Integer> skprIdList) {
		return prodService.getSku(prodId,skprIdList);
	}

	@RequestMapping("/test")
	public String test(){return "alipay";}
}
