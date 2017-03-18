package com.whut.dao;

import java.util.List;

import com.whut.model.Order;

public interface IOrderDao {
	/*
	 * 返回新插入后的记录的id
	 */
	public int addOrder(Order order);

	public int deleteOrderById(Integer id);
	
	public int countAllOrders();

	public List<Order> findAllOrders(int pageBegin, int pageSize);
	
	public Order findOrderById(Integer id);
	
	public int countOrdersByUserId(Integer userId);

	public List<Order> findOrdersByUserId(Integer userId, int pageBegin, int pageSize);
	
	public int countOrdersByOrderState(Integer state);

	public List<Order> findOrdersByOrderState(Integer state, int pageBegin, int pageSize);
	
	
	public int confirmOrder(Order or);
}
