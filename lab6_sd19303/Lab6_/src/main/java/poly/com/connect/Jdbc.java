package poly.com.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc {
    // Khai báo thông tin kết nối
    static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static String dburl = "jdbc:sqlserver://localhost;database=HRM";
    static String username = "sa";
    static String password = "vinh512005";

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

    /** Thao tác dữ liệu (INSERT, UPDATE, DELETE) */
    public static int executeUpdate(String sql) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        try {
            return statement.executeUpdate(sql);
        } finally {
            connection.close(); // Đóng kết nối sau khi thực hiện
        }
    }

    /** Truy vấn dữ liệu (SELECT) */
    public static ResultSet executeQuery(String sql) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);
    }
}

