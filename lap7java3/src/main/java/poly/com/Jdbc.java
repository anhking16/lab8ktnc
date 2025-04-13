package poly.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Jdbc {
    static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static String dburl = "jdbc:sqlserver://localhost;database=HRM";
    static String username = "sa";
    static String password = "tka22102005";

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dburl, username, password);
    }

    public static int executeUpdate(String sql, Object... values) throws SQLException {
        PreparedStatement stmt = createPreStmt(sql, values);
        return stmt.executeUpdate();
    }

    public static ResultSet executeQuery(String sql, Object... values) throws SQLException {
        PreparedStatement stmt = createPreStmt(sql, values);
        return stmt.executeQuery();
    }

    private static PreparedStatement createPreStmt(String sql, Object... values) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        for (int i = 0; i < values.length; i++) {
            stmt.setObject(i + 1, values[i]);
        }
        return stmt;
    }
}