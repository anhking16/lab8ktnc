package controller;

import java.io.IOException;

import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.UserDAO;
@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		UserDAO dao = new UserDAO();
		User user = (User) dao.findAll();
		if (user == null){
			req.setAttribute("message", "Username không tồn tại");
		}else if (!user.getPassword().equals(password)) {
			req.setAttribute("message", "Password không đúng");
		}else {
			req.getSession().setAttribute("user", user);
			req.setAttribute("message", "Login successfully");
			req.getRequestDispatcher("/user2/index").forward(req, resp);
		}
		req.getRequestDispatcher("/login2.jsp").forward(req, resp);
	}
}
