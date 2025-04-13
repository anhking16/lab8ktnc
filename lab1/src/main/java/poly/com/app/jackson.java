package poly.com.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
public class jackson {

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
demo2();
	}

	private static void demo2() throws Exception {
		// TODO Auto-generated method stub
		String path ="E:\\eclipseluu\\lab1\\src\\main\\resources\\studens.json";
		ObjectMapper mapper = new ObjectMapper();
		JsonNode students = mapper.readTree(new File(path));
		students.iterator().forEachRemaining(student -> {
		System.out.println(">>Name:"+ student.get("name").asText());
		});
	}

	private static void demo1() throws Exception {
		// TODO Auto-generated method stub
		String json ="E:\\eclipseluu\\lab1\\src\\main\\resources\\studen.json";
				
				
				/*"{\r\n" +
	            "   \"name\": \"Nguyễn Văn Tèo\",\r\n" +
	            "   \"gender\": true,\r\n" +
	            "   \"marks\": 7.5,\r\n" +
	            "   \"contact\": {\r\n" + // Đảm bảo mở object contact đúng cách
	            "       \"email\": \"teonv@gmail.com\",\r\n" +
	            "       \"phone\": \"0913745789\"\r\n" + // Không có dấu phẩy ở cuối cùng của object
	            "   },\r\n" + // Đóng object contact và có dấu phẩy để tiếp tục object JSON
	            "   \"subjects\": [\"WEB205\", \"COM108\"]\r\n" + // Không có lỗi dấu phẩy
	            "}";*/
		ObjectMapper mapper = new ObjectMapper();
        JsonNode student = mapper.readTree(new File(json));

        System.out.println(">> Name: " + student.get("name").asText());
        System.out.println(">> Marks: " + student.get("marks").asDouble());
        System.out.println(">> Gender: " + student.get("gender").asBoolean());

        JsonNode contact = student.get("contact");
        System.out.println(">> Email: " + contact.get("email").asText());
        System.out.println(">> Phone: " + contact.get("phone").asText());

        student.get("subjects").forEach(subject -> {
            System.out.println(">> Subject: " + subject.asText());
        });
}
}
