package Lab2;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/baitap1")
public class TinhToan extends HttpServlet {
 @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	 req.getRequestDispatcher("baitap1.jsp").forward(req, resp);
 }
 @Override
 	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
 		// TODO Auto-generated method stub
 		float a,b,kq;
 		a=Float.parseFloat(req.getParameter("txta").toString());
 		b=Float.parseFloat(req.getParameter("txtb").toString());
 		kq=a+b;
 		req.setAttribute("kq",kq);
 		req.getRequestDispatcher("baitap1.jsp").forward(req, resp);
}
}
