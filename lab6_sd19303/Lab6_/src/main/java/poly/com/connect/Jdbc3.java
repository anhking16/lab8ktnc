package poly.com.connect;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Jdbc3 {
	// Khai báo thông tin kết nối
    static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static String dburl = "jdbc:sqlserver://localhost;database=HRM";
    static String username = "sa";
    static String password = "123456";

    // Nạp driver khi lớp được tải
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /** Mở kết nối */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dburl, username, password);
    }

    /** Thao tác dữ liệu (INSERT, UPDATE, DELETE) với thủ tục lưu trữ */
    public static int executeUpdate(String sql, Object... values) throws SQLException {
        Connection connection = getConnection();
        CallableStatement statement = connection.prepareCall(sql);  // Sử dụng CallableStatement
        try {
            // Gán các tham số vào thủ tục lưu trữ
            for (int i = 0; i < values.length; i++) {
                statement.setObject(i + 1, values[i]);
            }
            return statement.executeUpdate();
        } finally {
            connection.close(); // Đóng kết nối sau khi thực hiện
        }
    }

    /** Truy vấn dữ liệu (SELECT) với thủ tục lưu trữ */
    public static ResultSet executeQuery(String sql, Object... values) throws SQLException {
        Connection connection = getConnection();
        CallableStatement statement = connection.prepareCall(sql);  // Sử dụng CallableStatement
        // Gán các tham số vào thủ tục lưu trữ
        for (int i = 0; i < values.length; i++) {
            statement.setObject(i + 1, values[i]);
        }
        return statement.executeQuery();
    }
}
