package poly.com;


import org.springframework.data.jpa.repository.JpaRepository;



public interface StudentDao extends JpaRepository<Student, String>{}