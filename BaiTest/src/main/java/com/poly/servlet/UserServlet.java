package com.poly.servlet;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;



import org.apache.commons.beanutils.BeanUtils;

import com.poly.bean.User;
import com.poly.utils.UserDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({"/user/index", "/user/delete", "/user/create", "/user/update", "/user/edit/*", "/user/search"})
public class UserServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserDAO dao = new UserDAO();
		User user = new User();
		
		String uri = req.getRequestURI();
		
		if (uri.contains("edit")) {
			String id = uri.substring(uri.lastIndexOf("/") + 1);
			System.out.print("edit:" + id);
			user = dao.findById(id);
		} else if (uri.contains("create")) {
			try {
				BeanUtils.populate(user, req.getParameterMap());				
				dao.create(user);
				req.setAttribute("message", "Thêm mới thành công");
			} catch (Exception e) {
				req.setAttribute("message", "Thêm mới thất bại");
			}
		} else if (uri.contains("update")) {
			try {
				BeanUtils.populate(user, req.getParameterMap());
				dao.update(user);
				req.setAttribute("message", "Cập nhật thành công");
			} catch (Exception e) {
				req.setAttribute("message", "Cập nhật thất bại");
			}
		} else if (uri.contains("delete")) {
			try {
				String id = req.getParameter("id");
				dao.remove(id);
				req.setAttribute("message", "Xóa thành công");
			} catch (Exception e) {
				req.setAttribute("message", "Xóa thất bại");
			}
		} else if (uri.contains("search")) {
			String keyword = req.getParameter("keyword");
			List<User> allUsers = dao.findAll();
			
			// Filter users by keyword (search by fullname or email)
			List<User> filteredUsers = allUsers.stream()
				.filter(u -> u.getFullname().toLowerCase().contains(keyword.toLowerCase()) 
				          || u.getEmail().toLowerCase().contains(keyword.toLowerCase()))
				.collect(Collectors.toList());
			
			req.setAttribute("items", filteredUsers);
			req.getRequestDispatcher("/views/user.jsp").forward(req, resp);
			return; 
		}
		
		// Default behavior
		req.setAttribute("form", user);
		req.setAttribute("items", dao.findAll());
		req.getRequestDispatcher("/views/user.jsp").forward(req, resp);
	}
}
