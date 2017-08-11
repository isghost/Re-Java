package www.ccyblog.novel.modules.images.web;

import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import www.ccyblog.novel.modules.images.service.CloudStorage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by isghost on 2017/8/10.
 */
@Log4j
@Controller
@RequestMapping("/images")
public class ImagesController {
    @Autowired
    CloudStorage cloudStorage;
    @RequestMapping("/upload1")
    public @ResponseBody Map uploadFile(@RequestPart MultipartFile image){
        HashMap<String, Boolean> hashMap = new HashMap<String, Boolean>();
        hashMap.put("status", true);
        String result = cloudStorage.saveImage(image);
        Object parse = JSON.parse(result);
        return hashMap;
    }


    @RequestMapping("/imagesView")
    public String imagesView(){
        return "imagesView";
    }
}
