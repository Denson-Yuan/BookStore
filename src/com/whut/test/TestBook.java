package com.whut.test;

import java.util.List;

import org.junit.Test;

import com.whut.dao.IBookDao;
import com.whut.dao.impl.BookDao;
import com.whut.model.Book;

public class TestBook {
	@Test
	public void testFindName(){
		IBookDao bd = new BookDao();
		List<Book> list = bd.findBookByName("英语");
		for (Book book : list) {
			System.out.println(book.getName());
		}
	}
	
	@Test
	public void testCount(){
		IBookDao bd = new BookDao();
		System.out.println(bd.countBookByName("英语"));
	}
}
