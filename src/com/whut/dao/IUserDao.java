package com.whut.dao;

import java.util.List;

import com.whut.model.User;

public interface IUserDao {
	public int addUser(User user);

	public int updateUser(User user);
    
	public int deleteUser(User user);
	
	public User findUserByEmail(String email);
	
	public boolean emailIsExist(String email);
	
	public List<User> findAllUser ();
	
	public User findUserById(Integer id);
	
	public User findManagerByEmail(String email);

}
