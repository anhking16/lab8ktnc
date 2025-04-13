package impl;

import java.util.List;

import dao.AbstractDao;
import dao.HistoryDao;
import entily.History;
import entily.User;

public class HistoryDaoImpl extends AbstractDao<History> implements HistoryDao{

	@Override
	public List<History> findByUser(String username) {
		// TODO Auto-generated method stub
		String sql="SELECT o FROM History o WHERE  o.users.username = ?0 AND o.video.isActive = 1"
				+ " ORDER BY o.viewedDate DESC";
		return super.findMany(History.class, sql, username);
	}

	@Override
	public List<History> findByUserAndIsLiked(String username) {
		// TODO Auto-generated method stub
		String sql="SELECT o FROM History o WHERE o.users.username = ?0 AND o.isLiked = 1 "
				+ " AND o.video.isActive = 1"
				+ " ORDER BY o.viewedDate DESC";
		return super.findMany(History.class, sql, username);
	}

	@Override
	public History findByUserIdAndVideoId(Integer userId, Integer videoId) {
		// TODO Auto-generated method stub
		String sql ="SELECT o FROM History o WHERE o.users.id = ?0 AND o.video.id = ?1"
				+" AND o.video.isActive = 1";
		return super.findOne(History.class, sql, userId, videoId);
	}

	

}
