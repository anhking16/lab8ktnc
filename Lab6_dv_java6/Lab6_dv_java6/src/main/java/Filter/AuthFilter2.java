package Filter;

import java.io.IOException;

import Model.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebFilter("/secure")
public class AuthFilter2 implements HttpFilter {

	@Override
	public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		if (user==null) {
			session.setAttribute("secureUrl", req.getRequestURI());
			System.out.println("AuthFilter2: " +session.getAttribute("secureUrl"));
			resp.sendRedirect(req.getContextPath()+ "/login");
		}else {
			chain.doFilter(req, resp);
		}
		
	}

}
