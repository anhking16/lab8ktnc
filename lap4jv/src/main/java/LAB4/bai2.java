package LAB4;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet({"/calculate/add", "/calculate/sub"}) 
public class bai2 extends HttpServlet  {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	req.setAttribute("message", "Nhập số và chọn phép tính"); 
	
	String a = req.getParameter("a"); 
	String b = req.getParameter("b"); String path = req.getServletPath(); if(path.endsWith("/add")) {  	double c = Double.valueOf(a) + Double.valueOf(b);  	req.setAttribute("message", a + " + " + b + " = " + c); 
	} else { 
	 	double c = Double.valueOf(a) - Double.valueOf(b);  	req.setAttribute("message", a + " - " + b + " = " + c); 
	} 

}
}
