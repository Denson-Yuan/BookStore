package com.whut.test;

import org.junit.Test;

import com.whut.dao.IUserDao;
import com.whut.dao.impl.UserDao;

public class TestUser {
	@Test
	public void testFindByEmail(){
		IUserDao ud = new UserDao();
		System.out.println(ud.findUserByEmail("105@qq.com").getName());
	}
}
