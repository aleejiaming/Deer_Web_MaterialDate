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


//�w����Ƭd�߱��Controller
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
	
	
	// �w�]��k
	public String execute() {
		return "success";
	}

	// �Ȥ�W�l�d�߻P���ƭq��եΪ��action
	public String CustomerQry() {
		// �Ĥ@���ШD �եΪ��
		// �٨S���d�߹L ���this.customer == null
			try {
			// �z�LSpring Factory�n�@�� DAO(����)
			JdbcOperations dao = factory.getBean("dao", JdbcOperations.class);
			// �ȤH���q��
			// �i��d��
			String sql = "SELECT * FROM customer WHERE customer_Num = ?";
			
			result = dao.query(sql, new Object[] { customer_Num }, new BeanPropertyRowMapper<Customer>(Customer.class));
			if (result.size() == 0) { 
				message = String.format("�d�߫ȤH���:%s �d�L�O��!!!", customer_Num);
			}
			// �d��	
			}catch(Exception ex) {
				message = String.format("�d�߸�ƥ��ѡA�T��:%s", ex.getMessage());
			}
			return "success";
	}

	public String customerAdd() {
		//���� if ���@��
		if (this.customer != null && this.customer.getCustomer_Name().length() > 0) {
			// �s�W�@�~
			// 1.�z�L�u�t�nDAO����
			JdbcOperations dao = factory.getBean("dao", JdbcOperations.class);
			int r = dao.update(
					"insert into customer(customer_Name,customer_Num,customer_Email,customer_Addres,customer_City,create_Date) values(?,?,?,?,?,?)",
					// �غc�ΦW���O ��@PreparedStatementSetter
					new PreparedStatementSetter() {

						@Override
						public void setValues(PreparedStatement pre) throws SQLException {
							// �]�w�ѼƤ��e							
							pre.setString(1, customer.getCustomer_Name());
							pre.setString(2, customer.getCustomer_Num());
							pre.setString(3, customer.getCustomer_Email());
							pre.setString(4, customer.getCustomer_Addres());
							pre.setString(5, customer.getCustomer_City());
							pre.setString(6, customer.getCreate_Date());
						}
					});
			if (r > 0) {
				message = "�s�W�������\";
			}
			return "ok";
		} else {
			return "success"; // �Ĥ@���ШD�եΪ��
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
