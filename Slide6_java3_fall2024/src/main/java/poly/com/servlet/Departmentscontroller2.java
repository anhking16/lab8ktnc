package poly.com.servlet;

import poly.com.dao.DeparmentDao;
import poly.com.model.Departments;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet({"/departments1", "/departments1/add", "/departments1/delete", "/departments1/edit", "/departments1/search"})
public class Departmentscontroller2 extends HttpServlet {
    DeparmentDao departmentDao = new DeparmentDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "list":
                showDepartments(request, response);
                break;
            case "delete":
                deleteDepartment(request, response);
                break;
            case "edit":
                editDepartment(request, response);
                break;
            case "search":
                searchDepartment(request, response);
                break;
            default:
                showDepartments(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            addDepartment(request, response);
        } else if ("update".equals(action)) {
            updateDepartment(request, response);
        }
    }

    private void showDepartments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Departments> departments = departmentDao.getAllDepartments();
        request.setAttribute("departments", departments);
        request.getRequestDispatcher("list.jsp").forward(request, response);
    }

    private void deleteDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        departmentDao.deleteDepartment(id);
        showDepartments(request, response);
    }

    private void editDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Departments department = departmentDao.findDepartmentById1(id);
        request.setAttribute("department", department);
        request.getRequestDispatcher("list.jsp").forward(request, response);
    }

    private void searchDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchId = request.getParameter("searchId");
        List<Departments> departments = departmentDao.getAllDepartments();
        if (searchId != null && !searchId.trim().isEmpty()) {
            departments = List.of(departmentDao.findDepartmentById1(searchId));
        }
        request.setAttribute("departments", departments);
        request.getRequestDispatcher("list.jsp").forward(request, response);
    }

    private void addDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        departmentDao.insertDepartment(id, name, description);
        showDepartments(request, response);
    }

    private void updateDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        departmentDao.updateDepartment(id, name, description);
        showDepartments(request, response);
    }
}
