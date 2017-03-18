package com.whut.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.whut.model.Book;
import com.whut.model.BookType;
import com.whut.service.impl.BookService;
import com.whut.utils.Page;

@SuppressWarnings("serial")
public class BookAction extends ActionSupport {

	private Map<String, Object> dataMap = new HashMap<>();

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	private BookService bookService = new BookService();

	public String showAllBook() {
		List<Book> list = bookService.showAllBook();
		ServletActionContext.getRequest().setAttribute("list", list);
		return SUCCESS;
	}

	public String getBooksByType() {
		HttpServletRequest request = ServletActionContext.getRequest();
		int typeId = Integer.parseInt(request.getParameter("typeId"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		Page<Book> page = bookService.showBookByType(typeId, pageNum, pageSize);
		dataMap.clear();
		dataMap.put("data", page);
		return SUCCESS;
	}

	public String showBookByName() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String name = request.getParameter("name");
		List<Book> list = bookService.showBookByName(name);
		request.setAttribute("list", list);
		return SUCCESS;
	}

	public String getBookTypes() {
		List<BookType> bookTypeList = bookService.findAllBookTypes();
		dataMap.clear();
		dataMap.put("bookTypeList", bookTypeList);
		return SUCCESS;
	}

	public String setBookType() {
		HttpServletRequest request = ServletActionContext.getRequest();
		int typeId = Integer.parseInt(request.getParameter("typeId"));
		BookType bt = bookService.findBookTypeById(typeId);
		request.setAttribute("typeId", typeId);
		request.setAttribute("typeName", bt.getTypeName());
		return "type";
	}
	
	public String bookDetail(){
		HttpServletRequest request = ServletActionContext.getRequest();
		int bookId = Integer.parseInt(request.getParameter("bookId"));
		Book book = bookService.showBookById(bookId);
		request.setAttribute("book", book);
		return "detail";
	}

	public String getBooks() {
		System.out.println(123);
		List<Book> books = bookService.showAllBook();

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		for (Book book : books) {
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("id", book.getId());
			hashMap.put("typeId", book.getType().getId());
			hashMap.put("typeName", book.getType().getTypeName());
			hashMap.put("isbn", book.getIsbn());
			hashMap.put("name", book.getName());
			hashMap.put("author", book.getAuthor());
			hashMap.put("press", book.getPress());
			hashMap.put("publishTime", book.getPublishTime());
			hashMap.put("price", book.getPrice());
			hashMap.put("amount", book.getAmount());
			hashMap.put("imgPath", book.getImgPath());
			hashMap.put("introduction", book.getIntroduction());

			list.add(hashMap);
		}
		dataMap.put("rows", list);
		dataMap.put("total", list.size());
		return SUCCESS;
	}

	public void addBook() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Book book = new Book();
		book.setIsbn(request.getParameter("isbn"));
		book.setName(request.getParameter("name"));
		book.setAuthor(request.getParameter("isbn"));
		String type = request.getParameter("typeId");
		String price = request.getParameter("price");
		String amount = request.getParameter("amount");
		BookType bookType = new BookType();
		if (type != null) {
			bookType.setId(Integer.parseInt(type));
		}
		book.setType(bookType);
		book.setPress(request.getParameter("press"));
		book.setPublishTime(request.getParameter("publishTime"));
		if (price != null) {
			book.setPrice(Float.parseFloat(price));
		}
		if (amount != null) {
			book.setAmount(Integer.parseInt(amount));
		}
		book.setImgPath(request.getParameter("imaPath"));
		book.setIntroduction(request.getParameter("introduction"));
		try {
			bookService.addBook(book);

		} catch (Exception ex) {

		}
	}

	public void updateBook() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Book book = new Book();
		BookType bookType = new BookType();
		String idStr = request.getParameter("id");
		if (idStr != null) {
			book.setId(Integer.parseInt(idStr));
		}
		book.setIsbn(request.getParameter("isbn"));
		book.setName(request.getParameter("name"));
		book.setAuthor(request.getParameter("isbn"));
		String type = request.getParameter("typeId");

		if (!type.matches("[0-9]+")) {
			bookType = bookService.showBookTypeByName(type);
		} else {
			bookType.setId(Integer.parseInt(type));
		}
		book.setType(bookType);
		String price = request.getParameter("price");
		String amount = request.getParameter("amount");
		book.setPress(request.getParameter("press"));
		book.setPublishTime(request.getParameter("publishTime"));
		if (price != null) {
			book.setPrice(Float.parseFloat(price));
		}
		if (amount != null) {
			book.setAmount(Integer.parseInt(amount));
		}
		book.setImgPath(request.getParameter("imaPath"));
		book.setIntroduction(request.getParameter("introduction"));
		try {
			bookService.updateBook(book);

		} catch (Exception ex) {

		}
	}
}
