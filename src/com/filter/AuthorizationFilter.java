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
		// 攔截有沒有憑證的cookies物件
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		// 取出所有的Cookie
		Cookie[] cookies = request.getCookies();
		//目前我找不到 透過Struts 將 Controller.loginForm()產生的cookie資料傳到 filter
		//所以目前filter是虛設。
		boolean isValid = true; 		 	
		if (cookies != null) {
			
			// 走訪Cookie 判斷是否有憑證的Cookie	
			for (Cookie cookie : cookies) {
				// 判斷是否有憑證(登入發出的憑證)..登入發出憑證名稱.cred	
					
					if((".appcred").equals(cookie.getName())) {
						isValid = true;
						break;
				}
			}
			
			// 進行判斷是否合法使用者
			if (isValid) {
				//往下走
				chain.doFilter(request, response);			
			} else {
				//這時的瀏覽器(客戶端)是在? /customer?
				// 踢到大門口
				response.sendRedirect("../register/login.action");
			}
		} else {
			response.sendRedirect("../register/login.action");
		}
	//以下是塞看看，反正也沒有太大的影響
	}
}
