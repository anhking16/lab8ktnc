package poly.com.servlet;

import poly.com.dao.EmployeesDao;
import poly.com.model.Employees;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(urlPatterns = {"/employees", "/employees/add", "/employees/delete", "/employees/update", "/employees/search","/employees/edit"})
@MultipartConfig
public class EmployeesController extends HttpServlet {
    private EmployeesDao employeesDao = new EmployeesDao();
    private final String UPLOAD_DIR = "uploads";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        if (path.equals("/employees")) {
            List<Employees> employees = employeesDao.getAllEmployees();
            request.setAttribute("employeesList", employees);
            request.getRequestDispatcher("/employees.jsp").forward(request, response);
        }
       
        
        
        switch (path) {
           
        
            case "/employees/delete":
                // Xóa nhân viên
                String deleteId = request.getParameter("id");
                employeesDao.deleteEmployee(deleteId);
                request.setAttribute("message", "Xóa nhân viên thành công!"); // Thông báo xóa thành công
                break;

                     

            case "/employees/search":
                // Tìm nhân viên
                String searchId = request.getParameter("id");
                Employees foundEmployee = employeesDao.findEmployeeById(searchId);
                if (foundEmployee != null) {
                    request.setAttribute("foundEmployee", foundEmployee);
                    request.getRequestDispatcher("/timemployees.jsp").forward(request, response);
                } else {
                    request.setAttribute("message", "Nhân viên không tìm thấy.");
                    request.getRequestDispatcher("/timemployees.jsp").forward(request, response); // Chuyển hướng lại đến timemployees.jsp
                }
                break;
            case "/employees/edit":
                // Tìm nhân viên
            	 String editId = request.getParameter("id");
                Employees editfound = employeesDao.findEmployeeById(editId);
                if (editfound != null) {
                    request.setAttribute("foundEmployee", editfound);
                 request.setAttribute("editid", editfound);
                 
                 
                 List<Employees> allEmployees = employeesDao.getAllEmployees();
                 request.setAttribute("employeesList", allEmployees);
                 request.getRequestDispatcher("/employees.jsp").forward(request, response);
   
                }
                
                break;    
                
        }
        
        List<Employees> allEmployees = employeesDao.getAllEmployees();
        request.setAttribute("employeesList", allEmployees);
        
        // Chuyển hướng về trang JSP để hiển thị lại
        request.getRequestDispatcher("/employees.jsp").forward(request, response);
    }

    
    private void addEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Code thêm nhân viên
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        String fullname = request.getParameter("fullname");
        String photo = uploadFile(request); // Lấy đường dẫn ảnh sau khi upload
        boolean gender = request.getParameter("gender") != null;
        String birthdayStr = request.getParameter("birthday");
        Date birthday = null;

        try {
            birthday = parseDate(birthdayStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        double salary = Double.parseDouble(request.getParameter("salary"));
        String departmentId = request.getParameter("departmentId");

        // Thêm nhân viên vào CSDL
        employeesDao.insertEmployee(id, password, fullname, photo, gender, birthday, salary, departmentId);
        request.setAttribute("message", "Thêm nhân viên thành công!"); // Thông báo thêm thành công
        
        List<Employees> allEmployees = employeesDao.getAllEmployees();
        request.setAttribute("employeesList", allEmployees);
        request.getRequestDispatcher("/employees.jsp").forward(request, response);
    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Code cập nhật nhân viên
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        String fullname = request.getParameter("fullname");
        String photo = uploadFile(request); // Lấy đường dẫn ảnh sau khi upload
        boolean gender = request.getParameter("gender") != null;
        String birthdayStr = request.getParameter("birthday");
        Date birthday = null;

        try {
            birthday = parseDate(birthdayStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        double salary = Double.parseDouble(request.getParameter("salary"));
        String departmentId = request.getParameter("departmentId");

        // Cập nhật nhân viên vào CSDL
        employeesDao.updateEmployee(id, password, fullname, photo, gender, birthday, salary, departmentId);
        request.setAttribute("message", "Cập nhật nhân viên thành công!"); // Thông báo cập nhật thành công
       
        List<Employees> allEmployees = employeesDao.getAllEmployees();
        request.setAttribute("employeesList", allEmployees);
        request.getRequestDispatcher("/employees.jsp").forward(request, response);
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      

        String action = request.getParameter("action");
     // Kiểm tra nếu action là null
        if (action == null || action.isEmpty()) {
            request.setAttribute("message", "Vui lòng chọn một hành động hợp lệ!"); // Thông báo lỗi nếu action null
            request.getRequestDispatcher("/employees.jsp").forward(request, response);
            return; // Dừng xử lý nếu không có action
        }
        switch (action) 
	        {
	            
	        	case "Add Employee":
	                addEmployee(request, response);
	                break;
	
	            case "Update Employee":
	                updateEmployee(request, response);
	                break;
	            case "Delete Employee":
	            	String deleteId = request.getParameter("id");
	                employeesDao.deleteEmployee(deleteId);
	                request.setAttribute("message", "Xóa nhân viên thành công!"); // Thông báo xóa thành công
	               
	                List<Employees> allEmployees = employeesDao.getAllEmployees();
	                request.setAttribute("employeesList", allEmployees);
	                request.getRequestDispatcher("/employees.jsp").forward(request, response);
	                
	                break; 
	        }
        
          }

    private String uploadFile(HttpServletRequest request) throws IOException, ServletException {
        // Lấy phần file từ request
        Part filePart = request.getPart("photo"); // "photo" là tên của input type=file trong form

        // Kiểm tra xem có tệp nào được chọn không
        if (filePart == null || filePart.getSize() == 0) {
            // Trả về đường dẫn ảnh mặc định nếu không có tệp nào được chọn
            return "uploads/default.jpg"; // Thay đổi giá trị này thành đường dẫn của ảnh mặc định của bạn
        }

        // Lấy tên file và đường dẫn
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); 
        String filePath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR + File.separator + fileName;

        // Tạo thư mục nếu chưa tồn tại
        File uploadsDir = new File(getServletContext().getRealPath("") + File.separator + UPLOAD_DIR);
        if (!uploadsDir.exists()) {
            uploadsDir.mkdirs();
        }

        // Ghi file vào thư mục uploads
        filePart.write(filePath);

        // Trả về đường dẫn ảnh để lưu vào cơ sở dữ liệu
        return UPLOAD_DIR + "/" + fileName;
    }


    private Date parseDate(String dateStr) throws Exception {
        try {
            // Định dạng ngày bạn mong muốn (ví dụ: yyyy-MM-dd)
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false); // Bắt lỗi nếu định dạng không chính xác

            // Chuyển đổi từ String sang java.util.Date
            java.util.Date utilDate = sdf.parse(dateStr);

            // Chuyển từ java.util.Date sang java.sql.Date (phục vụ cho việc lưu vào database)
            return new java.sql.Date(utilDate.getTime());
        } catch (Exception e) {
            // Nếu có lỗi, ném ngoại lệ
            throw new Exception("Định dạng ngày không hợp lệ");
        }
    }
} 