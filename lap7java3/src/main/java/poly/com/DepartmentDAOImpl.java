package poly.com;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAOImpl implements DepartmentDAO {

    @Override
    public List<Department> findAll() {
        String sql = "SELECT * FROM Departments";
        try {
            List<Department> departments = new ArrayList<>();
            ResultSet resultSet = Jdbc.executeQuery(sql);
            while (resultSet.next()) {
                Department department = new Department();
                department.setId(resultSet.getString("Id"));
                department.setName(resultSet.getString("Name"));
                department.setDescription(resultSet.getString("Description"));
                departments.add(department);
            }
            return departments;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Department findById(String id) {
        String sql = "SELECT * FROM Departments WHERE Id = ?";
        try {
            ResultSet resultSet = Jdbc.executeQuery(sql, id);
            if (resultSet.next()) {
                Department department = new Department();
                department.setId(resultSet.getString("Id"));
                department.setName(resultSet.getString("Name"));
                department.setDescription(resultSet.getString("Description"));
                return department;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("Department not found");
    }

    @Override
    public void create(Department department) {
        String sql = "INSERT INTO Departments (Id, Name, Description) VALUES (?, ?, ?)";
        try {
            Jdbc.executeUpdate(sql, department.getId(), department.getName(), department.getDescription());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Department department) {
        String sql = "UPDATE Departments SET Name = ?, Description = ? WHERE Id = ?";
        try {
            Jdbc.executeUpdate(sql, department.getName(), department.getDescription(), department.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM Departments WHERE Id = ?";
        try {
            Jdbc.executeUpdate(sql, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
