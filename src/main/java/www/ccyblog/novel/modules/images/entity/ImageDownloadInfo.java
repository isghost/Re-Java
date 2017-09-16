package www.ccyblog.novel.modules.images.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by isghost on 2017/9/6.
 */
@Data
@AllArgsConstructor
public class ImageDownloadInfo {
    private int id;
    private String imageUrl;
    private int uid;
    private int status;
    public ImageDownloadInfo(String imageUrl, int uid, int status){
        this(0, imageUrl, uid, status);
    }
}
