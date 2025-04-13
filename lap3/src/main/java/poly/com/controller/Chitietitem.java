package poly.com.controller;

import java.io.IOException;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import poly.com.model.Item;
@WebServlet("/ProductDetailServlet")
public class Chitietitem extends HttpServlet
{
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// TODO Auto-generated method stub
		 String name = req.getParameter("id");
		 selectById(name, req);
		 req.getRequestDispatcher("/detailtu.jsp").forward(req, resp);
		}
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	 String name = req.getParameter("id");
	 selectById(name, req);
	 req.getRequestDispatcher("/detailtu.jsp").forward(req, resp);
	 
}

public void selectById(String name, HttpServletRequest request) throws ServletException, IOException 
{
{
    ArrayList<Item> items = new ArrayList<>();
    items.add(new Item("iphone 13", "3.png", 70000, 0.30));
    items.add(new Item("iphone 14", "3.png", 20000, 0.30));
    items.add(new Item("iPhone 15", "3.png", 90000, 0.25));
    items.add(new Item("iphone 16", "3.png", 150000, 0.10));

    ArrayList<Item> selectedItems = new ArrayList<>();
    for (Item item : items) {
        if (item.getName().equalsIgnoreCase(name)) {
            selectedItems.add(item); //1 sản phẩm
            
        }
    }

    // Lưu danh sách sản phẩm đã chọn vào request để chuyển tiếp tới trang JSP
    request.setAttribute("selectedItems", selectedItems);
}

}


}


