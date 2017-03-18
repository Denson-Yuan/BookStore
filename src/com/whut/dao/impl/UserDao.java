package com.whut.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.whut.dao.IUserDao;
import com.whut.model.User;
import com.whut.utils.DbUtils;

public class UserDao implements IUserDao {
	@Override
	public int addUser(User user) {
		String sql = "insert into user (email,password,role,name,sex,phone,address1,address2) values (?,?,?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstm = null;
		int count = 0;
		try {
			conn = DbUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, user.getEmail());
			pstm.setString(2, user.getPassword());
			pstm.setInt(3, user.getRole());
			pstm.setString(4, user.getName());
			pstm.setInt(5, user.getSex());
			pstm.setString(6, user.getPhone());
			pstm.setString(7, user.getAddress1());
			pstm.setString(8, user.getAddress2());
			count = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(null, pstm, conn);
		}
		return count;
	}

	@Override
	public boolean emailIsExist(String email) {
		String sql = "select email from user where email = ?";
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		boolean b = false;
		try {
			conn = DbUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, email);
			rs = pstm.executeQuery();
			if (rs.next()) {
				b = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, pstm, conn);
		}
		return b;
	}

	public int deleteUser(User user) {
		String sql = "delete from user where email= ?";
		Connection conn = null;
		PreparedStatement pstm = null;
		Integer count = 0;
		try {
			conn = DbUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, user.getEmail());
			count = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(null, pstm, conn);
		}
		return count;
	}

	public int updateUser(User user) {
		String sql = "update user set phone = ?,address1 = ?,address2=? where email = ?";
		Connection conn = null;
		PreparedStatement pstm = null;
		int count = 0;
		try {
			conn = DbUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, user.getPhone());
			pstm.setString(2, user.getAddress1());
			pstm.setString(3, user.getAddress2());
			pstm.setString(4, user.getEmail());
			count = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(null, pstm, conn);
		}
		return count;

	}

	public List<User> findAllUser() {
		String sql = "select * from user where role=1";
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<User> list = new ArrayList<User>();

		try {
			conn = DbUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setName(rs.getString("name"));
				user.setSex(rs.getInt("sex"));
				user.setAddress1(rs.getString("address1"));
				user.setAddress2(rs.getString("address2"));
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, pstm, conn);
		}
		return list;
	}

	public User findUserByEmail(String email) {
		String sql = "select * from user where email = ? and role=1";
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		User user = null;

		try {
			conn = DbUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, email);
			rs = pstm.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setRole(rs.getInt("role"));
				user.setSex(rs.getInt("sex"));
				user.setPhone(rs.getString("phone"));
				user.setAddress1(rs.getString("address1"));
				user.setAddress2(rs.getString("address2"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, pstm, conn);
		}
		return user;
	}

	@Override
	public User findUserById(Integer id) {
		String sql = "select * from user where id = ?";
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		User user = null;

		try {
			conn = DbUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setSex(rs.getInt("sex"));
				user.setPhone(rs.getString("phone"));
				user.setAddress1(rs.getString("address1"));
				user.setAddress2(rs.getString("address2"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, pstm, conn);
		}
		return user;
	}

	@Override
	public User findManagerByEmail(String email) {
		String sql = "select * from user where email = ? and role=0";
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		User user = null;

		try {
			conn = DbUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, email);
			
			rs = pstm.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setRole(rs.getInt("role"));
				user.setSex(rs.getInt("sex"));
				user.setPhone(rs.getString("phone"));
				user.setAddress1(rs.getString("address1"));
				user.setAddress2(rs.getString("address2"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, pstm, conn);
		}
		return user;
	}
}
