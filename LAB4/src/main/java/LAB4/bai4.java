package LAB4;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
@MultipartConfig()
@WebServlet("/bai4")
public class bai4 extends HttpServlet {
private static final long serialVersionUID = 1L;

// Xử lý GET request
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
        throws ServletException, IOException {
    // Forward request đến trang upload.jsp
    req.getRequestDispatcher("/views/upload.jsp").forward(req, resp);
}

// Xử lý POST request
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
        throws ServletException, IOException {
    // Lấy file upload từ form
    Part photo = req.getPart("photo");

    // Đường dẫn để lưu file
    String path = "/static/files/" + photo.getSubmittedFileName();

    // Lấy đường dẫn tuyệt đối trên server
    String filename = req.getServletContext().getRealPath(path);

    // Ghi file lên server
    photo.write(filename);

    // Forward lại về trang upload.jsp
    req.getRequestDispatcher("/views/upload.jsp").forward(req, resp);
}
}

