package ASMcontroler;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/NewsletterServlet")
public class newletter extends HttpServlet {
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Lấy email từ request
    String email = request.getParameter("email");
    
    // Gửi phản hồi lại cho người dùng
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.println("<html><body>");
    if (email != null && !email.isEmpty()) {
        out.println("<h2>Cảm ơn bạn đã đăng ký nhận bản tin với email: " + email + "</h2>");
    } else {
        out.println("<h2>Vui lòng nhập email hợp lệ.</h2>");
    }
    out.println("<a href='home.jsp'>Quay lại trang chủ</a>");
    out.println("</body></html>");
}

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
}
	
}
