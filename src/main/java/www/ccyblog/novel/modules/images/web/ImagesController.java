package www.ccyblog.novel.modules.images.web;

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

import java.util.HashMap;
import java.util.Map;

/**
 * Created by isghost on 2017/8/10.
 * 用户图片Controller
 */
@Log4j
@Controller
@RequestMapping("/image")
public class ImagesController {
    private final String IMAGE_INFO_CACHE_NAME = "otherCollection";
    @Autowired
    UserImageService userImageService;

    /**
     * 上传图片
     * @param image 图片信息
     * @return 图片上传结果
     */
    @RequestMapping("/upload1")
    @RequiresRoles("user")
    @CacheEvict(value = IMAGE_INFO_CACHE_NAME, allEntries = true)
    public @ResponseBody Map uploadFile(@RequestPart MultipartFile image){
        HashMap<String, Boolean> hashMap = new HashMap<String, Boolean>();
        boolean result = userImageService.saveImage(image);
        hashMap.put(image.getOriginalFilename(), result);

        return hashMap;
    }

    /**
     * 收藏图片
     * @param imageName 图片名称
     * @return
     */
    @RequestMapping("/collectionImage.json")
    @RequiresRoles("user")
    public @ResponseBody Map collectionImage(@RequestParam String imageName){
        HashMap<String, Boolean> hashMap = new HashMap<String, Boolean>();
        boolean result = userImageService.addUserCollection(imageName);
        hashMap.put(imageName, result);
        hashMap.put("flag", result);
        return hashMap;
    }

    /**
     * 显示图片上传界面
     * @return 上传图片的视图名称
     */
    @RequestMapping("/imagesUpload")
    @RequiresRoles("user")
    public String imagesView(){
        return "imagesUpload";
    }

    /**
     * 获得自己上传的图片
     * @param pageNum 页数
     * @return 图片信息
     */
    @RequiresRoles("user")
    @RequestMapping("/collections.json")
    public @ResponseBody String getCollectionImages(@RequestParam(defaultValue = "1") int pageNum){
        return userImageService.getCollectionInfo(pageNum);
    }

    /**
     * 获得其他用户上传的图片
     * @param pageNum 页数
     * @return 图片信息
     */
//    @RequiresRoles("user")
    @RequestMapping("/otherCollection.json")
    @Cacheable(value = IMAGE_INFO_CACHE_NAME, key = "#pageNum")
    public @ResponseBody String getOtherUserImages(@RequestParam(defaultValue = "1")int pageNum){
        return userImageService.getOtherUserImages(pageNum);
    }

    /**
     * 图片浏览界面
     * @return 图片浏览视图名称
     */
    @RequiresRoles("user")
    @RequestMapping("/imageView")
    public String showImageView(){
        return "imagesView";
    }

    /**
     * 图片浏览界面
     * @return 图片浏览视图名称
     */
    @RequestMapping("/otherImageView")
    public String showOtherImageView(){
        return "imagesView";
    }
}
