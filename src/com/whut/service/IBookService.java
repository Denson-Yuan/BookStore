package com.whut.service;

import java.util.List;

import com.whut.model.Book;
import com.whut.model.BookType;
import com.whut.utils.Page;

public interface IBookService {
	public List<BookType> addBookType(BookType bookType);

	public List<BookType> updateBookType(BookType bookType);

	public List<Book> addBook(Book book);

	public List<Book> updateBook(Book book);

	public List<Book> showAllBook();

	public Page<Book> showBookByType(int typeId, int pageNum, int pageSize);

	public List<Book> showBookByName(String name);

	public Book showBookById(int id);
	
	public List<BookType> findAllBookTypes();
	
	public BookType findBookTypeById(Integer id);
	
	public BookType showBookTypeByName(String typeName);
}
