package Servlet;


import java.io.IOException;
import java.util.List;

import DAO.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        // Khởi tạo UserRepository (có thể sử dụng Dependency Injection trong Spring)
        userRepository = new UserRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            String username = request.getParameter("username");
            userRepository.deleteById(username);
        }

        // Tải danh sách người dùng và chuyển đến JSP
        List<User> users = userRepository.findAll();
        request.setAttribute("users", users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("userList.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String lastname = request.getParameter("lastname");
        boolean isAdmin = Boolean.parseBoolean(request.getParameter("isAdmin"));

        // Tạo đối tượng User mới và lưu vào cơ sở dữ liệu
        User user = new User(username, password, lastname, isAdmin);
        userRepository.save(user);

        // Chuyển hướng về trang danh sách người dùng
        response.sendRedirect("user");
    }
}
