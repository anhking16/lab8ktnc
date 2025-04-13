package Filter;

import java.io.IOException;

import Model.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebFilter({"/user/index","/user/edit/*","/video/like/*","/video/share/*","/account/change-password","/admin/*"})
public class AuthFilter implements HttpFilter {

	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		String uri = req.getRequestURI();
		User user = (User)req.getSession().getAttribute("user");
		String error = "";
		if(user==null) {//chua dang nhap
			error = resp.encodeURL("Vui Long Dang Nhap");
			
		}else if (!user.getAdmin() && uri.contains("/admin/")) {//kg phai admin
			error = resp.encodeURL("Vui Long Dang Nhap Voi Vai Tro Admin");
		}
		if (!error.isEmpty()) {// truy cap kg hop le
			req.getSession().setAttribute("securi", uri);
		}else {//truy cap hop le
			chain.doFilter(req, resp);
		}
		
		
		
	}

}
