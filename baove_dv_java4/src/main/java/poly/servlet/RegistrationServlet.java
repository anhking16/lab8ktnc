package poly.servlet;

import dao.RegistrationDAO;
import dao.RegistrationDAOInterface;
import dao.Registration;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private RegistrationDAOInterface registrationDAO;

    @Override
    public void init() {
        registrationDAO = new RegistrationDAO();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if (action == null) {
                listRegistration(request, response);
            } else {
                switch (action) {
                    case "delete":
                        deleteRegistration(request, response);
                        break;
                    case "search":
                        searchRegistration(request, response);
                        break;
                    case "register":
                        registerUser(request, response);
                        break;
                    default:
                        listRegistration(request, response);
                }
            }
        } catch (Exception ex) {
            throw new ServletException("Request handling failed.", ex);
        }
    }

    private void listRegistration(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Registration> registrations = registrationDAO.findAll();
        request.setAttribute("registrations", registrations);
        request.getRequestDispatcher("/registration.jsp").forward(request, response);
    }

    private void deleteRegistration(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        registrationDAO.deleteByUsername(username);
        response.sendRedirect("registration?action=list");
    }

    private void searchRegistration(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lastname = request.getParameter("lastname");
        List<Registration> registrations = registrationDAO.searchByLastname(lastname);
        request.setAttribute("registrations", registrations);
        request.getRequestDispatcher("/registration.jsp").forward(request, response);
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String lastname = request.getParameter("lastname");
        boolean isAdmin = request.getParameter("admin") != null;

        if (registrationDAO.isUsernameExist(username)) {
            request.setAttribute("errorMessage", "Username already exists.");
        } else {
            Registration newUser = new Registration(username, password, lastname, isAdmin);
            registrationDAO.save(newUser);
        }
        listRegistration(request, response); // Hiển thị danh sách sau khi xử lý
    }
}
