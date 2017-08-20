package www.ccyblog.novel.modules.index.web;

import lombok.extern.log4j.Log4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by ccy on 2017/7/31.
 * 首页
 */
@Log4j
@Controller
public class IndexController {
    @Autowired
    RedisTemplate redisTemplate;
    @RequestMapping(value={"/index", "/", ""})
    public String showIndex(Model model){
        Subject currentUser = SecurityUtils.getSubject();
        if(currentUser.isAuthenticated()){
            model.addAttribute("login", true);
        }
        return "index";
    }

    @RequestMapping(value={"/subpage"})
    public String showSubpage(){
        return "subpage";
    }
}
