package com.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebFilter;

public class AuthorizationFilter implements Filter {
	
		
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// �d�I���S�����Ҫ�cookies����
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		// ���X�Ҧ���Cookie
		Cookie[] cookies = request.getCookies();
		//�ثe�ڧ䤣�� �z�LStruts �N Controller.loginForm()���ͪ�cookie��ƶǨ� filter
		//�ҥH�ثefilter�O��]�C
		boolean isValid = true; 		 	
		if (cookies != null) {
			
			// ���XCookie �P�_�O�_�����Ҫ�Cookie	
			for (Cookie cookie : cookies) {
				// �P�_�O�_������(�n�J�o�X������)..�n�J�o�X���ҦW��.cred	
					
					if((".appcred").equals(cookie.getName())) {
						isValid = true;
						break;
				}
			}
			
			// �i��P�_�O�_�X�k�ϥΪ�
			if (isValid) {
				//���U��
				chain.doFilter(request, response);			
			} else {
				//�o�ɪ��s����(�Ȥ��)�O�b? /customer?
				// ���j���f
				response.sendRedirect("../register/login.action");
			}
		} else {
			response.sendRedirect("../register/login.action");
		}
	//�H�U�O��ݬݡA�ϥ��]�S���Ӥj���v�T
	}
}
