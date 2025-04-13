package poly.com.sql;

import java.sql.ResultSet;

import poly.com.connect.Jdbc;

public class TestJdbc {
	public static void main(String[] args) {
        try {
            String sql = "SELECT column_name FROM table_name";
            ResultSet resultSet = Jdbc.executeQuery(sql);

            // Duyệt qua kết quả truy vấn
            while (resultSet.next()) {
                String value = resultSet.getString("column_name");
                System.out.println(value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        try {
            // Thêm dữ liệu
            String insertSql = "INSERT INTO table_name (column1, column2) VALUES ('value1', 'value2')";
            int rowsInserted = Jdbc.executeUpdate(insertSql);
            System.out.println("Rows inserted: " + rowsInserted);

            // Cập nhật dữ liệu
            String updateSql = "UPDATE table_name SET column1 = 'new_value' WHERE column2 = 'condition'";
            int rowsUpdated = Jdbc.executeUpdate(updateSql);
            System.out.println("Rows updated: " + rowsUpdated);

            // Xóa dữ liệu
            String deleteSql = "DELETE FROM table_name WHERE column1 = 'value'";
            int rowsDeleted = Jdbc.executeUpdate(deleteSql);
            System.out.println("Rows deleted: " + rowsDeleted);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
}
