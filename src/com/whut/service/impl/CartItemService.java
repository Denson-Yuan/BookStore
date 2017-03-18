package com.whut.service.impl;

import java.util.List;

import com.whut.dao.ICartItemDao;
import com.whut.dao.impl.CartItemDao;
import com.whut.model.CartItem;
import com.whut.service.ICartItemService;

public class CartItemService implements ICartItemService {

	private ICartItemDao cartItemDao = new CartItemDao();

	@Override
	public int addCartItem(CartItem ci) {
		int userId = ci.getUser().getId();
		int bookId = ci.getBook().getId();
		CartItem cartItem = cartItemDao.findCartItemByUserIdAndBookId(userId, bookId);
		if (cartItem == null) {
			return cartItemDao.addCartItem(ci);
		} else {
			int curNum = cartItem.getAmount();
			return cartItemDao.updateItemNum(cartItem.getId(), curNum + 1);
		}
	}

	@Override
	public int deleteCartItemById(Integer id) {
		return cartItemDao.deleteCartItemById(id);
	}

	@Override
	public List<CartItem> findCartItemByUserId(Integer userId) {
		return cartItemDao.findCartItemByUserId(userId);
	}

	@Override
	public int updateCartItemNum(Integer id,int num) {
		return cartItemDao.updateItemNum(id, num);
	}

}
