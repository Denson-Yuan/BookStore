package com.whut.test;

import java.util.List;

import org.junit.Test;

import com.whut.dao.IOrderDetailDao;
import com.whut.dao.impl.OrderDetailDao;
import com.whut.model.Book;
import com.whut.model.Order;
import com.whut.model.OrderDetail;

public class TestOrderDetail {
	
	@Test
	public void testAdd(){
		Order order = new Order();
		Book book = new Book();
		order.setId(6);
		book.setId(1);
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrder(order);
		orderDetail.setBook(book);
		orderDetail.setCount(3);
		IOrderDetailDao oddd = new OrderDetailDao();
		oddd.addOrderDetail(orderDetail);
	}
	
	@Test
	public void testDeleteByOrderId(){
		Order order = new Order();
		order.setId(5);
		IOrderDetailDao oddd = new OrderDetailDao();
		oddd.deleteOrderDetailByOrderId(order.getId());
	}
	
	@Test
	public void testfind(){
		IOrderDetailDao odd = new OrderDetailDao();
		List<OrderDetail> list = odd.findOrderDetailsByOrderId(6);
		for (OrderDetail orderDetail : list) {
			System.out.println(orderDetail.getBook().getName());
		}
	}
}
