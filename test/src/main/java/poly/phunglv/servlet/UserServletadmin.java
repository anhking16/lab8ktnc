package poly.phunglv.servlet;

import java.io.IOException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import poly.phunglv.bean.User;
import poly.phunglv.dao.UserDAOadmin;

@WebServlet({"/user", "/user/index", "/user/delete", "/user/create", "/user/update", "/user/edit/*", "/user/search"})
public class UserServletadmin extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAOadmin dao = new UserDAOadmin();
        User user = new User();

        String uri = req.getRequestURI();
        String searchKeyword = req.getParameter("search");

        try {
            if (uri.contains("edit")) {
                String username = uri.substring(uri.lastIndexOf("/") + 1);
                user = dao.findById(username);
                if (user != null) {
                    req.setAttribute("form", user);
                } else {
                    req.setAttribute("message", "Người dùng không tồn tại.");
                }
            } else if (uri.contains("create")) {
                BeanUtils.populate(user, req.getParameterMap());
                dao.create(user);
                req.setAttribute("message", "Thêm mới thành công");
            } else if (uri.contains("update")) {
                BeanUtils.populate(user, req.getParameterMap());
                dao.update(user);
                req.setAttribute("message", "Cập nhật thành công");
            } else if (uri.contains("delete")) {
                String username = req.getParameter("username");
                dao.remove(username);
                req.setAttribute("message", "Xóa thành công");
            }
        } catch (Exception e) {
            req.setAttribute("message", "Đã xảy ra lỗi: " + e.getMessage());
        }

        List<User> users;
        if (searchKeyword != null && !searchKeyword.trim().isEmpty()) {
            users = dao.findByKeyword(searchKeyword);
        } else {
            users = dao.findAll();
        }

        req.setAttribute("items", users);
        req.getRequestDispatcher("/views/user.jsp").forward(req, resp);
    }
}
