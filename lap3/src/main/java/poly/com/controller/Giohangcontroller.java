package poly.com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import poly.com.model.CartItem;
import poly.com.model.Item;
@WebServlet("/giohang")
public class Giohangcontroller extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 HttpSession session = req.getSession(); // Lấy session
	        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems"); // Lấy giỏ hàng từ session

	        // Nếu giỏ hàng chưa có, khởi tạo mới
	        if (cartItems == null) {
	            cartItems = new ArrayList<>();
	        }

	        // Tạo danh sách sản phẩm
	        List<Item> items = new ArrayList<>();
	        items.add(new Item("iphone 13", "3.png", 70000, 0.30));
		    items.add(new Item("iphone 14", "3.png", 20000, 0.30));
		    items.add(new Item("iPhone 15", "3.png", 90000, 0.25));
		    items.add(new Item("iphone 16", "3.png", 150000, 0.10));

	        req.setAttribute("items", items);
		req.getRequestDispatcher("cart.jsp").forward(req, resp);
	}

}
