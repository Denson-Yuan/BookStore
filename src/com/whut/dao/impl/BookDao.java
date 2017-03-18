package com.whut.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import com.whut.dao.IBookDao;
import com.whut.model.Book;
import com.whut.model.BookType;
import com.whut.utils.DbUtils;

public class BookDao implements IBookDao {

	@Override
	public int addBook(Book book) {
		String sql = "insert into book (isbn,name,author,typeId,press,publishTime,price,amount,imgPath,"
				+ "introduction) values (?,?,?,?,?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstm = null;
		Integer count = 0;
		try {
			conn = DbUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, book.getIsbn());
			pstm.setString(2, book.getName());
			pstm.setString(3, book.getAuthor());
			pstm.setInt(4, book.getType().getId());
			pstm.setString(5, book.getPress());
			pstm.setString(6, book.getPublishTime());
			pstm.setFloat(7, book.getPrice());
			pstm.setInt(8, book.getAmount());
			pstm.setString(9, book.getImgPath());
			pstm.setString(10, book.getIntroduction());
			count = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(null, pstm, conn);
		}
		return count;
	}

	@Override
	public int deleteBookById(Integer id) {
		String sql = "delete from book where id= ?";
		Connection conn = null;
		PreparedStatement pstm = null;
		Integer count = 0;
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
	public int updateBook(Book book) {
		String sql = "update book set isbn = ?,name=?,author=?,typeId=?,press=?,publishTime=?,"
				+ "price=?,amount=?,imgPath=?,introduction=? where id=?";
		Connection conn = null;
		PreparedStatement pstm = null;
		Integer count = 0;
		try {
			conn = DbUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, book.getIsbn());
			pstm.setString(2, book.getName());
			pstm.setString(3, book.getAuthor());
			pstm.setInt(4, book.getType().getId());
			pstm.setString(5, book.getPress());
			pstm.setString(6, book.getPublishTime());
			pstm.setFloat(7, book.getPrice());
			pstm.setInt(8, book.getAmount());
			pstm.setString(9, book.getImgPath());
			pstm.setString(10, book.getIntroduction());
			pstm.setInt(11, book.getId());
			count = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(null, pstm, conn);
		}
		return count;
	}

	@Override
	public Book findBookById(Integer id) {
		String sql = "select * from book where id = ?";
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Book book = new Book();
		try {
			conn = DbUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			while (rs.next()) {
				BookType bookType = new BookType();
				BookTypeDao btd = new BookTypeDao();
				bookType = btd.findBookTypeById(rs.getInt("typeId"));
				book.setId(rs.getInt("id"));
				book.setIsbn(rs.getString("isbn"));
				book.setName(rs.getString("name"));
				book.setAuthor(rs.getString("author"));
				book.setType(bookType);
				book.setPress(rs.getString("press"));
				String pubTime = rs.getString("publishTime");
				if (pubTime != null && !pubTime.equals("")) {
					pubTime = pubTime.substring(0, 10);
				}
				book.setPublishTime(pubTime);
				book.setPrice(rs.getFloat("price"));
				book.setAmount(rs.getInt("amount"));
				book.setImgPath(rs.getString("imgPath"));
				book.setIntroduction(rs.getString("introduction"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, pstm, conn);
		}
		return book;
	}

	@Override
	public List<Book> findAllBook() {
		String sql = "select * from book";
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Book> list = new ArrayList<Book>();

		try {
			conn = DbUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				BookType bookType = new BookType();
				BookTypeDao btd = new BookTypeDao();
				bookType = btd.findBookTypeById(rs.getInt("typeId"));
				book.setId(rs.getInt("id"));
				book.setIsbn(rs.getString("isbn"));
				book.setName(rs.getString("name"));
				book.setAuthor(rs.getString("author"));
				book.setType(bookType);
				book.setPress(rs.getString("press"));
				String pubTime = rs.getString("publishTime");
				if (pubTime != null && !pubTime.equals("")) {
					pubTime = pubTime.substring(0, 10);
				}
				book.setPublishTime(pubTime);
				book.setPrice(rs.getFloat("price"));
				book.setAmount(rs.getInt("amount"));
				book.setImgPath(rs.getString("imgPath"));
				book.setIntroduction(rs.getString("introduction"));
				list.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, pstm, conn);
		}
		return list;
	}

	@Override
	public List<Book> findBookByType(Integer typeId, int pageBegin, int pageSize) {
		String sql = "SELECT * FROM `book` WHERE `typeId` = ? LIMIT ?,?";
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Book> list = new ArrayList<Book>();

		try {
			conn = DbUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, typeId);
			pstm.setInt(2, pageBegin);
			pstm.setInt(3, pageSize);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				BookType bookType = new BookType();
				BookTypeDao btd = new BookTypeDao();
				bookType = btd.findBookTypeById(rs.getInt("typeId"));
				book.setId(rs.getInt("id"));
				book.setIsbn(rs.getString("isbn"));
				book.setName(rs.getString("name"));
				book.setAuthor(rs.getString("author"));
				book.setType(bookType);
				book.setPress(rs.getString("press"));
				String pubTime = rs.getString("publishTime");
				if (pubTime != null && !pubTime.equals("")) {
					pubTime = pubTime.substring(0, 10);
				}
				book.setPublishTime(pubTime);
				book.setPrice(rs.getFloat("price"));
				book.setAmount(rs.getInt("amount"));
				book.setImgPath(rs.getString("imgPath"));
				book.setIntroduction(rs.getString("introduction"));
				list.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, pstm, conn);
		}
		return list;
	}

	@Override
	public List<Book> findBookByName(String name) {
		String sql = "SELECT * FROM `book` WHERE `name` LIKE '%"+name+"%'";
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Book> list = new ArrayList<Book>();

		try {
			conn = DbUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				BookType bookType = new BookType();
				BookTypeDao btd = new BookTypeDao();
				bookType = btd.findBookTypeById(rs.getInt("typeId"));
				book.setId(rs.getInt("id"));
				book.setIsbn(rs.getString("isbn"));
				book.setName(rs.getString("name"));
				book.setAuthor(rs.getString("author"));
				book.setType(bookType);
				book.setPress(rs.getString("press"));
				String pubTime = rs.getString("publishTime");
				if (pubTime != null && !pubTime.equals("")) {
					pubTime = pubTime.substring(0, 10);
				}
				book.setPublishTime(pubTime);
				book.setPrice(rs.getFloat("price"));
				book.setAmount(rs.getInt("amount"));
				book.setImgPath(rs.getString("imgPath"));
				book.setIntroduction(rs.getString("introduction"));
				list.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, pstm, conn);
		}
		return list;
	}

	@Override
	public int countBookByType(Integer typeId) {
		String sql = "SELECT COUNT(*) `bookNum` FROM `book` WHERE typeId = ?";
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = DbUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, typeId);
			rs = pstm.executeQuery();
			rs.next();
			count = rs.getInt("bookNum");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, pstm, conn);
		}
		return count;
	}

	@Override
	public int countBookByName(String name) {
		String sql = "SELECT COUNT(*) `bookNum` FROM `book` WHERE name LIKE '%"+name+"%'";
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = DbUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			rs.next();
			count = rs.getInt("bookNum");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtils.close(rs, pstm, conn);
		}
		return count;
	}

}
