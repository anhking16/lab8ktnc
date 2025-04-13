package ServiceImpl;

import java.sql.Timestamp;
import java.util.List;

import dao.HistoryDao;
import entily.History;
import entily.User;
import entily.Video;
import impl.HistoryDaoImpl;
import service.HistoryService;
import service.VideoService;
public class HistoryServiceImpl implements HistoryService{

	private HistoryDao dao;
	private VideoService videoService = new VideoServiceImpl();
		
			public HistoryServiceImpl() {
	dao = new HistoryDaoImpl();
}
	@Override
	public List<History> findByUser(String username) {
		// TODO Auto-generated method stub
		return dao.findByUser(username);
	}

	@Override
	public List<History> findByUserAndIsLiked(String username) {
		// TODO Auto-generated method stub
		return dao.findByUserAndIsLiked(username);
	}

	@Override
	public History findByUserIdAndVideoId(Integer userId, Integer videoId) {
		// TODO Auto-generated method stub
		return dao.findByUserIdAndVideoId(userId, videoId);
	}

	@Override
	public History create(User users, Video video) {
	    // Tìm lịch sử tồn tại
	    History exisHistory = findByUserIdAndVideoId(users.getId(), video.getId());
	    
	    // Nếu chưa có lịch sử, tạo đối tượng mới
	    if (exisHistory == null) {
	        exisHistory = new History(); // Khởi tạo đối tượng mới
	        exisHistory.setUser(users); // Gán người dùng
	        exisHistory.setVideo(video); // Gán video
	        exisHistory.setViewedDate(new Timestamp(System.currentTimeMillis())); // Gán thời gian xem
	        exisHistory.setIsLiked(Boolean.FALSE); // Mặc định chưa thích
	        return dao.create(exisHistory); // Lưu vào cơ sở dữ liệu
	    }
	    
	    // Nếu đã có lịch sử, trả về lịch sử hiện tại
	    return exisHistory;
	}


	@Override
	public boolean updateLikeOrUnlike(User users, String videoHref) {
	    // Tìm video qua href
	    Video video = videoService.findByHref(videoHref);
	    // Tìm lịch sử tồn tại
	    History existHistory = findByUserIdAndVideoId(users.getId(), video.getId());
	 
	    // Cập nhật trạng thái like hoặc unlike
	    if (existHistory.getIsLiked() == Boolean.FALSE) {
	        existHistory.setIsLiked(Boolean.TRUE);
	        existHistory.setLikeDate(new Timestamp(System.currentTimeMillis()));
	    } else {
	        existHistory.setIsLiked(Boolean.FALSE); // Set về không thích
	        existHistory.setLikeDate(null); // Xóa ngày thích
	    }
	    // Lưu cập nhật vào cơ sở dữ liệu
	    History updateHistory = dao.update(existHistory);
	    return updateHistory != null ? true : false; // Trả về kết quả cập nhật
	}

	
	
}
