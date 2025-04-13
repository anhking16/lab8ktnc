package poly.com.controller;

import java.io.IOException;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import poly.com.model.Item;
@WebServlet("/bai4list")
public class Bai4controller extends HttpServlet
{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	req.setAttribute("lan", 10000000);
	
	ArrayList<String> danhsach= new ArrayList<String>();
	danhsach.add("cần ");
	danhsach.add("cù");
	danhsach.add("bù");
	danhsach.add("ăn cơm");
	req.setAttribute("ds", danhsach);
	
	
		ArrayList<Item> items = new ArrayList<>();
	  	items.add(new Item("iphone 13", "3.png", 70000, 0.30));
	    items.add(new Item("iphone 14", "3.png", 20000, 0.30));
	    items.add(new Item("iPhone 15", "3.png", 90000, 0.25));
	    items.add(new Item("iphone 16", "3.png", 150000, 0.10));
	 
	
	req.setAttribute("items", items);
			
	req.getRequestDispatcher("listproducts.jsp").forward(req, resp);
}

}
