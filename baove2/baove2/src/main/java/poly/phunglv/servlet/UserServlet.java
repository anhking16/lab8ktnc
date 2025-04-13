package poly.phunglv.servlet;

import java.io.IOException;
import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.commons.beanutils.BeanUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import poly.phunglv.bean.User;
import poly.phunglv.dao.UserDAO;

@WebServlet({"/user", "/user/index", "/user/delete", "/user/create", "/user/update", "/user/edit/*", "/user/search"})
public class UserServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO dao = new UserDAO();
        User user = new User();
        
        String uri = req.getRequestURI();
        String searchKeyword = req.getParameter("search"); // Lấy từ khóa tìm kiếm nếu có

        if (uri.contains("edit")) {
            String id = uri.substring(uri.lastIndexOf("/") + 1);
            user = dao.findById(id);
            req.setAttribute("form", user);
        } else if (uri.contains("create")) {
            try {
                BeanUtils.populate(user, req.getParameterMap());
                dao.create(user);
                req.setAttribute("message", "Thêm mới thành công");
            } catch (Exception e) {
                req.setAttribute("message", "Thêm mới thất bại");
            }
        } else if (uri.contains("update")) {
            try {
                BeanUtils.populate(user, req.getParameterMap());
                dao.update(user);
                req.setAttribute("message", "Cập nhật thành công");
            } catch (Exception e) {
                req.setAttribute("message", "Cập nhật thất bại");
            }
        } else if (uri.contains("delete")) {
            try {
                String id = req.getParameter("id");
                dao.remove(id);
                req.setAttribute("message", "Xóa thành công");
            } catch (Exception e) {
                req.setAttribute("message", "Xóa thất bại");
            }
        }

        // Xử lý tìm kiếm theo nhiều thuộc tính (email, id, fullname)
        List<User> users;
        if (searchKeyword != null && !searchKeyword.trim().isEmpty()) {
            users = dao.findByKeyword(searchKeyword); // Tìm kiếm theo từ khóa
        } else {
            users = dao.findAll(); // Lấy toàn bộ danh sách nếu không có từ khóa
        }

        req.setAttribute("items", users); // Gán kết quả tìm kiếm vào thuộc tính "items"
        req.getRequestDispatcher("/views/user.jsp").forward(req, resp);
    }
}
