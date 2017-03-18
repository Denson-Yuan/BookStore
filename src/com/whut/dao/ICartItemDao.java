package com.whut.dao;

import java.util.List;

import com.whut.model.CartItem;

public interface ICartItemDao {
	
	public int addCartItem(CartItem ci);
	
	public int deleteCartItemById(Integer id);
	
	public List<CartItem> findCartItemByUserId(Integer userId);
	
	public CartItem findCartItemByUserIdAndBookId(Integer userId,Integer bookId);
	
	public int updateItemNum(Integer id,Integer num);
}
