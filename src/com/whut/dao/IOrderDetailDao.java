package com.whut.dao;

import java.util.List;

import com.whut.model.OrderDetail;

public interface IOrderDetailDao {
	public int addOrderDetail(OrderDetail od);
	
	public int deleteOrderDetailByOrderId(Integer orderId);
	
	public List<OrderDetail> findOrderDetailsByOrderId(Integer orderId);

	public List<OrderDetail> findAllOrderDetails();
	
	public List<OrderDetail> searchOrders(String state);
}
