package poly.com.Controller;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import poly.com.bean.Student;

@Controller
public class UtilityController {
    @RequestMapping("/demo/utilities")
    public String utilities(Model model) throws Exception {
        // Đọc file JSON từ classpath
        File file = new ClassPathResource("students.json").getFile();
        
        // Chuyển đổi JSON sang List<Student>
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Student>> type = new TypeReference<List<Student>>() {};
        List<Student> list = mapper.readValue(file, type);

        // Đưa danh sách sinh viên vào model
        model.addAttribute("dssv", list);
        model.addAttribute("now", new Date());
        
        return "utilities";
    }
}
