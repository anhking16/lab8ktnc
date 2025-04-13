package poly.com.sql;

import java.sql.ResultSet;

import poly.com.connect.Jdbc3;

public class TestJdbc3 {
	public static void main(String[] args) {
        try {
            // Gọi thủ tục lưu trữ để truy vấn dữ liệu
            String sql = "{CALL spSelect(?, ?)}";  // Ví dụ với thủ tục spSelect có 2 tham số
            Object[] values = { "parameter1", "parameter2" };  // Truyền các tham số

            ResultSet resultSet = Jdbc3.executeQuery(sql, values);

            // Duyệt qua kết quả truy vấn
            while (resultSet.next()) {
                String value = resultSet.getString("column_name");  // Thay thế 'column_name' bằng tên cột thật
                System.out.println(value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        try {
            // Gọi thủ tục lưu trữ để thêm dữ liệu
            String insertSql = "{CALL spInsert(?, ?)}";  // Ví dụ với thủ tục spInsert có 2 tham số
            Object[] insertValues = { "value1", "value2" };  // Truyền các tham số
            int rowsInserted = Jdbc3.executeUpdate(insertSql, insertValues);
            System.out.println("Rows inserted: " + rowsInserted);

            // Gọi thủ tục lưu trữ để cập nhật dữ liệu
            String updateSql = "{CALL spUpdate(?, ?)}";  // Ví dụ với thủ tục spUpdate có 2 tham số
            Object[] updateValues = { "new_value", "condition_value" };  // Truyền các tham số
            int rowsUpdated = Jdbc3.executeUpdate(updateSql, updateValues);
            System.out.println("Rows updated: " + rowsUpdated);

            // Gọi thủ tục lưu trữ để xóa dữ liệu
            String deleteSql = "{CALL spDelete(?)}";  // Ví dụ với thủ tục spDelete có 1 tham số
            Object[] deleteValues = { "condition_value" };  // Truyền các tham số
            int rowsDeleted = Jdbc3.executeUpdate(deleteSql, deleteValues);
            System.out.println("Rows deleted: " + rowsDeleted);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
