package com.whut.dao;

import java.util.List;

import com.whut.model.BookType;

public interface IBookTypeDao {
	public int addBookType(BookType bt);
	
	public int deleteBookTypeById(Integer id);
	
	public int updateBookType(BookType bt);
	
	public List<BookType> findAllBookType();
	
	public BookType findBookTypeById(Integer id);
	
	public BookType findBookTypeByName(String typeName);
}
