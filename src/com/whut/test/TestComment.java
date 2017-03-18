package com.whut.test;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;

import com.whut.dao.ICommentDao;
import com.whut.dao.impl.BookDao;
import com.whut.dao.impl.CommentDao;
import com.whut.dao.impl.UserDao;
import com.whut.model.Book;
import com.whut.model.Comment;
import com.whut.model.User;

public class TestComment {
	
	
	@Test
	public void testAdd(){
		Book book = new BookDao().findBookById(3);
		User user = new UserDao().findUserById(4);
		Comment comment = new Comment();
		comment.setBook(book);
		comment.setUser(user);
		comment.setContent("!!!!!!!");
		comment.setLevel(5);
		comment.setTime(LocalDateTime.now().toString());
		ICommentDao commentDao = new CommentDao();
		commentDao.addComment(comment);
	}
	
	@Test
	public void testDelete(){
		ICommentDao cmd = new CommentDao();
		int id = 1;
		cmd.deleteCommentById(id);
	}
	
	@Test
	public void testFind(){
		ICommentDao dao = new CommentDao();
		List<Comment> list = dao.findCommentsByBookId(3, 0, 3);
		for (Comment comment : list) {
			System.out.println(comment.getId());
			System.out.println(comment.getContent());
			System.out.println(comment.getTime());
			System.out.println();
		}
	}
}
