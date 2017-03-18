package com.whut.service.impl;

import java.util.List;

import com.whut.dao.IUserDao;
import com.whut.dao.impl.UserDao;
import com.whut.model.User;
import com.whut.service.IUserService;

public class UserService implements IUserService {

	private IUserDao userDao = new UserDao();

	

	@Override
	public List<User> showAllUser() {
		List<User> list = userDao.findAllUser();
		return list;

	}

	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	@Override
	public User findUserByEmail(String email) {
		return userDao.findUserByEmail(email);
	}

	@Override
	public int addUser(User user) {
		return userDao.addUser(user);
	}

	@Override
	public User findManagerByEmail(String email) {
		return userDao.findManagerByEmail(email);
	}

}
