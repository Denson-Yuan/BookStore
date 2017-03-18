package com.whut.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.whut.dao.IBookTypeDao;
import com.whut.model.BookType;
import com.whut.utils.DbUtils;

public class BookTypeDao implements IBookTypeDao {

	public int addBookType(BookType bt) {
		String sql = "insert into booktype (typeName) values (?)";
		Connection conn = null;
		PreparedStatement pstm = null;
		int count = 0;
		try {
			conn = DbUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, bt.getTypeName());
		    count=pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(null, pstm, conn);
		}
		return count;
	}

	@Override
	public int deleteBookTypeById(Integer id) {
		String sql = "delete from booktype where id= ?";
		Connection conn = null;
		PreparedStatement pstm = null;
		int count = 0;
		try {
			conn = DbUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			count=pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(null, pstm, conn);
		}
		return count;
	}

	@Override
	public int updateBookType(BookType bt) {
		String sql = "update booktype set typeName = ?";
		Connection conn = null;
		PreparedStatement pstm = null;
		int count = 0;
		try {
			conn = DbUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, bt.getTypeName());
			count=pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(null, pstm, conn);
		}
		return count;
	}

	@Override
	public List<BookType> findAllBookType() {
		String sql = "select * from booktype";
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<BookType> list = new ArrayList<BookType>();
		
		try {
			conn = DbUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				BookType bookType = new BookType();
				bookType.setId(rs.getInt("id"));
				bookType.setTypeName(rs.getString("typeName"));
                
                list.add(bookType);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, pstm, conn);
		}
		return list;
	}

	@Override
	public BookType findBookTypeById(Integer id) {
		String sql = "select * from booktype where id = ?";
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		BookType bt=new BookType();
		try {
			conn = DbUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			while (rs.next()) {
				bt.setId(rs.getInt("id"));
                bt.setTypeName(rs.getString("typeName"));
                
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, pstm, conn);
		}
		return bt;
	
	}

	@Override
	public BookType findBookTypeByName(String typeName) {
		String sql = "select * from booktype where typeName = ?";
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		BookType bt=new BookType();
		try {
			
			conn = DbUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, typeName);
			rs = pstm.executeQuery();
			while (rs.next()) {
				bt.setId(rs.getInt("id"));
                bt.setTypeName(rs.getString("typeName"));
                
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, pstm, conn);
		}
		return bt;
	}

	

}
