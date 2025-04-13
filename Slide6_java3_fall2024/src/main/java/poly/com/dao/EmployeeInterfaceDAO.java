package poly.com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import poly.com.model.Employees;

public class EmployeeInterfaceDAO extends Connectdao implements DAOInterface<Employees, String> {

	@Override
	public List<Employees> selectAll() {
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

	@Override
	public Employees selectById(String id) {
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

	public List<Employees> selectAllWithDepartmentId(String id) {
		List<Employees> employees = new ArrayList<>();
		String sql = "SELECT * FROM Employees where DepartmentId = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
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

	@Override
	public void printAll() {
		List<Employees> employees = this.selectAll();
		if (employees.isEmpty()) {
			System.out.println("Không có nhân viên nào!");
		} else {
			for (Employees employee : employees) {
				System.out.println("ID: " + employee.getId() + ", Fullname: " + employee.getFullname()
						+ ", Department ID: " + employee.getDepartmentId());
			}
		}
	}

	@Override
	public int insert(Employees entity) {
		String sql = "INSERT INTO Employees (Id, Password, Fullname, Photo, Gender, Birthday, Salary, DepartmentId) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, entity.getId());
			ps.setString(2, entity.getPassword());
			ps.setString(3, entity.getFullname());
			ps.setString(4, entity.getPhoto());
			ps.setBoolean(5, entity.isGender());
			ps.setDate(6, entity.getBirthday());
			ps.setDouble(7, entity.getSalary());
			ps.setString(8, entity.getDepartmentId());
			int rowInserted = ps.executeUpdate();
			System.out.println("Thêm nhân viên thành công!");
			return rowInserted;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}

	}

	@Override
	public int update(Employees entity) {
		String sql = "UPDATE Employees SET Password = ?, Fullname = ?, Photo = ?, Gender = ?, Birthday = ?, Salary = ?, DepartmentId = ? WHERE Id = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, entity.getPassword());
			ps.setString(2, entity.getFullname());
			ps.setString(3, entity.getPhoto());
			ps.setBoolean(4, entity.isGender());
			ps.setDate(5, entity.getBirthday());
			ps.setDouble(6, entity.getSalary());
			ps.setString(7, entity.getDepartmentId());
			ps.setString(8, entity.getId());
			int rowsUpdated = ps.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Cập nhật nhân viên thành công!");
			} else {
				System.out.println("Không tìm thấy nhân viên để cập nhật.");
			}
			return rowsUpdated;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return 0;
		}

	}

	@Override
	public void delete(String id) {
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

}
