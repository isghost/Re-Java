package www.ccyblog.novel.modules.images.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;

/**
 * Created by isghost on 2017/8/12.
 */
@Data
@RequiredArgsConstructor
public class UserImage {
    long imageid;
    String fileName;
}
