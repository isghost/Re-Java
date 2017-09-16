package www.ccyblog.novel.modules.images.dao;

import org.springframework.stereotype.Repository;
import www.ccyblog.novel.modules.images.entity.ImageDownloadInfo;

/**
 * Created by isghost on 2017/9/6.
 */
@Repository
public interface ImageDownloadDao {
    int insert(ImageDownloadInfo imageDownloadInfo);
    int update(ImageDownloadInfo imageDownloadInfo);
    ImageDownloadInfo getInfoById(int id);

}
