package LAB4;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/dangky") // Phải khớp với action trong JSP
public class bai3 extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        // Forward request sang file bai3.jsp để hiển thị form
        req.getRequestDispatcher("bai3.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        // Đọc các tham số đơn trị
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String gender = req.getParameter("gender");
        String family = req.getParameter("family"); // Checkbox

        // Đọc tham số country (quốc tịch)
        String country = req.getParameter("country");

        // Đọc các tham số đa trị (sở thích)
        String[] hobbies = req.getParameterValues("hobby");

        // Đọc tham số ghi chú
        String note = req.getParameter("note");

        // Xuất các giá trị ra console để kiểm tra
        System.out.println("Tên đăng nhập: " + username);
        System.out.println("Mật khẩu: " + password);
        System.out.println("Giới tính: " + gender);
        System.out.println("Đã có gia đình: " + (family != null ? "Có" : "Chưa"));
        System.out.println("Quốc tịch: " + country);

        // Kiểm tra các sở thích (đa trị)
        if (hobbies != null) {
            System.out.print("Sở thích: ");
            for (String hobby : hobbies) {
                System.out.print(hobby + " ");
            }
            System.out.println();
        } else {
            System.out.println("Sở thích: Không có");
        }

        System.out.println("Ghi chú: " + note);

        // Forward lại về trang đăng ký sau khi xử lý xong
        req.getRequestDispatcher("/WEB-INF/bai3.jsp").forward(req, resp);

    }
}
