package poly.com.Controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import poly.com.bean.Student;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Student2Controller {

    @RequestMapping("/student/list")
    public String list(Model model, @RequestParam("index") Optional<Integer> index) throws IOException {
        File file = new ClassPathResource("Students.json").getFile();
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Student>> type = new TypeReference<List<Student>>() {};
        List<Student> list = mapper.readValue(file, type);

        model.addAttribute("sv", list.get(index.orElse(0)));
        model.addAttribute("dssv", list);
        
        return "student/list";
    }
}
