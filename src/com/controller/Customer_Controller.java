package com.controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.domain.Customer;


//針對材料查詢控制器Controller
public class Customer_Controller implements ServletResponseAware,ServletRequestAware {
	// attribute
	private String customer_Num;
	private String message;
	private ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext.xml");
	private List<Customer> result;
	private Customer customer;
	private HttpServletResponse response;
	private HttpServletRequest request;

	
	
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String getCustomer_Num() {
		return customer_Num;
	}
	public void setCustomer_Num(String customer_Num) {
		this.customer_Num = customer_Num;
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Customer> getResult() {
		return result;
	}

	public void setResult(List<Customer> result) {
		this.result = result;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	// 預設方法
	public String execute() {
		return "success";
	}

	// 客戶名子查詢與材料訂單調用表單action
	public String CustomerQry() {
		// 第一次請求 調用表單
		// 還沒有查詢過 表示this.customer == null
			try {
			// 透過Spring Factory要一個 DAO(正轉)
			JdbcOperations dao = factory.getBean("dao", JdbcOperations.class);
			// 客人的訂單
			// 進行查詢
			String sql = "SELECT * FROM customer WHERE customer_Num = ?";
			
			result = dao.query(sql, new Object[] { customer_Num }, new BeanPropertyRowMapper<Customer>(Customer.class));
			if (result.size() == 0) { 
				message = String.format("查詢客人資料:%s 查無記錄!!!", customer_Num);
			}
			// 查詢	
			}catch(Exception ex) {
				message = String.format("查詢資料失敗，訊息:%s", ex.getMessage());
			}
			return "success";
	}

	public String customerAdd() {
		//解釋 if 的作用
		if (this.customer != null && this.customer.getCustomer_Name().length() > 0) {
			// 新增作業
			// 1.透過工廠要DAO物件
			JdbcOperations dao = factory.getBean("dao", JdbcOperations.class);
			int r = dao.update(
					"insert into customer(customer_Name,customer_Num,customer_Email,customer_Addres,customer_City,create_Date) values(?,?,?,?,?,?)",
					// 建構匿名類別 實作PreparedStatementSetter
					new PreparedStatementSetter() {

						@Override
						public void setValues(PreparedStatement pre) throws SQLException {
							// 設定參數內容							
							pre.setString(1, customer.getCustomer_Name());
							pre.setString(2, customer.getCustomer_Num());
							pre.setString(3, customer.getCustomer_Email());
							pre.setString(4, customer.getCustomer_Addres());
							pre.setString(5, customer.getCustomer_City());
							pre.setString(6, customer.getCreate_Date());
						}
					});
			if (r > 0) {
				message = "新增紀錄成功";
			}
			return "ok";
		} else {
			return "success"; // 第一次請求調用表單
		}
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		response.setCharacterEncoding("UTF-8");
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
}
