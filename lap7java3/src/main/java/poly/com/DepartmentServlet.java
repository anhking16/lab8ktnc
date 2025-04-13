package poly.com;
import org.apache.commons.beanutils.BeanUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet({"/department/index", "/department/edit/*", "/department/create", "/department/update", "/department/delete", "/department/reset"})
public class DepartmentServlet extends HttpServlet {

    private DepartmentDAO departmentDAO;

    @Override
    public void init() throws ServletException {
        departmentDAO = new DepartmentDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        if (path.startsWith("/department/edit")) {
            String id = req.getPathInfo().substring(1);
            Department department = departmentDAO.findById(id);
            req.setAttribute("item", department);
        }
        List<Department> departments = departmentDAO.findAll();
        req.setAttribute("list", departments);
        req.setAttribute("path", req.getContextPath() + "/department");
        req.getRequestDispatcher("/department.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Department department = new Department();
        try {
            BeanUtils.populate(department, req.getParameterMap());
            String action = req.getServletPath();
            if (action.endsWith("/create")) {
                departmentDAO.create(department);
            } else if (action.endsWith("/update")) {
                departmentDAO.update(department);
            } else if (action.endsWith("/delete")) {
                String id = department.getId();
                departmentDAO.deleteById(id);
            } else if (action.endsWith("/reset")) {
                req.setAttribute("item", new Department());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/department/index");
    }
}
