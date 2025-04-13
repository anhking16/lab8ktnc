package poly.com.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import poly.com.dao.DepartmentInterfaceDAO;
import poly.com.dao.EmployeeInterfaceDAO;
import poly.com.model.Departments;
import poly.com.model.Employees;

import java.io.IOException;
import java.util.List;

@WebServlet("/department-details/*")
public class DepartmentDetailsController extends HttpServlet {
	private EmployeeInterfaceDAO employeeDAO = new EmployeeInterfaceDAO();
	private DepartmentInterfaceDAO departmentDAO = new DepartmentInterfaceDAO();
	
	
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	String path = req.getRequestURI();
	String[] parts = path.split("/"); 
	String departmentId = parts[parts.length - 1];
	
	List<Employees> employeesList = employeeDAO.selectAllWithDepartmentId(departmentId);
	Departments department = departmentDAO.selectById(departmentId);
	
	req.setAttribute("employeesList", employeesList);
	req.setAttribute("department", department);
	
	req.getRequestDispatcher("/departments4/department-details.jsp").forward(req, res);
	
}

}
