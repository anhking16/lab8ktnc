package poly.com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import poly.com.model.Departments;

public class DepartmentInterfaceDAO extends Connectdao implements DAOInterface<Departments, String> {

	@Override
	public List<Departments> selectAll() {
		 List<Departments> departments = new ArrayList<>();
		    String sql = "SELECT * FROM Departments";
		    try {
		        Statement stmt = conn.createStatement();
		        ResultSet rs = stmt.executeQuery(sql);
		        while (rs.next()) {
		            String id = rs.getString("id");
		            String name = rs.getString("Name");
		            String description = rs.getString("Description");
		            departments.add(new Departments(id, name, description));
		        }
		    } catch (Exception ex) {
		        ex.printStackTrace();
		    }
		    return departments;
	}

	@Override
	public Departments selectById(String id) {
		Departments department= null;
	    String sql = "SELECT * FROM Departments where id = ?";
	    try {
	    	PreparedStatement ps = conn.prepareStatement(sql);
	    	ps.setString(1, id);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            String name = rs.getString("Name");
	            String description = rs.getString("Description");
	            department =  new Departments(id, name, description);
	        }
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        
	    }
	    return department;
	}
	
	@Override
	public void printAll() {
        String sql = "SELECT * FROM Departments";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (!rs.isBeforeFirst()) {
                System.out.println("Không có phòng ban nào!");
                return;
            }
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("Name");
                String description = rs.getString("Description");
                System.out.println("ID: " + id + ", Name: " + name + ", Description: " + description);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

	@Override
	public int insert(Departments entity) {
		String sql = "INSERT INTO Departments (id, Name, Description) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, entity.getId());
            ps.setString(2, entity.getName());
            ps.setString(3, entity.getDescription());
            int rowInserted = ps.executeUpdate();
            System.out.println("Thêm phòng ban thành công!");
            return rowInserted;
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
	}

	@Override
	public int update(Departments entity) {
		String sql = "UPDATE Departments SET Name = ?, Description = ? WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getDescription());
            ps.setString(3, entity.getId());
            int rowUpdated = ps.executeUpdate();
            System.out.println("Cập nhật phòng ban thành công!");
            return rowUpdated;
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
	}

	@Override
	public void delete(String id) {
		String sql = "DELETE FROM Departments WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
            System.out.println("Xóa phòng ban thành công!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}

}
