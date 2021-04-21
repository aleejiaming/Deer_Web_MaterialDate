package com.controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementSetter;
import com.domain.Material;

public class Material_Store_Controller {

	private ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext.xml");
	private String material_Type;
	private String message;
	private Material material;
	private List<Material> result;
	private HttpServletResponse response;
	private HttpServletRequest request;

	public List<Material> getResult() {
		return result;
	}

	public void setResult(List<Material> result) {
		this.result = result;
	}

	public String getMaterial_Type() {
		return material_Type;
	}

	public void setMaterial_Type(String material_Type) {
		this.material_Type = material_Type;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

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

	public String MaterialStoreQry() {
		try {
			if (this.material != null && this.material.getMaterial_Name().length() > 0) {

				JdbcOperations dao = factory.getBean("dao", JdbcOperations.class);
				String sql = "SELECT material_Id,material_Name,material_Amount,material_Type"
						+ " FROM material WHERE material_Type = ? ";
				result = dao.query(sql, new Object[] { material_Type },
						new BeanPropertyRowMapper<Material>(Material.class));

				// 查詢結果
				if (result.size() == 0) {
					message = String.format("查詢庫存資料:%s 查無記錄!!!", material_Type);
					return "success";
				}
			} 
		} catch (Exception ex) {
			message = String.format("查詢資料失敗，訊息:%s", ex.getMessage());
		}
		
		return "ok";
		
	}

	public String MaterialStoreAdd() {
		if (this.material != null && this.material.getMaterial_Name().length() > 0) {
			JdbcOperations dao = factory.getBean("dao", JdbcOperations.class);
			int i = dao.update("INSERT INTO material (product_Name,product_Amount,product_Type) " + "VALUE (?,?,?)",
					new PreparedStatementSetter() {

						@Override
						public void setValues(PreparedStatement pre) throws SQLException {
							// 設定參數內容
							pre.setString(1, material.getMaterial_Name());
							pre.setInt(2, material.getMaterial_Amount());
							pre.setString(3, material.getMaterial_Type());
						}
					});

			if (i > 0) { 
				message = String.format("新增資料筆數:%d", i);
			}
			return "ok";
			
			}else{
				return "success";
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
