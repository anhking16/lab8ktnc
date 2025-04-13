package poly.com;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/students") // Định nghĩa URL gốc
public class StudentRestController {

    @Autowired
    StudentDao dao; // Inject DAO để truy vấn dữ liệu

    // API lấy danh sách tất cả sinh viên
    @GetMapping
    public List<Student> getAll() {
        return dao.findAll();
    }

    // API cập nhật thông tin sinh viên theo email
    @PutMapping("/{email}")
    public Student updateStudent(@PathVariable("email") String email, @RequestBody Student student) {
        if (!dao.existsById(email)) { 
            throw new RuntimeException("Không tìm thấy sinh viên có email: " + email);
        }
        return dao.save(student);
    }

    // API xóa sinh viên theo email
    @DeleteMapping("/{email}")
    public void deleteStudent(@PathVariable("email") String email) {
        if (!dao.existsById(email)) {
            throw new RuntimeException("Không tìm thấy sinh viên có email: " + email);
        }
        dao.deleteById(email);
    }
    
    
    @PostMapping("/rest/students")
    public Student post(@RequestBody Student student) {
    dao.save(student);
    return student;

    }
    
    @PutMapping("/rest/students/{email}")
    public Student put(@PathVariable("email") String email, @RequestBody Student student) {
    dao.save(student);
    return student;

    }
}
