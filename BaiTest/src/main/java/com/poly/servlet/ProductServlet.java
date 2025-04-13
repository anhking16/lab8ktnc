package com.poly.servlet;

import java.io.IOException;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;

import com.poly.bean.Product;
import com.poly.utils.ProductDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({"/product/index", "/product/delete", "/product/create", "/product/update", "/product/edit/*"})
public class ProductServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDAO dao = new ProductDAO();
        Product product = new Product();
        String uri = req.getRequestURI(); // Lấy URI để xác định hành động

        try {
            // Xử lý các trường hợp theo URI
            if (uri.contains("edit")) {
                // Lấy ID sản phẩm từ URL và tìm trong DB
                String code = uri.substring(uri.lastIndexOf("/") + 1);
                product = dao.findById(code);
                if (product == null) {
                    req.setAttribute("message", "Không tìm thấy sản phẩm!");
                } else {
                    req.setAttribute("form", product);
                }
            } else if (uri.contains("create")) {
                // Thêm mới sản phẩm
                BeanUtils.populate(product, req.getParameterMap());
                if (dao.findById(product.getCode()) != null) {
                    req.setAttribute("message", "Mã sản phẩm đã tồn tại, không thể thêm mới.");
                } else {
                    dao.create(product);
                    req.setAttribute("message", "Thêm sản phẩm thành công.");
                }
            } else if (uri.contains("update")) {
                try {
                    // Giả sử product đã được định nghĩa ở đâu đó
                    BeanUtils.populate(product, req.getParameterMap());
                    
                    // Giả sử dao là đối tượng quản lý dữ liệu của bạn
                    dao.update(product);
                    
                    // Thiết lập thông báo thành công để hiển thị trong view
                    req.setAttribute("message", "Cập nhật thành công");
                } catch (Exception e) {
                    // Xử lý ngoại lệ
                    req.setAttribute("message", "Có lỗi xảy ra khi cập nhật sản phẩm");
                }
            


            

            

            } else if (uri.contains("delete")) {
                // Xóa sản phẩm
                String code = req.getParameter("code");
                dao.remove(code);
                req.setAttribute("message", "Xóa sản phẩm thành công.");
            }
        } catch (Exception e) {
            req.setAttribute("message", "Đã xảy ra lỗi: " + e.getMessage());
        }

        // Truyền danh sách sản phẩm và thông tin form về JSP
        req.setAttribute("items", dao.findAll());
        req.getRequestDispatcher("/views/product.jsp").forward(req, resp);
    }
}
