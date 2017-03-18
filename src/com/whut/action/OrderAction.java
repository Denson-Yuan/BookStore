package com.whut.action;

import java.time.LocalDateTime;
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
import com.whut.model.Order;
import com.whut.model.OrderDetail;
import com.whut.model.User;
import com.whut.service.IOrderService;
import com.whut.service.impl.OrderService;
import com.whut.utils.Constant;
import com.whut.utils.Page;

@SuppressWarnings("serial")
public class OrderAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

	private HttpServletRequest request;

	private Map<String, Object> dataMap = new HashMap<>();

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

	IOrderService orderService = new OrderService();
	
	public String findOrdersByUser(){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null){
			dataMap.clear();
			dataMap.put("isSuccess", false);
			dataMap.put("msg", "登录已超时，请重新登录！");
		}else {
			int pageNum = Integer.parseInt(request.getParameter("pageNum"));
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			Page<Order> page = orderService.findOrdersByUserId(user.getId(), pageNum, pageSize);
			dataMap.clear();
			dataMap.put("isSuccess", true);
			dataMap.put("data", page);
		}
		return SUCCESS;
	}
	
	public String findOrderDetailsByOrderId(){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null){
			dataMap.clear();
			dataMap.put("isSuccess", false);
			dataMap.put("msg", "登录已超时，请重新登录！");
		}else {
			int orderId = Integer.parseInt(request.getParameter("orderId"));
			List<OrderDetail> listOrderDetail = orderService.findOrderDetailByOrderId(orderId);
			dataMap.clear();
			dataMap.put("isSuccess", true);
			dataMap.put("data", listOrderDetail);
		}
		return SUCCESS;
	}
	
	
	public String getUserInfo(){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null){
			dataMap.clear();
			dataMap.put("isSuccess", false);
			dataMap.put("msg", "登录已超时，请重新登录！");
		}else {
			dataMap.clear();
			dataMap.put("isSuccess", true);
			dataMap.put("user", user);
		}
		return SUCCESS;
	}
	
	public String submit(){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null){
			dataMap.clear();
			dataMap.put("isSuccess", false);
			dataMap.put("msg", "登录已超时，请重新登录！");
		}else {
			String reciver = request.getParameter("reciver");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			String remark = request.getParameter("remark");
			Order order = new Order();
			order.setUser(user);
			order.setOrderTime(LocalDateTime.now().toString());
			order.setOrderState(Constant.ORDER_STATE_UNFINISHED);
			order.setRemark(remark);
			order.setReciver(reciver);
			order.setPhone(phone);
			order.setAddress(address);
			
			orderService.addOrder(order);
			dataMap.clear();
			dataMap.put("isSuccess", true);
			dataMap.put("msg","订单已提交！");
		}
		return SUCCESS;
	}
	
	public String getOrderDetails(){ 
        List<OrderDetail> ods = orderService.findAllOrderDetails();      
        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();  
        for (OrderDetail od :ods) {  
              HashMap<String, Object> hashMap = new HashMap<String, Object>();  
              hashMap.put("orderId",od.getOrder().getId());  
              hashMap.put("userName",od.getOrder().getUser().getName());
              hashMap.put("email", od.getOrder().getUser().getEmail());
              hashMap.put("reciver", od.getOrder().getReciver());
              hashMap.put("phone", od.getOrder().getPhone());
              hashMap.put("address", od.getOrder().getAddress());
              hashMap.put("bookName",od.getBook().getName());
              hashMap.put("count",od.getCount());
              hashMap.put("price", od.getBook().getPrice());
              hashMap.put("orderTime",od.getOrder().getOrderTime());  
              hashMap.put("finishTime",od.getOrder().getFinishTime());
              int orderState=od.getOrder().getOrderState();
              String str="";
              if(orderState==1){
            	  str="未完成";
              }
              else if(orderState==2){
            	  str="取消";
              }
              else if(orderState==3){
            	  str="已完成";
              }
              hashMap.put("orderState",str);  
              hashMap.put("remark", od.getOrder().getRemark());
              list.add(hashMap);  
        }  
        dataMap.put("rows", list);
        dataMap.put("total", list.size());
        return SUCCESS;   
    }
	
	public String searchOrders(){
		HttpServletRequest request = ServletActionContext.getRequest();
		List<OrderDetail> ods = orderService.searchOrders(request.getParameter("state"));      
        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();  
        for (OrderDetail od :ods) {  
              HashMap<String, Object> hashMap = new HashMap<String, Object>();  
              hashMap.put("orderId",od.getOrder().getId());  
              hashMap.put("userName",od.getOrder().getUser().getName());
              hashMap.put("email", od.getOrder().getUser().getEmail());
              hashMap.put("reciver", od.getOrder().getReciver());
              hashMap.put("phone", od.getOrder().getPhone());
              hashMap.put("address", od.getOrder().getAddress());
              hashMap.put("bookName",od.getBook().getName());
              hashMap.put("count",od.getCount());
              hashMap.put("price", od.getBook().getPrice());
              hashMap.put("orderTime",od.getOrder().getOrderTime());  
              hashMap.put("finishTime",od.getOrder().getFinishTime());
              int orderState=od.getOrder().getOrderState();
              String str="";
              if(orderState==1){
            	  str="未完成";
              }
              else if(orderState==2){
            	  str="取消";
              }
              else if(orderState==3){
            	  str="已完成";
              }
              hashMap.put("orderState",str);  
              hashMap.put("remark", od.getOrder().getRemark());
              list.add(hashMap);  
        }  
        dataMap.put("rows", list);
        dataMap.put("total", list.size());
        return SUCCESS;   

	}
	//后台确认收货
	public String confirmOrder(){
		HttpServletRequest request = ServletActionContext.getRequest();
	    String order=request.getParameter("orderId");
	    String state=request.getParameter("orderState");
		Order or=new Order();
		or.setId(Integer.parseInt(order));
		if(state.equals("未完成")){
			 orderService.confirmOrder(or);
			 dataMap.put("isSuccess",true);
			 dataMap.put("msg", "确认成功");
        }
		else{
			dataMap.put("isSuccess",false);
			dataMap.put("msg", "订单已取消或完成，无法确认！");
		} 
        return SUCCESS;	
	}
	
}