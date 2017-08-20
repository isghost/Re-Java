package www.ccyblog.novel.common.logs;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by isghost on 2017/8/16.
 * 登录相关的日志记录，以及消息发送
 */
@Log4j
@Aspect
@Component
public class LoginAspect {

    @Autowired
    RabbitTemplate rabbitTemplate;

    // 不推荐使用创建切点的方式，因为idea的自动提示会消失，查找问题会比较麻烦
    @Before("execution (* www.ccyblog.novel.modules.account.web.AccountController.login(..)) && args(username, ..)")
    public void login(String username){
        // 记录在本地
        log.info("username :" + username + " try to login");
        // 发送给需要处理登录信息服务
        Session session = SecurityUtils.getSubject().getSession();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("host", session.getHost());
        jsonObject.put("startTimestamp", session.getStartTimestamp());
        jsonObject.put("username", username);
        jsonObject.put("lastAccessTime", session.getLastAccessTime());
        rabbitTemplate.convertAndSend(jsonObject);
    }

    @Around("execution (* www.ccyblog.novel.modules.account.web.AccountController.login(..)) && args(username, ..)")
    public Object loginAround(ProceedingJoinPoint joinPoint,String username) throws Exception {
        String returnStr = null;
        try {
            returnStr = (String)joinPoint.proceed();
            if(returnStr.equals("redirect:/")){
                log.info("username :" + username + " login success");
            }
            else{
                log.info("username :" + username + " login failed");
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.info("username :" + username + " login error:" + throwable.getMessage());
        }
        return returnStr;
    }

    @AfterThrowing("execution (* www.ccyblog.novel.modules.account.web.AccountController.login(..)) && args(username, ..)")
    public void loginThrowing(String username){
        log.info("username :" + username + " happen to throwing");
    }
}
