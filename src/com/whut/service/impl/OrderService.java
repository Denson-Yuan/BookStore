package com.whut.service.impl;

import java.util.List;

import com.whut.dao.IBookDao;
import com.whut.dao.ICartItemDao;
import com.whut.dao.IOrderDao;
import com.whut.dao.IOrderDetailDao;
import com.whut.dao.impl.BookDao;
import com.whut.dao.impl.CartItemDao;
import com.whut.dao.impl.OrderDao;
import com.whut.dao.impl.OrderDetailDao;
import com.whut.model.CartItem;
import com.whut.model.Order;
import com.whut.model.OrderDetail;
import com.whut.service.IOrderService;
import com.whut.utils.Page;

public class OrderService implements IOrderService {
	
	private IOrderDao orderDao = new OrderDao();
	
	private IOrderDetailDao orderDetailDao = new OrderDetailDao(); 
	
	private ICartItemDao cartItemDao = new CartItemDao(); 
	
	private IBookDao bookDao = new BookDao();

	@Override
	public int addOrder(Order order) {
		//添加订单
		int orderId = orderDao.addOrder(order);
		order.setId(orderId);
		//逐一将购物车中的条目添加至订单项
		int userId = order.getUser().getId();
		List<CartItem> list = cartItemDao.findCartItemByUserId(userId);
		for (CartItem cartItem : list) {
			OrderDetail od = new OrderDetail();
			od.setOrder(order);
			od.setBook(bookDao.findBookById(cartItem.getBook().getId()));
			od.setCount(cartItem.getAmount());
			orderDetailDao.addOrderDetail(od);
			//删除购物车中条目
			cartItemDao.deleteCartItemById(cartItem.getId());
		}
		return 0;
	}

	@Override
	public int delteteOrderById(Integer id) {
		orderDetailDao.deleteOrderDetailByOrderId(id);
		orderDao.deleteOrderById(id);
		return 0;
	}

	@Override
	public Page<Order> findAllOrders(int pageNum, int pageSize) {
		int pageBegin = (pageNum - 1) * pageSize;
		List<Order> list = orderDao.findAllOrders(pageBegin, pageSize);
		int totalRecord = orderDao.countAllOrders();
		int totalPage = totalRecord / pageSize;
		if(totalRecord % pageSize != 0){
			totalPage++;
		}
		return new Page<Order>(pageNum,totalPage,list);
	}

	@Override
	public Order findOrderById(Integer id) {
		return orderDao.findOrderById(id);
	}

	@Override
	public Page<Order> findOrdersByUserId(Integer userId, int pageNum, int pageSize) {
		int pageBegin = (pageNum - 1) * pageSize;
		List<Order> list = orderDao.findOrdersByUserId(userId, pageBegin, pageSize);
		int totalRecord = orderDao.countOrdersByUserId(userId);
		int totalPage = totalRecord / pageSize;
		if(totalRecord % pageSize != 0){
			totalPage++;
		}
		return new Page<Order>(pageNum,totalPage,list);
	}

	@Override
	public Page<Order> findOrdersByOrderState(Integer state, int pageNum, int pageSize) {
		int pageBegin = (pageNum - 1) * pageSize;
		List<Order> list = orderDao.findOrdersByOrderState(state, pageBegin, pageSize);
		int totalRecord = orderDao.countOrdersByOrderState(state);
		int totalPage = totalRecord / pageSize;
		if(totalRecord % pageSize != 0){
			totalPage++;
		}
		return new Page<Order>(pageNum,totalPage,list);
	}

	@Override
	public List<OrderDetail> findAllOrderDetails() {
		return orderDetailDao.findAllOrderDetails();
	}

	@Override
	public List<OrderDetail> searchOrders(String state) {
		return orderDetailDao.searchOrders(state);
	}

	@Override
	public int confirmOrder(Order order) {
		return orderDao.confirmOrder(order);
	}

	@Override
	public List<OrderDetail> findOrderDetailByOrderId(Integer orderId) {
		return orderDetailDao.findOrderDetailsByOrderId(orderId);
	}
}
