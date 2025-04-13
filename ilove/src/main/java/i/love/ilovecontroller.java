package i.love;


	
	import org.springframework.boot.SpringApplication;
	import org.springframework.boot.autoconfigure.SpringBootApplication;
	import org.springframework.stereotype.Controller;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.ResponseBody;

	@SpringBootApplication
	public class ilovecontroller {
	    public static void main(String[] args) {
	        SpringApplication.run(ilovecontroller.class, args);
	    }
	}

	@Controller
	class WebController {
	    @GetMapping("/index")
	    public String home() {
	        return "index";
	    }	   
	}


