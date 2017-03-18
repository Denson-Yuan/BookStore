package com.whut.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.whut.model.Book;
import com.whut.model.CartItem;
import com.whut.model.User;
import com.whut.service.ICartItemService;
import com.whut.service.impl.CartItemService;

@SuppressWarnings("serial")
public class CartAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
	
	private HttpServletRequest request;
	
	private Map<String, Object> dataMap = new HashMap<>();

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}
	
	@Override
	public void setServletResponse(HttpServletResponse response) {
		
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	ICartItemService cartItemService = new CartItemService();
	
	public String show(){
		return "cart";
	}
	
	public String addToCart(){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user==null){
			dataMap.put("isSuccess", false);
			dataMap.put("msg","请先登录！");
		}else{
			String id = request.getParameter("bookId");
			System.out.println(id);
			int bookId = Integer.parseInt(request.getParameter("bookId"));
			Book book = new Book();
			book.setId(bookId);
			
			CartItem ci = new CartItem();
			ci.setUser(user);
			ci.setBook(book);
			ci.setAmount(1);
			cartItemService.addCartItem(ci);
			
			dataMap.clear();
			dataMap.put("isSuccess", true);
			dataMap.put("msg","添加成功！");
		}
		return SUCCESS;
	}
	
	public String getTotalPriceAndNum(){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null){
			dataMap.clear();
			dataMap.put("totalPrice", 0);
			dataMap.put("totalNum", 0);
		}else{
			List<CartItem> list = cartItemService.findCartItemByUserId(user.getId());
			int totalNum = 0;
			double totalPrice = 0;
			for (CartItem cartItem : list) {
				int curNum = cartItem.getAmount();
				double curPrice = cartItem.getAmount() * cartItem.getBook().getPrice();
				totalNum += curNum;
				totalPrice += curPrice;
			}
			dataMap.clear();
			dataMap.put("totalNum", totalNum);
			dataMap.put("totalPrice", totalPrice);
		}
		return SUCCESS;
	}
	
	public String getCartItem(){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null){
			dataMap.clear();
			dataMap.put("isSuccess", false);
			dataMap.put("msg", "您还未登录，请登录后查看.");
		}else {
			List<CartItem> list = cartItemService.findCartItemByUserId(user.getId());
			dataMap.clear();
			dataMap.put("isSuccess", true);
			dataMap.put("data", list);
		}
		return SUCCESS;
	}
	
	public String deleteCartItem(){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null){
			dataMap.clear();
			dataMap.put("isSuccess", false);
			dataMap.put("msg", "登录已超时，请重新登录！");
		}else {
			int id = Integer.parseInt(request.getParameter("cartItemId"));
			cartItemService.deleteCartItemById(id);
			dataMap.clear();
			dataMap.put("isSuccess",true);
		}
		return SUCCESS;
	}
	
	public String updateCartItemNum(){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null){
			dataMap.clear();
			dataMap.put("isSuccess", false);
			dataMap.put("msg", "登录已超时，请重新登录！");
		}else {
			int id = Integer.parseInt(request.getParameter("cartItemId"));
			int num = Integer.parseInt(request.getParameter("num"));
			cartItemService.updateCartItemNum(id, num);
			dataMap.clear();
			dataMap.put("isSuccess",true);
		}
		return SUCCESS;
	}
	
	public String checkout(){
		return "placeorder";
	}
}
