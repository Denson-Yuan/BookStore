package com.whut.service;

import java.util.List;

import com.whut.model.CartItem;

public interface ICartItemService {
	
	public int addCartItem(CartItem ci);
	
	public int deleteCartItemById(Integer id);
	
	public List<CartItem> findCartItemByUserId(Integer userId);
	
	public int updateCartItemNum(Integer id, int num);
}
