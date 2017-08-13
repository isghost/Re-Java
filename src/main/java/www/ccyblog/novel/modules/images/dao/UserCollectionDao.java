package www.ccyblog.novel.modules.images.dao;

import org.springframework.stereotype.Repository;
import www.ccyblog.novel.modules.images.entity.UserCollection;

import java.util.ArrayList;

/**
 * Created by isghost on 2017/8/12.
 */
@Repository
public interface UserCollectionDao {
    int update(UserCollection userCollection);
    UserCollection select(int uid);
}
