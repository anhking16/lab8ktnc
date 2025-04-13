package poly.com.controller;
import java.util.Map;
import java.util.HashMap;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/bai1")
public class Bai2controller extends HttpServlet
{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	req.setAttribute("message", "Welcome to FPT Polytechnic");
	Map<String, Object> map = new HashMap<>();
	map.put("fullname", "Nguyễn Văn Tèo");
	map.put("gender", "Male");
	map.put("country", "Việt Nam");
	req.setAttribute("user", map);
	req.getRequestDispatcher("/page.jsp").forward(req, resp);
	
	
	
	
}
}
