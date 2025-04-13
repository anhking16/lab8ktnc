package poly.com.sql;

import java.sql.ResultSet;

import poly.com.connect.Jdbc2;

public class TestJdbc2 {
	public static void main(String[] args) {
        try {
            // Câu lệnh SELECT với tham số
            String sql = "SELECT column_name FROM table_name WHERE condition_column = ?";
            Object[] values = { "value_condition" };  // Giá trị tham số

            ResultSet resultSet = Jdbc2.executeQuery(sql, values);

            // Duyệt qua kết quả truy vấn
            while (resultSet.next()) {
                String value = resultSet.getString("column_name");
                System.out.println(value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        try {
            // Câu lệnh INSERT với tham số
            String insertSql = "INSERT INTO table_name (column1, column2) VALUES (?, ?)";
            Object[] insertValues = { "value1", "value2" };
            int rowsInserted = Jdbc2.executeUpdate(insertSql, insertValues);
            System.out.println("Rows inserted: " + rowsInserted);

            // Câu lệnh UPDATE với tham số
            String updateSql = "UPDATE table_name SET column1 = ? WHERE column2 = ?";
            Object[] updateValues = { "new_value", "value_condition" };
            int rowsUpdated = Jdbc2.executeUpdate(updateSql, updateValues);
            System.out.println("Rows updated: " + rowsUpdated);

            // Câu lệnh DELETE với tham số
            String deleteSql = "DELETE FROM table_name WHERE column1 = ?";
            Object[] deleteValues = { "value1" };
            int rowsDeleted = Jdbc2.executeUpdate(deleteSql, deleteValues);
            System.out.println("Rows deleted: " + rowsDeleted);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
