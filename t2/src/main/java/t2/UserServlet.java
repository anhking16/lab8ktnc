package t2;
import java.io.IOException;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/User Servlet") // Đảm bảo không có khoảng trắng
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int id = Integer.parseInt(request.getParameter("id") != null ? request.getParameter("id") : "0");
        String search = request.getParameter("search");

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=test;encrypt=true;trustServerCertificate=true;", "sa", "tka22102005");

            if ("Create".equals(action)) {
                pstmt = conn.prepareStatement("INSERT INTO users (username, email, password) VALUES (?, ?, ?)");
                pstmt.setString(1, username);
                pstmt.setString(2, email);
                pstmt.setString(3, password);
                pstmt.executeUpdate();
            } else if ("Update".equals(action)) {
                pstmt = conn.prepareStatement("UPDATE users SET username=?, email=?, password=? WHERE id=?");
                pstmt.setString(1, username);
                pstmt.setString(2, email);
                pstmt.setString(3, password);
                pstmt.setInt(4, id);
                pstmt.executeUpdate();
            } else if ("Delete".equals(action)) {
                pstmt = conn.prepareStatement("DELETE FROM users WHERE id=?");
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
            } else if ("Edit".equals(action)) {
                pstmt = conn.prepareStatement("SELECT * FROM users WHERE id=?");
                pstmt.setInt(1, id);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    request.setAttribute("username", rs.getString("username"));
                    request.setAttribute("email", rs.getString("email"));
                    request.setAttribute("id", rs.getInt("id"));
                }
            } else if ("Search".equals(action)) {
                pstmt = conn.prepareStatement("SELECT * FROM users WHERE username LIKE ?");
                pstmt.setString(1, "%" + search + "%");
                ResultSet rs = pstmt.executeQuery();
                request.setAttribute("resultSet", rs);
            }

            request.getRequestDispatcher("USER.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch (SQLException e) {}
            if (conn != null) try { conn.close(); } catch (SQLException e) {}
        }
    }
}