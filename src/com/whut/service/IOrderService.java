package com.whut.service;

import java.util.List;

import com.whut.model.Order;
import com.whut.model.OrderDetail;
import com.whut.utils.Page;

public interface IOrderService {
	
public int addOrder(Order order);
	
	public int delteteOrderById(Integer id);
	
	public List<OrderDetail> findAllOrderDetails();
	
	public List<OrderDetail> searchOrders(String state);
	
	public int confirmOrder(Order order);
	
	public Page<Order> findAllOrders(int pageNum,int pageSize);
	
	public Order findOrderById(Integer id);
	
	public Page<Order> findOrdersByUserId(Integer userId,int pageNum,int pageSize);
	
	public Page<Order> findOrdersByOrderState(Integer state,int pageNum,int pageSize);
	
	public List<OrderDetail> findOrderDetailByOrderId(Integer orderId);
}
