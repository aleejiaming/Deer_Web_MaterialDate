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
	// Attribute �t�X���U������name="xxxx"
	private String username;
	private String userpassword;
	private String useremail;
	// BeanFactory �����u�t
	private ApplicationContext factory = new ClassPathXmlApplicationContext("applicationContext.xml");
	private String message; // �B�z���᪺�T�����A
	// �n�J�|����T����
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
	 * @return action�S�����w��k�W�� �N�|�o�@�ӹw�]��
	 */
	public String execute() {
		// TODO Auto-generated method stub
		return "success";
	}

	public String loginForm() throws ServletException, IOException {
		// ���Ѫ�� �]�i�����ҧ@�~
		if (this.user == null) {
			// �Ĥ@���n�J�@�~
			//�Ĥ@���n�J��request.getCookies is null
			return "success";
			
		} else {
			// ���ҬO�_�X�k�b��
			// �u�t�nDAO����
			JdbcOperations dao = factory.getBean("dao", JdbcOperations.class);
			// �i��d��
			//!COUNT() ��ƥΨӭp��ŦX�d�߱����������`�@���X���C
			//count(���W) as ���O�W  from"��ƪ�" 
			//as   �i�z�Ѭ��G�Χ@�B���A�@���F�@��O���s�R�W�C�W�Ϊ̪�W�C
			String sql = "select count(*) as counter from user where username=? and userpassword=?";
			// �浧�d�� �z�L�ĤT�ӰѼƬd�ߵ��G���w�૬
			Integer i = dao.queryForObject(sql, new Object[] { user.getUsername(), user.getUserpassword() },
					Integer.class);
			message = String.valueOf(i) ;
			// Unboxing ���}������X���@�Ӿ�ƭ�
			if (i > 0) {
				// �o�X�e�ݪ�Cookie(����)
				
				//�ثe filter �^������ register_Controller �ǹL�h��cookie
				//login.aciton ���A���� �s�Wcookie�Afilter �z�L rep ���o request������  
				Cookie cred = new Cookie(".appcred", user.getUsername());
				cred.setMaxAge(3600);
				response.addCookie(cred);	
							
				message = String.format("���ҳq�L:%s",user.getUsername()) ;	
				System.out.println(cred.getName()+":"+cred.getValue());
				
			} else {
				message = "���ҥ���";
				return "error";
			}
			return "ok";
		}
	}

	// �i��|�����U
	public String registerSave() {
		// �����ާ@
		// 1.�^�������줺�e �ɧUvaluestack ������`�J��attribute
		// �ǧUstruts2�~�৹�����ʧ@
		try {
			JdbcOperations dao = factory.getBean("dao", JdbcOperations.class);
			// �i��s�W�@�~
			String sql = "insert into user(username,userpassword,useremail) values(?,?,?)";
			int affect = dao.update(sql,this.username,this.userpassword,this.useremail);
			message = String.format("�s�W�O����:%d", affect);

			return "success";
		} catch (Exception ex) {
			// ���@�W�X�{���D
			message = String.format("�s�W�O�����ѡA�T��:%s", ex.getMessage());
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
