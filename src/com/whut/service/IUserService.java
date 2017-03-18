package com.whut.service;

import java.util.List;

import com.whut.model.User;

public interface IUserService {
	
	public int addUser(User user);
	
	public void updateUser(User user);
	
	public List<User> showAllUser();
	
	public User findUserByEmail(String email);
	
	public User findManagerByEmail(String email);

}
