package poly.com.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/account/login")
public class bai1java4 extends HttpServlet
{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	req.setAttribute("message", "enter username");
	req.getRequestDispatcher("/account/login.jsp").forward(req, resp);
}
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	String username = req.getParameter("username");
	String password = req.getParameter("password");
	if(username.equalsIgnoreCase("FPT") && password.equals("poly")) {
	req.setAttribute("message", "Login successfully");
	} else {
	req.setAttribute("message", "Invalid username or password");
	}
	req.getRequestDispatcher("/account/login.jsp").forward(req, resp);
}
}
