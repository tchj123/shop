package shop;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import shop.bean.Page;
import shop.bean.order.OrderVO;
import shop.bean.product.ProductVO;
import shop.mapper.OrderMapper;
import shop.mapper.SkuMapper;
import shop.mapper.UserMapper;
import shop.service.CartService;
import shop.service.OrderService;
import shop.service.ProdService;
import shop.service.UserService;
import shop.util.ObjectAnalyzer;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class MapperTest {
	@Autowired
	UserMapper userMapper;
	@Autowired
	ProdService prodService;
	@Autowired
	CartService cartService;
	@Autowired
	UserService userService;
	@Autowired
	OrderMapper orderMapper;
	@Autowired
	SkuMapper skuMapper;
	@Autowired
	OrderService orderService;
	@Test
	public void getOrderList() {
		Page<OrderVO> page=orderService.getOrderList(1, null, 1);
		ObjectAnalyzer analyzer=new ObjectAnalyzer();
		analyzer.printObject(page);
	}
	
	@Test
	public void getOrderState(){
		ObjectAnalyzer analyzer=new ObjectAnalyzer();
		System.out.println(orderMapper.getOrderState("2020021714412840968109"));
	}
	

	@Test
	public void test() {
		ProductVO product=prodService.getProduct(1);
		ObjectAnalyzer.printObject(product);
	}

}
