package controller;

import java.io.IOException;
import Model.BeanCart;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ShoppingServletSession")
public class ShoppingServletSession extends HttpServlet {
    private static final String homeShop = "/views/Shopping.jsp";
    private static final String viewCartPage = "/views/ViewCart.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = ""; // Phòng NullPointerException
        }

        HttpSession session = request.getSession(true); // Luôn đảm bảo session tồn tại
        BeanCart cart = (BeanCart) session.getAttribute("CART");
        if (cart == null) {
            cart = new BeanCart();
        }

        switch (action) {
            case "Add To Cart":
                // Thêm sản phẩm vào giỏ hàng
                String item = request.getParameter("cboCard");
                cart.addToCart(item); // Đảm bảo tên phương thức chính xác
                session.setAttribute("CART", cart);
                request.getRequestDispatcher(homeShop).forward(request, response);
                break;

            case "View Cart":
                // Hiển thị giỏ hàng
                session.setAttribute("CART", cart);
                request.getRequestDispatcher(viewCartPage).forward(request, response);
                break;

            case "Delete":
                // Xóa sản phẩm khỏi giỏ hàng
                String[] items = request.getParameterValues("chk");
                if (items != null) {
                    for (String i : items) {
                        cart.deleteItem(i);
                    }
                }
                session.setAttribute("CART", cart);
                request.getRequestDispatcher(viewCartPage).forward(request, response);
                break;

            default:
                // Chuyển tiếp đến trang chủ mua sắm nếu không có hành động cụ thể
                request.getRequestDispatcher(homeShop).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
