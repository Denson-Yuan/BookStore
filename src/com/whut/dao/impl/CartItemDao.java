package com.whut.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.whut.dao.ICartItemDao;
import com.whut.model.Book;
import com.whut.model.CartItem;
import com.whut.model.User;
import com.whut.utils.DbUtils;

public class CartItemDao implements ICartItemDao {

	@Override
	public int addCartItem(CartItem ci) {
		String sql = "INSERT INTO `cart_item` (userId,bookId,amount) VALUES (?,?,?)";
		Connection conn = null;
		PreparedStatement pstm = null;
		int count = 0;
		try {
			conn = DbUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, ci.getUser().getId());
			pstm.setInt(2, ci.getBook().getId());
			pstm.setInt(3, ci.getAmount());
			count = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(null, pstm, conn);
		}
		return count;

	}

	@Override
	public int deleteCartItemById(Integer id) {
		String sql = "DELETE FROM `cart_item` WHERE id = ?";
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
	public List<CartItem> findCartItemByUserId(Integer userId) {
		String sql = "SELECT * FROM `cart_item` WHERE `userId` = ?";
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<CartItem> list = new ArrayList<>();

		try {
			conn = DbUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, userId);
			rs = pstm.executeQuery();
			while (rs.next()) {
				CartItem ci = new CartItem();
				User user = new UserDao().findUserById(userId);
				Book book = new BookDao().findBookById(rs.getInt("bookId"));

				ci.setId(rs.getInt("id"));
				ci.setUser(user);
				ci.setBook(book);
				ci.setAmount(rs.getInt("amount"));

				list.add(ci);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, pstm, conn);
		}
		return list;
	}

	@Override
	public int updateItemNum(Integer id, Integer num) {
		String sql = "UPDATE `cart_item` set `amount` = ? WHERE `id`=?";
		Connection conn = null;
		PreparedStatement pstm = null;
		int count = 0;
		try {
			conn = DbUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, num);
			pstm.setInt(2, id);
			count = pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(null, pstm, conn);
		}
		return count;
	}

	@Override
	public CartItem findCartItemByUserIdAndBookId(Integer userId, Integer bookId) {
		String sql = "SELECT * FROM `cart_item` WHERE `userId` = ? and bookId = ?";
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		CartItem ci = null;

		try {
			conn = DbUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, userId);
			pstm.setInt(2, bookId);
			rs = pstm.executeQuery();
			while (rs.next()) {
				ci = new CartItem();
				User user = new UserDao().findUserById(userId);
				Book book = new BookDao().findBookById(rs.getInt("bookId"));
					
				ci.setId(rs.getInt("id"));
				ci.setUser(user);
				ci.setBook(book);
				ci.setAmount(rs.getInt("amount"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, pstm, conn);
		}
		return ci;
	}
}
