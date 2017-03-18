package com.whut.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.whut.dao.IOrderDetailDao;
import com.whut.model.Book;
import com.whut.model.Order;
import com.whut.model.OrderDetail;
import com.whut.utils.DbUtils;

public class OrderDetailDao implements IOrderDetailDao {

	@Override
	public int addOrderDetail(OrderDetail od) {
		String sql = "INSERT INTO `order_detail` (orderId,bookId,count) VALUES (?,?,?)";
		Connection conn = null;
		PreparedStatement pstm = null;
		int count = 0;
		try {
			conn = DbUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, od.getOrder().getId());
			pstm.setInt(2, od.getBook().getId());
			pstm.setInt(3, od.getCount());
			count = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(null, pstm, conn);
		}
		return count;
	}

	@Override
	public int deleteOrderDetailByOrderId(Integer orderId) {
		String sql = "DELETE FROM `order_detail` WHERE orderId = ?";
		Connection conn = null;
		PreparedStatement pstm = null;
		int count = 0;
		try {
			conn = DbUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, orderId);
			count = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(null, pstm, conn);
		}
		return count;
	}

	@Override
	public List<OrderDetail> findOrderDetailsByOrderId(Integer orderId) {
		String sql = "SELECT * FROM `order_detail` WHERE `orderId` = ?";
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<OrderDetail> list = new ArrayList<>();

		try {
			conn = DbUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, orderId);
			rs = pstm.executeQuery();
			while (rs.next()) {
				OrderDetail odd = new OrderDetail();
				
				Order order = new OrderDao().findOrderById(rs.getInt("orderId"));
				Book book = new BookDao().findBookById(rs.getInt("bookId"));
				
				odd.setOrder(order);
				odd.setBook(book);
				odd.setCount(rs.getInt("count"));
				
				list.add(odd);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, pstm, conn);
		}

		return list;
	}

	@Override
	public List<OrderDetail> findAllOrderDetails() {
		String sql = "SELECT order.id,userId,orderTime,finishTime,orderState,remark,order_detail.id,bookId,count FROM "
				+ "bookstore.order left outer join order_detail on  order.id=order_detail.orderId";
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<OrderDetail> list = new ArrayList<>();

		try {
			conn = DbUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				OrderDetail odd = new OrderDetail();
				
				Order order = new OrderDao().findOrderById(rs.getInt("order.id"));
				Book book = new BookDao().findBookById(rs.getInt("bookId"));
				odd.setOrder(order);
				odd.setBook(book);
				odd.setCount(rs.getInt("count"));
				list.add(odd);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, pstm, conn);
		}

		return list;
	}

	@Override
	public List<OrderDetail> searchOrders(String state) {
		String sql = "select * FROM bookstore.order left outer join order_detail on  "
				+ "order.id=order_detail.orderId ";
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		 if (state != null && !state.equals(""))
         {
             int sta = Integer.parseInt(state);
             sql = sql + "where orderState='" +sta+ "'";
            
         }
         
		List<OrderDetail> list = new ArrayList<>();

		try {
			conn = DbUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				OrderDetail odd = new OrderDetail();
				
				Order order = new OrderDao().findOrderById(rs.getInt("order.id"));
				Book book = new BookDao().findBookById(rs.getInt("bookId"));
				odd.setOrder(order);
				odd.setBook(book);
				odd.setCount(rs.getInt("count"));
				list.add(odd);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, pstm, conn);
		}

		return list;
	}

}
