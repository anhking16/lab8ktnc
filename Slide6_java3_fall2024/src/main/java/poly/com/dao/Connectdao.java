package poly.com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Connectdao {

    protected static Connection conn;
   
    public Connectdao()
    {
        try {
            String url = "jdbc:sqlserver://localhost:1433;databaseName=lab6_java3";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, "sa", "tka22102005");
            System.out.println("Kết nối thành công!");
        	} 
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
