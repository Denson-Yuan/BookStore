package com.whut.dao;

import java.util.List;

import com.whut.model.Book;

public interface IBookDao {
	public int addBook(Book book);

	public int deleteBookById(Integer id);

	public int updateBook(Book book);

	public Book findBookById(Integer id);

	public List<Book> findAllBook();

	public List<Book> findBookByType(Integer typeId, int pageBegin, int pageSize);
	
	public int countBookByType(Integer typeId);

	public List<Book> findBookByName(String name);
	
	public int countBookByName(String name);
}
