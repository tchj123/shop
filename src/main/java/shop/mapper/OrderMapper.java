package shop.mapper;

import java.util.List;
import java.util.Optional;

import shop.bean.order.OrderDO;
import shop.bean.order.OrderItemDO;
import shop.bean.order.OrderState;
import shop.bean.order.OrderVO;

public interface OrderMapper {
	int insertOrder(OrderDO order);
	int insertOrderItem(List<OrderItemDO> itemList);
	int getCountOfOrder(int userId, OrderState state);
	List<OrderVO> getOrderVOList(int userId, OrderState state, int page, int onePageNum);
	Optional<OrderDO> getOrderDO(int userId,String orderNo);
	int orderPaied(String orderNo, String payNo,OrderState state);
	OrderState getOrderState(String orderNo);
	int cancelOrder(String orderNo, int userId, OrderState state);
    Optional<Integer> getOrderAmount(int userId, String orderNo);
	boolean hasOrder(String orderNo, Double orderAmount);
}
