package controller;

import java.io.IOException;

import org.apache.commons.beanutils.BeanUtils;
import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.UserDAO;

@WebServlet({ "/user/index", "/user/delete", "/user/create", "/user/update", "/user/edit/*" })
public class UserServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserDAO dao = new UserDAO();
		User user = new User();
		String uri = req.getRequestURI();

		if (uri.contains("edit")) {
			String id = uri.substring(uri.lastIndexOf("/") + 1);
			System.out.print("edit:" + id);
			user = dao.findById(id);

		} else if (uri.contains("create")) {
			try {
				BeanUtils.populate(user, req.getParameterMap());
				dao.create(user);
				req.setAttribute("message", "Them moi thanh cong");
			} catch (Exception e) {
				req.setAttribute("message", "Them moi that bai");
			}
		} else if (uri.contains("update")) {
			try {
				BeanUtils.populate(user, req.getParameterMap());
				dao.update(user);
				req.setAttribute("message", "Update thanh cong");
			} catch (Exception e) {
				req.setAttribute("message", "Update that bai");
			}
		} else if (uri.contains("delete")) {
			try {
				String id = req.getParameter("id");
				dao.remove(id);
				req.setAttribute("message", "Xoa thanh cong");
			} catch (Exception e) {
				req.setAttribute("message", "Xoa that bai");
			}
		}
		req.setAttribute("form", user);
		req.setAttribute("items", dao.findAll());
		req.getRequestDispatcher("/views/user.jsp").forward(req, resp);

	}

}
