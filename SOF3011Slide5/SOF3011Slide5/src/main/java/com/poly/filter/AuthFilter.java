package com.poly.filter;

import java.io.IOException;


import com.poly.bean.User;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@WebFilter({"/user/index","/user/edit/*","/video/like/*","/video/share/*","/account/change-password","/admin/*"})
public class AuthFilter implements HttpFilter {
	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		String uri = req.getRequestURI();
		User user = (User)req.getSession().getAttribute("user");
		String error=""; 
		if(user==null) {//chua dang nhap
			error = resp.encodeURL("Vui long dang nhap");
		}else if(!user.getAdmin() && uri.contains("/admin/")){//kg phai admin
			error = resp.encodeURL("Vui long dang nhap voi vai tro admin");
		}
		
		if(!error.isEmpty()) {// truy cap kg hop le
			req.getSession().setAttribute("securi", uri);
			resp.sendRedirect("/SOF3011Slide5/login.jsp?error="+resp.encodeURL(error));
		}else {//truy cap hop le
			chain.doFilter(req, resp);
		}
				
	}
}
