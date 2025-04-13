package poly.servlet;

import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Model.Product;


import java.io.IOException;
import java.util.List;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {

    private ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String code = req.getParameter("code");

        if ("edit".equals(action) && code != null) {
            Product product = productDAO.findById(code);
            req.setAttribute("product", product);
        } else if ("delete".equals(action) && code != null) {
            productDAO.delete(code);
        } 

        List<Product> products = productDAO.findAll();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/product.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));

        Product product = new Product();
        product.setCode(code);
        product.setName(name);
        product.setPrice(price);

        String action = req.getParameter("action");

        if ("create".equals(action)) {
            productDAO.save(product);
        } else if ("update".equals(action)) {
            productDAO.update(product);
        }

        resp.sendRedirect(req.getContextPath() + "/product");
    }
}
