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

@WebServlet({"/departments","/departments/them","/departments/xoa","/departments/sua","/departments/delete"})
public class Departmentscontroller extends HttpServlet {
     DeparmentDao departmentDao = new DeparmentDao();

 
    protected void xoa(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    	{
    	  String idToDelete = request.getParameter("id");
          departmentDao.deleteDepartment(idToDelete);

          // Sau khi xóa, tải lại danh sách phòng ban
          List<Departments> updatedDepartments = departmentDao.getAllDepartments();
          request.setAttribute("departments", updatedDepartments);

          // Forward lại danh sách mới cho trang list.jsp
          request.getRequestDispatcher("list.jsp").forward(request, response);
    
    	} 
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list"; // Mặc định là "list" nếu không có action nào được chỉ định
        }

        switch (action) {
            case "list":
                // Hiển thị danh sách phòng ban
                List<Departments> departments = departmentDao.getAllDepartments();
                request.setAttribute("departments", departments);
                request.getRequestDispatcher("list.jsp").forward(request, response);
                break;
                
            case "delete":
                // Xóa phòng ban
            	xoa(request,response);
                break;

            case "edit":
                // Sửa thông tin phòng ban
                String idToEdit = request.getParameter("id");
                Departments departmentToEdit = departmentDao.findDepartmentById1(idToEdit);
                request.setAttribute("department", departmentToEdit);
            
                request.getRequestDispatcher("list.jsp").forward(request, response);
                break;

            case "search":
                // Tìm kiếm phòng ban theo ID
            	 // Tìm kiếm phòng ban theo ID
                String searchId = request.getParameter("searchId");

                // Kiểm tra nếu searchId rỗng hoặc null thì load toàn bộ danh sách
                if (searchId == null || searchId.trim().isEmpty()) {
                    List<Departments> allDepartments = departmentDao.getAllDepartments();
                    request.setAttribute("departments", allDepartments);
                } else {
                    // Tìm kiếm theo ID
                    Departments department = departmentDao.findDepartmentById1(searchId);
                    if (department != null) {
                        request.setAttribute("departments", List.of(department));
                    } else {
                        request.setAttribute("departments", List.of()); // Trả về danh sách rỗng nếu không tìm thấy
                        request.setAttribute("message", "Không tìm thấy phòng ban với ID: " + searchId);
                    }
                }

                // Forward lại danh sách cho list.jsp
                request.getRequestDispatcher("list.jsp").forward(request, response);
                break;

            default:
                // Nếu action không hợp lệ, mặc định hiển thị danh sách phòng ban
                List<Departments> defaultDepartments = departmentDao.getAllDepartments();
                request.setAttribute("departments", defaultDepartments);
                request.getRequestDispatcher("list.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
        	try
        	{
            // Thêm mới phòng ban
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            departmentDao.insertDepartment(id, name, description);
            
            List<Departments> departments = departmentDao.getAllDepartments();
            request.setAttribute("departments", departments);
            request.setAttribute("messagethem", "Thêm Thành Công");
          
            request.getRequestDispatcher("list.jsp").forward(request, response);
            
            //response.sendRedirect("departments?action=list"); // dùng senredirect
        
        	}
        	catch(Exception ex)
        	{
        		 request.setAttribute("messagethem", "Lỗi nhập liệu ");
        		  request.getRequestDispatcher("list.jsp").forward(request, response);
        	}

        } else if ("update".equals(action)) {
            // Cập nhật thông tin phòng ban
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            departmentDao.updateDepartment(id, name, description);
            response.sendRedirect("departments?action=list");
        }
    }
}
