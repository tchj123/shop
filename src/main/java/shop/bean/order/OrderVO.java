package shop.bean.order;

import java.util.Date;
import java.util.List;

public class OrderVO {
	private int orderId;
	private String orderNo;
	private int totalNum;
	private double orderAmount;
	private OrderState state;
	private Date createTime;
	private List<OrderItemVO> orderItemVOList;
	
	public OrderVO() {
		super();
	}
	public OrderVO(int orderId, String orderNo, int totalNum, double orderAmount) {
		super();
		this.orderId = orderId;
		this.orderNo = orderNo;
		this.totalNum = totalNum;
		this.orderAmount = orderAmount;
	}
	public OrderVO(int orderId, String orderNo, int totalNum, double orderAmount, List<OrderItemVO> orderItemVO) {
		super();
		this.orderId = orderId;
		this.orderNo = orderNo;
		this.totalNum = totalNum;
		this.orderAmount = orderAmount;
		this.orderItemVOList = orderItemVO;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public double getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}
	public List<OrderItemVO> getOrderItemVOList() {
		return orderItemVOList;
	}
	public void setOrderItemVOList(List<OrderItemVO> orderItemVOList) {
		this.orderItemVOList = orderItemVOList;
	}
	public OrderState getState() {
		return state;
	}

	public void setState(OrderState state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
