package com.controller;

import com.domain.User;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;


import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcOperations;

public class Register_Controller implements ServletResponseAware {
	// Attribute 配合註冊表單欄位name="xxxx"
	private String username;
	private String userpassword;
	private String useremail;
	// BeanFactory 介面工廠
	private ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext.xml");
	private String message; // 處理之後的訊息狀態
	// 登入會員資訊物件
	private User user;
	private HttpServletResponse response;
	private HttpServletRequest request;
	
	
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return action沒有指定方法名稱 就會這一個預設的
	 */
	public String execute() {
		// TODO Auto-generated method stub
		return "success";
	}

	public String loginForm() throws ServletException, IOException {
		// 提供表單 也進行驗證作業
		if (this.user == null) {
			// 第一次登入作業
			//第一次登入的request.getCookies is null
			return "success";
			
		} else {
			// 驗證是否合法帳號
			// 工廠要DAO物件
			JdbcOperations dao = factory.getBean("dao", JdbcOperations.class);
			// 進行查詢
			//!COUNT() 函數用來計算符合查詢條件的欄位紀錄總共有幾筆。
			//count(欄位名) as 欄位別名  from"資料表" 
			//as   可理解為：用作、當成，作為；一般是重新命名列名或者表名。
			String sql = "select count(*) as counter from user where username=? and userpassword=?";
			// 單筆查詢 透過第三個參數查詢結果指定轉型
			Integer i = dao.queryForObject(sql, new Object[] { user.getUsername(), user.getUserpassword() },
					Integer.class);
			message = String.valueOf(i) ;
			// Unboxing 打開物件取出那一個整數值
			if (i > 0) {
				// 發出前端的Cookie(憑證)
				
				//目前 filter 擷取不到 register_Controller 傳過去的cookie
				//login.aciton 伺服器端 新增cookie，filter 透過 rep 取得 request的實體  
				Cookie cred = new Cookie(".appcred", user.getUsername());
				cred.setMaxAge(3600);
				response.addCookie(cred);	
							
				message = String.format("驗證通過:%s",user.getUsername()) ;	
				System.out.println(cred.getName()+":"+cred.getValue());
				
			} else {
				message = "驗證失敗";
				return "error";
			}
			return "ok";
		}
	}

	// 進行會員註冊
	public String registerSave() {
		// 介面操作
		// 1.擷取表單欄位內容 借助valuestack 表單欄位注入到attribute
		// 藉助struts2才能完成的動作
		try {
			JdbcOperations dao = factory.getBean("dao", JdbcOperations.class);
			// 進行新增作業
			String sql = "insert into user(username,userpassword,useremail) values(?,?,?)";
			int affect = dao.update(sql,this.username,this.userpassword,this.useremail);
			message = String.format("新增記錄數:%d", affect);

			return "success";
		} catch (Exception ex) {
			// 維護上出現問題
			message = String.format("新增記錄失敗，訊息:%s", ex.getMessage());
			return "failure";
		}
	}	
	
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
		response.setCharacterEncoding("UTF-8");	
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
		
	}
}
