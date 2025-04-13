package poly.com.Controller;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import poly.com.bean.Student;


@Controller
@RequestMapping("/student")
public class StudentController {
    
    @RequestMapping
    public String student(Model model, 
                          @RequestParam("index") Optional<Integer> index) throws Exception {
        
        ObjectMapper mapper = new ObjectMapper();
        String path = "E:\\eclipseluu\\lab2java6\\src\\main\\resources\\Students.json";
        
        TypeReference<List<Student>> type = new TypeReference<List<Student>>() {};
        List<Student> list = mapper.readValue(new File(path), type);

        Student student = list.get(index.orElse(0));
        model.addAttribute("sv", student);
        model.addAttribute("index", index.orElse(0));

        return "student";
    }
}

