package impl;

import java.util.ArrayList;
import java.util.List;

import dao.AbstractDao;
import dao.StatsDao;
import dto.VideoLikedInfo;

public class StatsDaoImpl extends AbstractDao<Object[]> implements StatsDao {

    @Override
    public List<VideoLikedInfo> findVideoLikedInfo() {
        // Câu truy vấn SQL được sửa để đảm bảo cú pháp chính xác
        String sql = "select v.id, v.title, v.href, sum(cast(coalesce(h.isliked, 0) as int)) as totallike "
                   + "from video v "
                   + "left join history h on v.id = h.videoId "
                   + "where v.isActive = 1 "
                   + "group by v.id, v.title, v.href "
                   + "order by sum(cast(coalesce(h.isLiked, 0) as int)) desc";

        // Thực thi truy vấn và lấy kết quả
        List<Object[]> objects = super.findManyByNativeQuery(sql);
        List<VideoLikedInfo> result = new ArrayList<>();

        // Chuyển đổi kết quả sang đối tượng VideoLikedInfo
        objects.forEach(object -> {
            VideoLikedInfo videoLikedInfo = setVideoLikedInfo(object);
            result.add(videoLikedInfo);
        });

        return result;
    }

    private VideoLikedInfo setVideoLikedInfo(Object[] object) {
        VideoLikedInfo videoLikedInfo = new VideoLikedInfo();
        videoLikedInfo.setVideoId((Integer) object[0]); // ID video
        videoLikedInfo.setTitle((String) object[1]);    // Tiêu đề video
        videoLikedInfo.setHref((String) object[2]);     // Liên kết video
        videoLikedInfo.setTotalLike(object[3] == null ? 0 : ((Number) object[3]).intValue()); // Tổng lượt thích
        return videoLikedInfo;
    }
}
