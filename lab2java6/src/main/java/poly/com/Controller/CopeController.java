package poly.com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CopeController {
    @Autowired
    HttpServletRequest request;
    
    @Autowired
    HttpSession session;
    
    @Autowired
    ServletContext servletContext;

    @RequestMapping("/scope")
    public String hello(Model model) {
        model.addAttribute("a", "I am in model");
        request.setAttribute("b", "I am in request");
        session.setAttribute("c", "I am in session");
        servletContext.setAttribute("d", "I am in application");
        return "scope";
    }
}
