package com.whut.test;

import java.util.List;

import org.junit.Test;

import com.whut.dao.ICartItemDao;
import com.whut.dao.impl.BookDao;
import com.whut.dao.impl.CartItemDao;
import com.whut.dao.impl.UserDao;
import com.whut.model.Book;
import com.whut.model.CartItem;
import com.whut.model.User;

public class TestCartItem {

	@Test
	public void testAdd(){
		ICartItemDao dao = new CartItemDao();
		User user = new UserDao().findUserById(2);
		Book book = new BookDao().findBookById(2);
		
		CartItem ci = new CartItem();
		ci.setUser(user);
		ci.setBook(book);
		ci.setAmount(1);
		
		dao.addCartItem(ci);
	}
	
	@Test
	public void testDelete(){
		ICartItemDao dao = new CartItemDao();
		dao.deleteCartItemById(3);
	}
	
	@Test
	public void testFind(){
		ICartItemDao dao = new CartItemDao();
		List<CartItem> list = dao.findCartItemByUserId(2);
		for (CartItem cartItem : list) {
			System.out.println(cartItem.getBook().getId());
			System.out.println(cartItem.getBook().getName());
			System.out.println();
		}
	}
}
