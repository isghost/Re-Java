package www.ccyblog.novel.modules.images.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import www.ccyblog.novel.modules.images.entity.UserImage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by isghost on 2017/8/12.
 * 用户图片DAO
 */
@Repository
public interface UserImageDao {
    int insert(UserImage userImage);
    List<String> select(List<Long> list);
    Integer selectOne(String imageName);
    List<String> recentImages(@Param("begin") int begin, @Param("num") int num);
    int getNum();
//    List<String> recentImages(int begin, int num);
}
