package poly.com.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import poly.com.bean.Student;

@Controller
public class HomeController {
@RequestMapping("/home/index")
public String index(Model model) throws Exception {
model.addAttribute("mejssage", "Welcome to Thymeleaf");
ObjectMapper mapper = new ObjectMapper();
String path ="E:\\eclipseluu\\lab2java6\\src\\main\\resources\\Student.json";
Student student = mapper.readValue(new File(path), Student.class);
model.addAttribute("sv", student);
return "home/index";

}

}