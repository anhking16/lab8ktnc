package controller;

import java.io.IOException;

import org.apache.commons.beanutils.BeanUtils;

import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.UserDAO;

@WebServlet({ "/account/sign-up", "/account/sign-in", "/account/sign-out", "/account/edit-profile" })
public class AccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();

        if (uri.endsWith("sign-in")) {
            this.doSignIn(req, resp);
        } else if (uri.endsWith("sign-up")) {
            this.doSignUp(req, resp);
        } else if (uri.endsWith("sign-out")) {
            this.doSignOut(req, resp);
        } else if (uri.endsWith("edit-profile")) {
            this.doEditProfile(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Resource not available.");
        }
    }

    private void doSignIn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getMethod().equalsIgnoreCase("POST")) {
            String username = req.getParameter("username");
            String password = req.getParameter("password");

            try {
                UserDAO dao = new UserDAO();
                User user = dao.findById(username);
                if (user == null) {
                    req.setAttribute("message", "Tài khoản không tồn tại!");
                } else if (!user.getPassword().equals(password)) {
                    req.setAttribute("message", "Sai mật khẩu!");
                } else {
                    req.getSession().setAttribute("user", user);
                    req.setAttribute("message", "Đăng nhập thành công!");
                    resp.sendRedirect(req.getContextPath() + "/views/user.jsp");
                    return;
                }
            } catch (Exception e) {
                req.setAttribute("message", "Lỗi hệ thống khi đăng nhập!");
            }
        }
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    private void doSignUp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getMethod().equalsIgnoreCase("POST")) {
            try {
                User newUser = new User();
                BeanUtils.populate(newUser, req.getParameterMap());

                UserDAO dao = new UserDAO();
                dao.create(newUser);
                req.setAttribute("message", "Đăng ký thành công!");
            } catch (Exception e) {
                req.setAttribute("message", "Lỗi hệ thống khi đăng ký!");
            }
        }
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    private void doSignOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }

    private void doEditProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        if (req.getMethod().equalsIgnoreCase("POST")) {
            try {
                BeanUtils.populate(user, req.getParameterMap());
                UserDAO dao = new UserDAO();
                dao.update(user);
                req.setAttribute("message", "Cập nhật tài khoản thành công!");
            } catch (Exception e) {
                req.setAttribute("message", "Lỗi hệ thống khi cập nhật tài khoản!");
            }
        }
        req.getRequestDispatcher("/views/edit-profile.jsp").forward(req, resp);
    }
}
