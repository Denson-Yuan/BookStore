package com.whut.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.whut.model.User;
import com.whut.service.IUserService;
import com.whut.service.impl.UserService;
import com.whut.utils.MD5;

@SuppressWarnings("serial")
public class UserAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

	private HttpServletRequest request;

	private User user;

	private Map<String, Object> dataMap = new HashMap<>();

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
	}

	private IUserService userService = new UserService();

	public String update() {
		userService.updateUser(user);
		HttpSession session = request.getSession();
		session.setAttribute("user", userService.findUserByEmail(user.getEmail()));
		return "person";
	}

	public String showAllUser() {
		List<User> users= userService.showAllUser();
	    List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();  
	    for (User user :users) {
	    	HashMap<String, Object> hashMap = new HashMap<String, Object>();  
	        hashMap.put("id",user.getId());  
	        hashMap.put("email",user.getEmail());
	        hashMap.put("name",user.getName());
	        if(user.getSex()==1){
	        	  hashMap.put("sex","女");
	        }
	        else if(user.getSex()==0){
	        	  hashMap.put("sex","男");
	        }
	        else{
	        	hashMap.put("sex",user.getSex());
	        }
	      
	        hashMap.put("phone",user.getPhone());
	        hashMap.put("address1",user.getAddress1());
	        hashMap.put("address2",user.getAddress2());
	        list.add(hashMap);  
	    }  
	    dataMap.put("rows", list);
	    dataMap.put("total", list.size()); 
	    return SUCCESS;   
	}

	public String showUser() {
		User userShow = userService.findUserByEmail(user.getEmail());
		ServletActionContext.getRequest().setAttribute("list", userShow);
		return SUCCESS;
	}

	public String login() {
		HttpSession session = request.getSession();
		MD5 md5 = new MD5();
		User currentUser = userService.findUserByEmail(user.getEmail());
		if (currentUser == null || !currentUser.getPassword().equals(md5.getMD5ofStr(user.getPassword()))) {
			dataMap.put("isSuccess", false);
			dataMap.put("msg", "用户名或密码错误");
		} else {
			session.setAttribute("user", currentUser);
			dataMap.put("isSuccess", true);
			dataMap.put("msg", "登录成功");
		}
		return SUCCESS;
	}

	public String logout() {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		return "logout";
	}
	
	public String toregister(){
		return "register";
	}
	

	public String register() {

		HttpSession session = request.getSession();
		MD5 md5 = new MD5();
		if (user != null) {
			User currentUser = userService.findUserByEmail(user.getEmail());
			if (currentUser != null) {
				dataMap.clear();
				dataMap.put("isSuccess", false);
				dataMap.put("msg", "该邮箱已被注册！");
			} else {
				user.setSex(0);;
				user.setPassword(md5.getMD5ofStr(user.getPassword()));
				userService.addUser(user);
				dataMap.clear();
				dataMap.put("isSuccess", true);
				dataMap.put("msg", "注册成功");
				session.setAttribute("user", user);
			}
		}
		return SUCCESS;
	}
	
	public String backLogin() {
		HttpSession session = request.getSession();
		String email=request.getParameter("userName");
		String password=request.getParameter("password");
		MD5 md5 = new MD5();
		User currentUser = userService.findManagerByEmail(email);
		if (currentUser == null || !currentUser.getPassword().equals(md5.getMD5ofStr(password))) {
			dataMap.put("isSuccess", false);
			dataMap.put("msg", "用户名或密码错误");
		} else {
			session.setAttribute("user", currentUser);
			dataMap.put("isSuccess", true);
			dataMap.put("msg", "登录成功");
		}
		return SUCCESS;
	}

	public String personal(){
		return "person";
	}
}