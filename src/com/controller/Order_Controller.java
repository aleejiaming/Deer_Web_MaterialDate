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

	
	// �q��s�W�t��
	public String OrderAdd() {
		if (this.order != null) {
		JdbcOperations dao = factory.getBean("dao", JdbcOperations.class);
		int r = dao.update(
				"INSERT INTO customerorder(cus_Id,mat_Id,sales_Amount) "
				+ " VALUES(?,?,?)",
				// �غc�ΦW���O ��@PreparedStatementSetter
				new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement pre) throws SQLException {
						// �]�w�ѼƤ��e
						pre.setInt(1, order.getCus_Id());
						pre.setInt(2, order.getMat_Id());
						pre.setInt(3, order.getSales_Amount());
					}
				});
		if (r > 0) {
			message = String.format("�s�W�q�浧��:%d",r) ;
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
				message = String.format("�d�߭q����:%s �d�L���!!!", customer_Num);
				return "success";
				}
			}catch(Exception ex) {
				message = String.format("���~�T��:%s",ex.getMessage());
			}
			return "ok";
	}
	//AJAX �b�ϥέq��d�߮ɡA�ˬd�Ȥ�q�ܬO�_���T�C
	public void cusnum_Validate() {
		try {
		//�^���qOrderQry �Ƕi�Ӫ��Ȥ�s��
			
		String cusnum = request.getParameter("cusnum");
		//�B�z�n�p��^�T�����e��???
		PrintWriter out;
		Integer result = 0;
		//�غc�ۭq�T������
		Message msg = new Message();
		try {		
		JdbcOperations dao = factory.getBean("dao",JdbcOperations.class);
		result = dao.queryForObject("SELECT customer_Num FROM customer WHERE customer_Num = ? ",
				new Object[] {cusnum},
				//�d�ߵ��Gcallback�B�z
				new RowMapper<Integer>(){
			//mapRow per Row(�B�z�۹�O��,���n�w��ResultSet.next() ���~�g�k)
			public Integer mapRow(ResultSet rs,int arg1) throws SQLException {
				//�۹�C�i��B�z(�@�w���O�� �Ȥ�q�ܭ���)
				return 1;			
			};
		});
		
		}catch(Exception ex) {
		}
		if(result == 1) {
			msg.setCode("200");
			msg.setInformation("���T���q�ܸ��X");
		}else {
			msg.setCode("200");
			msg.setInformation("�L���q�ܸ��X"); 		
		}
		out = response.getWriter();
		
		//�N����ǦC�Ʀ�Json String �ϥ�GSON
		
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
