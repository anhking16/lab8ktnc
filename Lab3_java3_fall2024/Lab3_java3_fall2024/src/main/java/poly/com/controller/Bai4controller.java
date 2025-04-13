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
	danhsach.add("Cần");
	danhsach.add("Cù");
	danhsach.add("Siêng");
	danhsach.add("Năng");
	req.setAttribute("ds", danhsach);
	
	
		ArrayList<Item> items = new ArrayList<>();
	  	items.add(new Item("Nokia 2020", "at.jpg", 500, 0.1));
	    items.add(new Item("Samsung Xyz", "at1.jpg", 700, 0.15));
	    items.add(new Item("iPhone Xy", "fpoly1.jpg", 900, 0.25));
	    items.add(new Item("Sony Erricson", "fpoly.jpg", 55, 0.3));
	 
	
	req.setAttribute("items", items);
			
	req.getRequestDispatcher("listproducts.jsp").forward(req, resp);
}

}
