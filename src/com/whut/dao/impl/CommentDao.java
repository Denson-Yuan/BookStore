package com.whut.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.whut.dao.ICommentDao;
import com.whut.model.Book;
import com.whut.model.Comment;
import com.whut.model.User;
import com.whut.utils.DbUtils;

public class CommentDao implements ICommentDao {

	@Override
	public int addComment(Comment comment) {
		String sql = "INSERT INTO `comment` (bookId,userId,content,level,time) VALUES (?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstm = null;
		int count = 0;
		try {
			conn = DbUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, comment.getBook().getId());
			pstm.setInt(2, comment.getUser().getId());
			pstm.setString(3, comment.getContent());
			pstm.setInt(4, comment.getLevel());
			pstm.setString(5, comment.getTime());
			count = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(null, pstm, conn);
		}
		return count;
	}

	@Override
	public int deleteCommentById(Integer id) {
		String sql = "DELETE FROM `comment` WHERE id = ?";
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
	public List<Comment> findCommentsByBookId(Integer bookId, int pageBegin, int pageSize) {
		String sql = "SELECT * FROM `comment` WHERE `bookId` = ? ORDER BY `time` DESC LIMIT ?,?";
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Comment> list = new ArrayList<>();

		try {
			conn = DbUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, bookId);
			pstm.setInt(2, pageBegin);
			pstm.setInt(3, pageSize);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Comment comment = new Comment();
				Book book = new BookDao().findBookById(bookId);
				User user = new UserDao().findUserById(rs.getInt("userId"));
				
				comment.setId(rs.getInt("id"));
				comment.setBook(book);
				comment.setUser(user);
				comment.setContent(rs.getString("content"));
				comment.setLevel(rs.getInt("level"));
				comment.setTime(rs.getString("time"));
				
				list.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, pstm, conn);
		}

		return list;
	}

	@Override
	public int countCommentsByBookId(Integer bookId) {
		String sql = "SELECT COUNT(*) `commentNum` FROM `comment` WHERE bookId = ?";
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = DbUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, bookId);
			rs = pstm.executeQuery();
			rs.next();
			count = rs.getInt("commentNum");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, pstm, conn);
		}
		return count;
	}

}
