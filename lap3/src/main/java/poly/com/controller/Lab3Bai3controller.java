package poly.com.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet({"/lab3bai3","/lab3bai4"})
public class Lab3Bai3controller extends HttpServlet
{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	String chon=req.getRequestURI();
	if (chon.contains("/lab3bai3"))
	{
	Map<String,Object> map = new HashMap<>();
	map.put("name","Iphone 16");
	map.put("price", 120000);
	map.put("date", new Date());
	req.setAttribute("item", map);
	req.getRequestDispatcher("lab3bai3.jsp").forward(req, resp);
	}
	else
	{
		Map<String,Object> map = new HashMap<>();
		map.put("Title","ta đa");
		map.put("content","Nội dung" );
		req.setAttribute("item", map);
		req.getRequestDispatcher("lab3bai4.jsp").forward(req, resp);
	}
	
	
}
}
