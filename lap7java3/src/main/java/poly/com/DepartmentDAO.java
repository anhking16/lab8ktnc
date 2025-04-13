package poly.com;

import java.util.List;

public interface DepartmentDAO {
    List<Department> findAll();
    Department findById(String id);
    void create(Department department);
    void update(Department department);
    void deleteById(String id);
}