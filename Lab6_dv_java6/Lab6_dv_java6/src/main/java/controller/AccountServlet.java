package controller;

import java.io.IOException;

import javax.mail.MessagingException;

import org.apache.commons.beanutils.BeanUtils;

import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.EmailUtil;
import util.UserDAO;

@WebServlet({ "/account/sign-up", "/account/sign-in", "/account/sign-out", "/account/edit-profile",
		"/account/forgot-password", "/account/change-password" })
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();

		if (uri.endsWith("sign-in")) {
			this.doSignIn(req, resp);
		} else if (uri.endsWith("sign-up")) {
			this.doSignUp(req, resp);
		} else if (uri.endsWith("sign-out")) {
			this.doSignOut(req, resp);
		} else if (uri.endsWith("edit-profile")) {
			this.doEditProfile(req, resp);
		} else if (uri.endsWith("forgot-password")) {
			this.doForgotPassword(req, resp);
		} else if (uri.endsWith("change-password")) {
			try {
				this.doChangePassword(req, resp);
			} catch (ServletException | IOException | MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Resource not available.");
		}
	}

	private void doSignIn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getMethod().equalsIgnoreCase("POST")) {
			String username = req.getParameter("username");
			String password = req.getParameter("password");

			try {
				UserDAO dao = new UserDAO();
				User user = dao.findById(username);
				if (user == null) {
					req.setAttribute("message", "Tài khoản không tồn tại!");
				} else if (!user.getPassword().equals(password)) {
					req.setAttribute("message", "Sai mật khẩu!");
				} else {
					req.getSession().setAttribute("user", user);
					req.setAttribute("message", "Đăng nhập thành công!");
					resp.sendRedirect(req.getContextPath() + "/views/user.jsp");
					return;
				}
			} catch (Exception e) {
				req.setAttribute("message", "Lỗi hệ thống khi đăng nhập!");
			}
		}
		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}

	private void doSignUp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getMethod().equalsIgnoreCase("POST")) {
			try {
				User newUser = new User();
				BeanUtils.populate(newUser, req.getParameterMap());

				UserDAO dao = new UserDAO();
				dao.create(newUser);
				req.setAttribute("message", "Đăng ký thành công!");
			} catch (Exception e) {
				req.setAttribute("message", "Lỗi hệ thống khi đăng ký!");
			}
		}
		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}

	private void doSignOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().invalidate();
		resp.sendRedirect(req.getContextPath() + "/login.jsp");
	}

	private void doEditProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = (User) req.getSession().getAttribute("user");

		if (user == null) {
			resp.sendRedirect(req.getContextPath() + "/login.jsp");
			return;
		}

		if (req.getMethod().equalsIgnoreCase("POST")) {
			try {
				// Chỉ cập nhật các thuộc tính được phép: email, password, fullname
				String email = req.getParameter("email");
				String password = req.getParameter("password");
				String fullname = req.getParameter("fullname");

				user.setEmail(email);
				user.setPassword(password);
				user.setFullname(fullname);

				// Lưu cập nhật vào database
				UserDAO dao = new UserDAO();
				dao.update(user);

				// Cập nhật session
				req.getSession().setAttribute("user", user);

				req.setAttribute("message", "Cập nhật thông tin thành công!");
			} catch (Exception e) {
				req.setAttribute("message", "Lỗi hệ thống khi cập nhật thông tin!");
			}
		}

		// Chuyển tiếp đến trang JSP để hiển thị thông tin hiện tại
		req.getRequestDispatcher("/views/edit-profile.jsp").forward(req, resp);
	}

	private void doForgotPassword(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (req.getMethod().equalsIgnoreCase("POST")) {
			String email = req.getParameter("email");
			try {
				UserDAO dao = new UserDAO();
				User user = dao.findByEmail(email); // Implement this method in UserDAO
				if (user == null) {
					req.setAttribute("message", "Email không tồn tại trong hệ thống!");
				} else {
					// Send password to user's email
					String subject = "Your Account Password";
					String message = "Xin chào " + user.getFullname() + ",\n\nMật khẩu của bạn là: "
							+ user.getPassword() + "\n\nVui lòng không chia sẻ mật khẩu này với bất kỳ ai!";
					EmailUtil.sendEmail(email, subject, message); // Implement EmailUtil class
					req.setAttribute("message", "Mật khẩu đã được gửi đến email của bạn!");
				}
			} catch (Exception e) {
				req.setAttribute("message", "Lỗi hệ thống khi xử lý yêu cầu quên mật khẩu!");
			}
		}
		req.getRequestDispatcher("/forgot-password.jsp").forward(req, resp);
	}

	private void doChangePassword(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException, MessagingException {
	    User user = (User) req.getSession().getAttribute("user");

	    if (user == null) {
	        resp.sendRedirect(req.getContextPath() + "/login.jsp");
	        return;
	    }

	    String step = req.getParameter("step");
	    if ("sendOtp".equals(step)) {
	        // Gửi OTP qua email
	        String otp = generateOtp();
	        req.getSession().setAttribute("otp", otp);
	        EmailUtil.sendEmail(user.getEmail(), "OTP for Password Change",
	                "Mã OTP của bạn là: " + otp + "\n\nVui lòng không chia sẻ mã này với bất kỳ ai.");
	        req.setAttribute("message", "OTP đã được gửi đến email của bạn!");
	        req.getRequestDispatcher("/views/change-password.jsp?step=verifyOtp").forward(req, resp);

	    } else if ("verifyOtp".equals(step)) {
	        // Xác thực OTP và cập nhật mật khẩu
	        String otp = (String) req.getSession().getAttribute("otp");
	        String inputOtp = req.getParameter("otp");
	        String newPassword = req.getParameter("newPassword");

	        if (otp != null && otp.equals(inputOtp)) {
	            user.setPassword(newPassword);
	            UserDAO dao = new UserDAO();
	            dao.update(user);
	            req.getSession().removeAttribute("otp");
	            req.setAttribute("message", "Mật khẩu đã được thay đổi thành công!");
	            req.getRequestDispatcher("/views/change-password.jsp").forward(req, resp);
	        } else {
	            req.setAttribute("message", "Mã OTP không hợp lệ!");
	            req.getRequestDispatcher("/views/change-password.jsp?step=verifyOtp").forward(req, resp);
	        }
	    }
	}




	// Phương thức sinh mã OTP ngẫu nhiên
	private String generateOtp() {
		int otp = (int) (Math.random() * 900000) + 100000; // Random 6 chữ số
		return String.valueOf(otp);
	}

}
