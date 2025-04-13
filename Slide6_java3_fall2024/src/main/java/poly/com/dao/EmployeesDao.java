
package poly.com.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date; // Import java.sql.Date cho thuộc tính ngày sinh
import java.util.ArrayList;
import java.util.List;

import poly.com.model.Employees;

public class EmployeesDao extends Connectdao {

    // Thêm nhân viên mới
    public void insertEmployee(String id, String password, String fullname, String photo, boolean gender, Date birthday, double salary, String departmentId) {
        String sql = "INSERT INTO Employees (Id, Password, Fullname, Photo, Gender, Birthday, Salary, DepartmentId) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, password);
            ps.setString(3, fullname);
            ps.setString(4, photo);
            ps.setBoolean(5, gender);
            ps.setDate(6, birthday); // Sử dụng java.sql.Date cho ngày sinh
            ps.setDouble(7, salary);
            ps.setString(8, departmentId);
            ps.executeUpdate();
            System.out.println("Thêm nhân viên thành công!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Sửa thông tin nhân viên
    public void updateEmployee(String id, String password, String fullname, String photo, boolean gender, Date updateBirthday, double salary, String departmentId) {
        String sql = "UPDATE Employees SET Password = ?, Fullname = ?, Photo = ?, Gender = ?, Birthday = ?, Salary = ?, DepartmentId = ? WHERE Id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, password);
            ps.setString(2, fullname);
            ps.setString(3, photo);
            ps.setBoolean(4, gender);
            ps.setDate(5, new java.sql.Date(updateBirthday.getTime())); // Chuyển đổi java.util.Date thành java.sql.Date
            ps.setDouble(6, salary);
            ps.setString(7, departmentId);
            ps.setString(8, id);
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Cập nhật nhân viên thành công!");
            } else {
                System.out.println("Không tìm thấy nhân viên để cập nhật.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Xóa nhân viên
    public void deleteEmployee(String id) {
        String sql = "DELETE FROM Employees WHERE Id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
            System.out.println("Xóa nhân viên thành công!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Tìm nhân viên theo ID
    public Employees findEmployeeById(String id) {
        String sql = "SELECT * FROM Employees WHERE Id = ?";
        Employees employee = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                employee = new Employees();
                employee.setId(rs.getString("Id"));
                employee.setPassword(rs.getString("Password"));
                employee.setFullname(rs.getString("Fullname"));
                employee.setPhoto(rs.getString("Photo"));
                employee.setGender(rs.getBoolean("Gender"));
                employee.setBirthday(rs.getDate("Birthday")); // Lấy java.sql.Date từ cơ sở dữ liệu
                employee.setSalary(rs.getDouble("Salary"));
                employee.setDepartmentId(rs.getString("DepartmentId"));
            } else {
                System.out.println("Không tìm thấy nhân viên!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return employee;
    }

    // Lấy tất cả nhân viên
    public List<Employees> getAllEmployees() {
		List<Employees> employees = new ArrayList<>();
		String sql = "SELECT * FROM Employees";
		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				Employees employee = new Employees();
				employee.setId(rs.getString("Id"));
				employee.setPassword(rs.getString("Password"));
				employee.setFullname(rs.getString("Fullname"));
				employee.setPhoto(rs.getString("Photo"));
				employee.setGender(rs.getBoolean("Gender"));
				employee.setBirthday(rs.getDate("Birthday")); // Lấy java.sql.Date từ cơ sở dữ liệu
				employee.setSalary(rs.getDouble("Salary"));
				employee.setDepartmentId(rs.getString("DepartmentId"));

				employees.add(employee); // Thêm nhân viên vào danh sách
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return employees;
    }

    // In danh sách tất cả nhân viên
    public void printAllEmployees() {
        List<Employees> employees = getAllEmployees();
        if (employees.isEmpty()) {
            System.out.println("Không có nhân viên nào!");
        } else {
            for (Employees employee : employees) {
                System.out.println("ID: " + employee.getId() + 
                                   ", Fullname: " + employee.getFullname() + 
                                   ", Department ID: " + employee.getDepartmentId());
            }
        }
    }
}
