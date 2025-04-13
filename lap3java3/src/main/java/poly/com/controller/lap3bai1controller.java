package poly.com.controller;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.List;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet
public class lap3bai1controller extends HttpServlet
{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServerException, IOException {
// TODO Auto-generated method stub
List<country> list = List.of(new country("VN","Viet Nam"), new country("US","Má>>,"),new country("CN","CHINA"));
req.setAttribute("countries", list);
req.getRequestDispatcher("lab3bai1.jsp").forward(req, resp);
}
}