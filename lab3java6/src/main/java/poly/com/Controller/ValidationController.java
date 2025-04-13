package poly.com.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import poly.com.Student2;

@Controller
public class ValidationController {
	@GetMapping("/validation/form")
	public String form(Model model){
	Student2 student = new Student2();
	model.addAttribute("sv", student);
	return "validation/form";
}
	

	@PostMapping("/validation/validate")
	public String save(Model model, @Validated @ModelAttribute("sv") Student2 form, BindingResult errors) {
	    if (errors.hasErrors()) {
	        model.addAttribute("message", "Vui lòng sửa các lỗi sau: ");
	        return "validation/form";
	    }
	    return "validation/success";
	}

}