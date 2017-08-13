package www.ccyblog.novel.modules.images.dao;

import org.springframework.stereotype.Repository;
import www.ccyblog.novel.modules.images.entity.UserImage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by isghost on 2017/8/12.
 */
@Repository
public interface UserImageDao {
    int insert(UserImage userImage);
    List<String> select(List<Long> list);
}
