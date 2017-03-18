package com.whut.service.impl;

import java.util.List;

import com.whut.dao.impl.BookDao;
import com.whut.dao.impl.BookTypeDao;
import com.whut.model.Book;
import com.whut.model.BookType;
import com.whut.service.IBookService;
import com.whut.utils.Page;

public class BookService implements IBookService {
	
	private BookDao bookDao = new BookDao();
	private BookTypeDao bookTypeDao = new BookTypeDao();

	@Override
	public List<BookType> addBookType(BookType bookType) {
		bookTypeDao.addBookType(bookType);
		List<BookType> list = bookTypeDao.findAllBookType();
		return list;
	}

	@Override
	public List<BookType> updateBookType(BookType bookType) {
		bookTypeDao.updateBookType(bookType);
		List<BookType> list = bookTypeDao.findAllBookType();
		return list;
	}

	@Override
	public List<Book> addBook(Book book) {
		bookDao.addBook(book);
		List<Book> list = bookDao.findAllBook();
		return list;
	}

	@Override
	public List<Book> updateBook(Book book) {
		bookDao.updateBook(book);
		List<Book> list = bookDao.findAllBook();
		return list;
	}

	@Override
	public List<Book> showAllBook() {
		return bookDao.findAllBook();
	}

	@Override
	public Page<Book> showBookByType(int typeId, int pageNum, int pageSize) {
		int pageBegin = (pageNum - 1) * pageSize;
		List<Book> list = bookDao.findBookByType(typeId,pageBegin,pageSize);
		int totalRecord = bookDao.countBookByType(typeId);
		int totalPage = totalRecord / pageSize;
		if(totalRecord % pageSize != 0){
			totalPage++;
		}
		return new Page<>(pageNum,totalPage,list);

	}

	@Override
	public List<Book> showBookByName(String name) {
		// TODO Auto-generated method stub
		List<Book> list = bookDao.findBookByName(name);
		return list;
	}

	@Override
	public Book showBookById(int id) {
		// TODO Auto-generated method stub
		return bookDao.findBookById(id);
	}

	@Override
	public List<BookType> findAllBookTypes() {
		return bookTypeDao.findAllBookType();
	}

	@Override
	public BookType findBookTypeById(Integer id) {
		return bookTypeDao.findBookTypeById(id);
	}

	@Override
	public BookType showBookTypeByName(String typeName){
		return bookTypeDao.findBookTypeByName(typeName);
	}
}
