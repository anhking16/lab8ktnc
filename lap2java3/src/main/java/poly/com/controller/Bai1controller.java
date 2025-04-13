package poly.com.controller;
import java.util.Date;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/bai1")
public class Bai1controller extends HttpServlet
{
	@Override
	protected void doGet (HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
	req.setAttribute ("message", "Welcome to FPT Polytechnic!");
	req.setAttribute ("now", new Date());
	req.getRequestDispatcher("/bai1.jsp").forward(req, resp);
	}
}
