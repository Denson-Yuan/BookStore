package com.whut.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;
import com.whut.dao.IOrderDao;
import com.whut.model.Order;
import com.whut.model.User;
import com.whut.utils.DbUtils;

public class OrderDao implements IOrderDao {

	@Override
	public int addOrder(Order order) {

		String sql = "INSERT INTO `order` (userId,orderTime,finishTime,orderState,remark,reciver,phone,address) VALUES (?,?,?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = DbUtils.getConnection();
			pstm = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			pstm.setInt(1, order.getUser().getId());
			pstm.setString(2, order.getOrderTime());
			pstm.setString(3, order.getFinishTime());
			pstm.setInt(4, order.getOrderState());
			pstm.setString(5, order.getRemark());
			pstm.setString(6, order.getReciver());
			pstm.setString(7, order.getPhone());
			pstm.setString(8, order.getAddress());
			pstm.executeUpdate();
			rs = pstm.getGeneratedKeys();
			if(rs.next()){
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(null, pstm, conn);
		}
		
		return count;
	}

	@Override
	public int deleteOrderById(Integer id) {
		String sql = "DELETE FROM `order` WHERE id = ?";
		Connection conn = null;
		PreparedStatement pstm = null;
		int count = 0;
		try {
			conn = DbUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			count = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(null, pstm, conn);
		}
		return count;
	}

	@Override
	public List<Order> findAllOrders(int pageBegin, int pageSize) {
		String sql = "SELECT * FROM `order` ORDER BY `orderTime` DESC LIMIT ?,?";
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Order> list = new ArrayList<Order>();

		try {
			conn = DbUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, pageBegin);
			pstm.setInt(2, pageSize);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Order od = new Order();
				User u = new UserDao().findUserById(rs.getInt("userId"));

				od.setId(rs.getInt("id"));
				od.setUser(u);
				String orderTime = rs.getString("orderTime");
				od.setOrderTime(orderTime.substring(0, orderTime.length() - 2));
				String finishTime = rs.getString("finishTime");
				od.setFinishTime(finishTime.substring(0, finishTime.length() - 2));
				od.setOrderState(rs.getInt("orderState"));
				od.setRemark(rs.getString("remark"));
				od.setReciver(rs.getString("reciver"));
				od.setPhone(rs.getString("phone"));
				od.setAddress(rs.getString("address"));
				list.add(od);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, pstm, conn);
		}

		return list;
	}

	@Override
	public List<Order> findOrdersByUserId(Integer userId, int pageBegin, int pageSize) {
		String sql = "SELECT * FROM `order` WHERE `userId` = ? ORDER BY `orderTime` DESC LIMIT ?,?";
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Order> list = new ArrayList<Order>();

		try {
			conn = DbUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, userId);
			pstm.setInt(2, pageBegin);
			pstm.setInt(3, pageSize);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Order od = new Order();
				User u = new UserDao().findUserById(userId);
				od.setId(rs.getInt("id"));
				od.setUser(u);
				String orderTime = rs.getString("orderTime");
				if(orderTime!=null && !"".equals(orderTime)){
					od.setOrderTime(orderTime.substring(0, orderTime.length() - 2));
				}
				String finishTime = rs.getString("finishTime");
				if(finishTime!=null && !"".equals(finishTime)){
					od.setFinishTime(finishTime.substring(0, finishTime.length() - 2));
				}
				od.setOrderState(rs.getInt("orderState"));
				od.setRemark(rs.getString("remark"));
				od.setReciver(rs.getString("reciver"));
				od.setPhone(rs.getString("phone"));
				od.setAddress(rs.getString("address"));
				list.add(od);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, pstm, conn);
		}

		return list;
	}

	@Override
	public List<Order> findOrdersByOrderState(Integer state, int pageBegin, int pageSize) {
		String sql = "SELECT * FROM `order` WHERE `orderState` = ? ORDER BY `orderTime` DESC LIMIT ?,?";
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Order> list = new ArrayList<Order>();

		try {
			conn = DbUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, state);
			pstm.setInt(2, pageBegin);
			pstm.setInt(3, pageSize);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Order od = new Order();
				User u = new UserDao().findUserById(rs.getInt("userId"));

				od.setId(rs.getInt("id"));
				od.setUser(u);
				String orderTime = rs.getString("orderTime");
				od.setOrderTime(orderTime.substring(0, orderTime.length() - 2));
				String finishTime = rs.getString("finishTime");
				od.setFinishTime(finishTime.substring(0, finishTime.length() - 2));
				od.setOrderState(rs.getInt("orderState"));
				od.setRemark(rs.getString("remark"));
				od.setReciver(rs.getString("reciver"));
				od.setPhone(rs.getString("phone"));
				od.setAddress(rs.getString("address"));
				list.add(od);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, pstm, conn);
		}
		return list;
	}

	@Override
	public int countAllOrders() {
		String sql = "SELECT COUNT(*) `orderNum` FROM `order`";
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = DbUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			rs.next();
			count = rs.getInt("orderNum");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, pstm, conn);
		}
		return count;
	}

	@Override
	public int countOrdersByUserId(Integer userId) {
		String sql = "SELECT COUNT(*) `orderNum` FROM `order` WHERE userId = ?";
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = DbUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, userId);
			rs = pstm.executeQuery();
			rs.next();
			count = rs.getInt("orderNum");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, pstm, conn);
		}
		return count;
	}

	@Override
	public int countOrdersByOrderState(Integer state) {
		String sql = "SELECT COUNT(*) `orderNum` FROM `order` WHERE orderState = ?";
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = DbUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, state);
			rs = pstm.executeQuery();
			rs.next();
			count = rs.getInt("orderNum");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, pstm, conn);
		}
		return count;
	}

	@Override
	public Order findOrderById(Integer id) {
		String sql = "SELECT * FROM `order` WHERE id = ?";
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Order order = null;

		try {
			conn = DbUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			while (rs.next()) {
				order = new Order();
				User u = new UserDao().findUserById(rs.getInt("userId"));
				order.setId(id);
				order.setUser(u);
				order.setOrderTime(rs.getString("orderTime"));
				order.setFinishTime(rs.getString("finishTime"));
				order.setOrderState(rs.getInt("orderState"));
				order.setRemark(rs.getString("remark"));
				order.setReciver(rs.getString("reciver"));
				order.setPhone(rs.getString("phone"));
				order.setAddress(rs.getString("address"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, pstm, conn);
		}
		return order;
	}

	@Override
	public int confirmOrder(Order or) {
		String sql="update `order` set orderState=3 where id = ?";
		Connection conn = null;
		PreparedStatement pstm = null;
		int count = 0;
		try {
			conn = DbUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, or.getId());
		
			count = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(null, pstm, conn);
		}
		return count;
	}

}
