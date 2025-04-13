package impl;

import java.util.List;

import dao.StatsDao;
import dto.VideoLikedInfo;
import service.StatsService;

public class StatsServiceImpl implements StatsService{
private StatsDao statsDao;

public StatsServiceImpl() {
	statsDao = new StatsDaoImpl();
	}



	@Override
	public List<VideoLikedInfo> findVideoLikedInfo() {
		// TODO Auto-generated method stub
		return statsDao.findVideoLikedInfo();
	}

}
