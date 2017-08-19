package www.ccyblog.novel.modules.images.web;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import www.ccyblog.novel.modules.images.service.UserImageService;

import java.util.*;

/**
 * Created by isghost on 2017/8/10.
 */
@Log4j
@Controller
@RequestMapping("/image")
public class ImagesController {
    private final String IMAGE_INFO_CACHE_NAME = "otherCollection";
    @Autowired
    UserImageService userImageService;
    @RequestMapping("/upload1")
    @RequiresRoles("user")
    @CacheEvict(value = IMAGE_INFO_CACHE_NAME, allEntries = true)
    public @ResponseBody Map uploadFile(@RequestPart MultipartFile image){
        HashMap<String, Boolean> hashMap = new HashMap<String, Boolean>();
        boolean result = userImageService.saveImage(image);
        hashMap.put(image.getOriginalFilename(), result);

        return hashMap;
    }


    @RequestMapping("/imagesUpload")
    @RequiresRoles("user")
    public String imagesView(){
        return "imagesUpload";
    }

    @RequiresRoles("user")
    @RequestMapping("/collections.json")
    public @ResponseBody String getCollectionImages(@RequestParam(defaultValue = "1") int pageNum){
        return userImageService.getCollectionInfo(pageNum);
    }

    @RequiresRoles("user")
    @RequestMapping("/otherCollection.json")
    @Cacheable(value = IMAGE_INFO_CACHE_NAME, key = "#pageNum")
    public @ResponseBody String getOtherUserImages(@RequestParam(defaultValue = "1")int pageNum){
        return userImageService.getOtherUserImages(pageNum);
    }

    @RequiresRoles("user")
    @RequestMapping("/imageView")
    public String showImageView(){
        return "imagesView";
    }
}
