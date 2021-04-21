package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import org.springframework.jdbc.core.RowMapper;


import com.domain.Message;
import com.domain.Order;
import com.google.gson.Gson;

public class Order_Controller implements ServletResponseAware, ServletRequestAware {
	private String customer_Num;
	private String message;
	private ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext.xml");
	private Order order;
	private List<Order> result;
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

	public ApplicationContext getFactory() {
		return factory;
	}

	public void setFactory(ApplicationContext factory) {
		this.factory = factory;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<Order> getResult() {
		return result;
	}

	public void setResult(List<Order> result) {
		this.result = result;
	}

	
	// 訂單新增系統
	public String OrderAdd() {
		if (this.order != null) {
		JdbcOperations dao = factory.getBean("dao", JdbcOperations.class);
		int r = dao.update(
				"INSERT INTO customerorder(cus_Id,mat_Id,sales_Amount) "
				+ " VALUES(?,?,?)",
				// 建構匿名類別 實作PreparedStatementSetter
				new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement pre) throws SQLException {
						// 設定參數內容
						pre.setInt(1, order.getCus_Id());
						pre.setInt(2, order.getMat_Id());
						pre.setInt(3, order.getSales_Amount());
					}
				});
		if (r > 0) {
			message = String.format("新增訂單筆數:%d",r) ;
		}
		return "success";
		
		}else{
			return "ok";
		}
	}
	
	public String orderQry() {
			try {
			JdbcOperations dao = factory.getBean("dao",JdbcOperations.class);
			String sql = "SELECT "
					+ "o.order_Id,"
					+ "o.cus_Id,"
					+ "c.customer_Name,"
					+ "c.customer_Num,"
					+ "o.mat_Id,"
					+ "m.material_Name,"
					+ "m.material_Type,"
					+ "c.customer_Addres,"
					+ "o.sales_Amount "
					+ " FROM "
					+ "  customerorder o, "
					+ "  customer c,"
					+ "  material m"
					+ " WHERE "
					+ "  c.customer_Num = ? AND c.customer_Id = o.cus_Id AND m.material_Id = o.mat_Id";
			
			result = dao.query(sql,new Object[] {customer_Num},
					new BeanPropertyRowMapper<Order>(Order.class));
			
			if(result.size() == 0){
				message = String.format("查詢訂單資料:%s 查無資料!!!", customer_Num);
				return "success";
				}
			}catch(Exception ex) {
				message = String.format("錯誤訊息:%s",ex.getMessage());
			}
			return "ok";
	}
	//AJAX 在使用訂單查詢時，檢查客戶電話是否正確。
	public void cusnum_Validate() {
		try {
		//擷取從OrderQry 傳進來的客戶編號
			
		String cusnum = request.getParameter("cusnum");
		//處理好如何回訊息給前端???
		PrintWriter out;
		Integer result = 0;
		//建構自訂訊息物件
		Message msg = new Message();
		try {		
		JdbcOperations dao = factory.getBean("dao",JdbcOperations.class);
		result = dao.queryForObject("SELECT customer_Num FROM customer WHERE customer_Num = ? ",
				new Object[] {cusnum},
				//查詢結果callback處理
				new RowMapper<Integer>(){
			//mapRow per Row(處理相對記錄,不要針對ResultSet.next() 錯誤寫法)
			public Integer mapRow(ResultSet rs,int arg1) throws SQLException {
				//相對列進行處理(一定有記錄 客戶電話重複)
				return 1;			
			};
		});
		
		}catch(Exception ex) {
		}
		if(result == 1) {
			msg.setCode("200");
			msg.setInformation("正確的電話號碼");
		}else {
			msg.setCode("200");
			msg.setInformation("無此電話號碼"); 		
		}
		out = response.getWriter();
		
		//將物件序列化成Json String 使用GSON
		
		Gson gson = new Gson(); 
		String jsonString = gson.toJson(msg);
		out.print(jsonString);	
		
		}catch(IOException e) {
			e.printStackTrace();
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
