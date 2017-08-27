package www.ccyblog.novel.common.logs;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import www.ccyblog.novel.modules.account.entity.Account;

import javax.security.auth.Subject;
import java.time.LocalDateTime;

/**
 * Created by isghost on 2017/8/27.
 */
@Log4j
@Aspect
@Component
public class ImageAspect {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public JSONObject getMsg(MultipartFile file){
        Account account = (Account)SecurityUtils.getSubject().getSession().getAttribute("account");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("filename", file.getOriginalFilename());
        jsonObject.put("author", account.getUsername());
        jsonObject.put("time", LocalDateTime.now().toString());
        jsonObject.put("size", file.getSize());
        return jsonObject;
    }
    //
    @Before("execution (* www.ccyblog.novel.modules.images.web.ImagesController.uploadFile(..)) && args(file, ..)")
    public void login(MultipartFile file){
        // 记录在本地
        JSONObject msg = getMsg(file);
        msg.put("status", "before");

        rabbitTemplate.convertAndSend("novel.direct", "novel.alert.queue.uploadImage", msg);
    }

    @AfterThrowing(value = "execution (* www.ccyblog.novel.modules.images.web.ImagesController.uploadFile(..)) && args(file, ..)", throwing = "ex")
    public void loginThrowing(MultipartFile file, Throwable ex){
        JSONObject msg = getMsg(file);
        msg.put("status", "error");
        msg.put("errorInf", ex.toString());

        rabbitTemplate.convertAndSend(msg);
    }
}
