package service;

import java.util.List;

import entily.History;
import entily.User;
import entily.Video;

public interface HistoryService {
	List<History> findByUser(String username);
	List<History> findByUserAndIsLiked(String username);
	History findByUserIdAndVideoId(Integer userId, Integer videoId);
	History create(User users, Video video);
	boolean updateLikeOrUnlike(User users, String videoHref);
}
