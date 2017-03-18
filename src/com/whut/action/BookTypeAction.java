package com.whut.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.whut.model.BookType;
import com.whut.service.impl.BookService;

public class BookTypeAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BookService bookService = new BookService();
	private HashMap<String, Object> dataMap = new HashMap<String, Object>();

	public HashMap<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(HashMap<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public String getBookTypes() {
		List<BookType> types = bookService.findAllBookTypes();

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		for (BookType type : types) {
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("id", type.getId());
			hashMap.put("typeName", type.getTypeName());
			list.add(hashMap);
		}
		dataMap.put("rows", list);
		dataMap.put("total", list.size());
		return SUCCESS;
	}

	public void addBookType() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String type = request.getParameter("typeName");
		BookType bookType = new BookType();
		if (type != null) {
			bookType.setTypeName(type);
		}
		try {
			bookService.addBookType(bookType);

		} catch (Exception ex) {

		}
	}

	public void updateBookType() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String type = request.getParameter("typeName");
		String idStr = request.getParameter("id");
		BookType bookType = new BookType();
		bookType.setTypeName(type);
		if (idStr != null) {
			bookType.setId(Integer.parseInt(idStr));
		}
		try {
			bookService.updateBookType(bookType);

		} catch (Exception ex) {

		}
	}

}
