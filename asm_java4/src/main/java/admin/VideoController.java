package admin;

import java.io.IOException;
import java.util.List;

import ServiceImpl.VideoServiceImpl;
import constant.SessionAttr;
import entily.User;
import entily.Video;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.VideoService;

@WebServlet(urlPatterns = {"/admin/video"}, name = "VideoControllerOfAdmin")
public class VideoController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5754252296480566573L;
private VideoService videoService = new VideoServiceImpl();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
//		if (currentUser != null && currentUser. getIsAdmin() == Boolean. TRUE) {
	String action = req.getParameter("action");
	switch (action){
		case "view":
			doGetOverView(req, resp);
			break;
		case "delete":
			doGetDelete(req,resp);
	break;
		case "add":
			req.setAttribute("isEdit", false);
			doGetAdd(req,resp);
	break;
		case "edit":
			req.setAttribute("isEdit", true);
			doGetEdit(req,resp);
	break;
	}
	

//		}else {
//			resp.sendRedirect("index");
//		}
			
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
//		if (currentUser != null && currentUser. getIsAdmin() == Boolean. TRUE) {
	String action = req.getParameter("action");
	switch (action){
		case "add":
			doPostAdd(req, resp);
			break;
		case "edit":
			doPostEdit(req, resp);
			break;
	}
	

//		}else {
//			resp.sendRedirect("index");
//		}
			
	}
	
	
	
	
	
	
	
	
	
	private void doGetOverView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Video> videos = videoService.findAll();
		req.setAttribute("videos", videos);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/admin/video-overview.jsp");
		requestDispatcher.forward(req, resp);
	
	
	
	}
	
	
	
	
	
	private void doGetDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		String href = req.getParameter("href");
		Video videoDeleted = videoService.delete(href);
		if (videoDeleted != null) {
			resp.setStatus(204);
			
		}else {
			resp.setStatus(400);
		}
		
		
	
	
	}
	
	private void doGetAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/admin/video-edit.jsp");
		requestDispatcher.forward(req, resp);
	
	
	
	}
	
	private void doPostAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		String title = req.getParameter("title");
		String description = req.getParameter("description");
		String href = req.getParameter("href");
		String poster = req.getParameter("poster");
		
		Video video = new Video();
		video.setTitle(title);
		video.setHref(href);
		video.setDescription(description);
		video.setPoster(poster);

		Video videoReturn = videoService.create(video);
		
		if (videoReturn != null) {
			resp.setStatus(204);
			
		}else {
			resp.setStatus(400);
		}
		
	
	
	
	}
	
	
	
	private void doGetEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String href = req.getParameter("href");
		Video video = videoService.findByHref(href);
		req.setAttribute("video", video);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/admin/video-edit.jsp");
		requestDispatcher.forward(req, resp);
	
	
	
	}
	
	
	
	private void doPostEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		String title = req.getParameter("title");
		String description = req.getParameter("description");
		String href = req.getParameter("href");
		String poster = req.getParameter("poster");
		String hrefOrigin = req.getParameter("hrefOrigin");
		Video video = videoService.findByHref(hrefOrigin);
		video.setTitle(title);
		video.setHref(href);
		video.setDescription(description);
		video.setPoster(poster);

		Video videoReturn = videoService.update(video);
		
		if (videoReturn != null) {
			resp.setStatus(204);
			
		}else {
			resp.setStatus(400);
		}
		
	
		
	
	}
}
