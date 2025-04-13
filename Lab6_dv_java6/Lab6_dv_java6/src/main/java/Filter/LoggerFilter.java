package Filter;

import java.io.IOException;
import java.util.Date;



import Model.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//@WebFilter("/admin/*")
public class LoggerFilter implements HttpFilter{

	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		User user = (User) req.getSession().getAttribute("user");
		String uri = req.getRequestURI();
		Date time = new Date();
		// ghi nhan user, uri, time vao CSDL foac file
		chain.doFilter(req, resp);
	}
	
}
