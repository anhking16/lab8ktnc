package poly.com.servlet;

import poly.com.dao.DepartmentInterfaceDAO;
import poly.com.model.Departments;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet({"/departments4", "/departments4/them", "/departments4/xoa", "/departments4/sua", "/departments4/search"})
public class Departmentscontroller4 extends HttpServlet {
    private final DepartmentInterfaceDAO departmentDao = new DepartmentInterfaceDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();

        if (uri.endsWith("/departments4")) {
            // Hiển thị danh sách phòng ban
            hienThiDanhSach(request, response);
        } else if (uri.endsWith("/xoa")) {
            // Xóa phòng ban
            xoa(request, response);
        } else if (uri.endsWith("/sua")) {
            // Hiển thị form sửa phòng ban
            hienThiFormSua(request, response);
        } else if (uri.endsWith("/search")) {
            // Tìm kiếm phòng ban theo ID
            search(request, response);
        } else {
            // Mặc định hiển thị danh sách phòng ban
            hienThiDanhSach(request, response);
        }
    }

    private void hienThiDanhSach(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Departments> departments = departmentDao.selectAll();
        request.setAttribute("departments", departments);
        request.getRequestDispatcher("/departments4/list4.jsp").forward(request, response);
    }

    private void hienThiFormSua(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idToEdit = request.getParameter("id");
        Departments department = departmentDao.selectById(idToEdit);
        if (department != null) {
            request.setAttribute("department", department);
            request.getRequestDispatcher("/departments4/edit.jsp").forward(request, response);
        } else {
            request.setAttribute("message", "Phòng ban không tồn tại");
            hienThiDanhSach(request, response);
        }
    }

    protected void xoa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idToDelete = request.getParameter("id");
        departmentDao.delete(idToDelete);
        hienThiDanhSach(request, response);  // Hiển thị danh sách phòng ban sau khi xóa
    }

    protected void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchId = request.getParameter("searchId");
        List<Departments> departments;
        if (searchId == null || searchId.trim().isEmpty()) {
            departments = departmentDao.selectAll();
        } else {
            Departments department = departmentDao.selectById(searchId);
            if (department != null) {
                departments = List.of(department);
            } else {
                departments = List.of();
                request.setAttribute("message", "Không tìm thấy phòng ban với ID: " + searchId);
            }
        }
        request.setAttribute("departments", departments);
        request.getRequestDispatcher("/departments4/list4.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();

        if (uri.endsWith("/them")) {
            // Thêm mới phòng ban
            them(request, response);
        } else if (uri.endsWith("/sua")) {
            // Cập nhật thông tin phòng ban
            sua(request, response);
        }
    }

    private void sua(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Departments department = new Departments();
    	
    	department.setId(request.getParameter("id"));
    	department.setName(request.getParameter("name"));
        department.setDescription(request.getParameter("description"));
        
        departmentDao.update(department);
        // Hiển thị lại danh sách phòng ban ngay sau khi cập nhật
        hienThiDanhSach(request, response);
    }

    protected void them(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	Departments department = new Departments();
        	
        	department.setId(request.getParameter("id"));
        	department.setName(request.getParameter("name"));
            department.setDescription(request.getParameter("description"));
            departmentDao.insert(department);
            
            // Hiển thị danh sách phòng ban ngay sau khi thêm
            hienThiDanhSach(request, response);
        } catch (Exception ex) {
            request.setAttribute("message", "Lỗi nhập liệu");
            hienThiDanhSach(request, response); // Hiển thị lại danh sách sau khi lỗi
        }
    }
}
