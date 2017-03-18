package com.whut.test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.whut.dao.IOrderDao;
import com.whut.dao.impl.OrderDao;
import com.whut.model.Order;
import com.whut.model.User;

public class TestOrder {
	
	@Test
	public void testAddOrder(){
		User u = new User();
		u.setId(1);
		Order od = new Order();
		od.setUser(u);
		od.setOrderTime(LocalDateTime.now().toString());
		od.setOrderState(2);
		od.setRemark("好的123");
		IOrderDao odd = new OrderDao();
		int lastId = odd.addOrder(od);
		System.out.println(lastId);
	}
	
	@Test
	public void testDelOrder(){
		IOrderDao odd = new OrderDao();
		System.out.println(odd.deleteOrderById(3));
	}
	
	@Test
	public void testFindAllOrders(){
		List<Order> list = new ArrayList<Order>();
		IOrderDao odd = new OrderDao();
		list = odd.findAllOrders(0,8);
		for (Order order : list) {
			System.out.println("id: "+order.getId());
			System.out.println("userId: "+order.getUser().getId());
			System.out.println("orderTime: "+order.getOrderTime());
			System.out.println("finishTime: "+order.getFinishTime());
			System.out.println("orderState: "+order.getOrderState());
			System.out.println("remark: "+order.getRemark());
			System.out.println();
		}
	}
	
	@Test
	public void testFindOrdersByUserId(){
		List<Order> list = new ArrayList<Order>();
		IOrderDao odd = new OrderDao();
		list = odd.findOrdersByUserId(1, 2, 2);
		for (Order order : list) {
			System.out.println("id: "+order.getId());
			System.out.println("userId: "+order.getUser().getId());
			System.out.println("orderTime: "+order.getOrderTime());
			System.out.println("finishTime: "+order.getFinishTime());
			System.out.println("orderState: "+order.getOrderState());
			System.out.println("remark: "+order.getRemark());
			System.out.println();
		}
	}
	
	@Test
	public void testFindOrdersByState(){
		List<Order> list = new ArrayList<Order>();
		IOrderDao odd = new OrderDao();
		list = odd.findOrdersByOrderState(1, 0, 8);
		for (Order order : list) {
			System.out.println("id: "+order.getId());
			System.out.println("userId: "+order.getUser().getId());
			System.out.println("orderTime: "+order.getOrderTime());
			System.out.println("finishTime: "+order.getFinishTime());
			System.out.println("orderState: "+order.getOrderState());
			System.out.println("remark: "+order.getRemark());
			System.out.println();
		}
	}
	
	@Test
	public void testCount(){
		IOrderDao odd = new OrderDao();
		System.out.println(odd.countAllOrders());
	}
}
