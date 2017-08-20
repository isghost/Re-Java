package www.ccyblog.novel.common.logs;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j;

/**
 * Created by isghost on 2017/8/17.
 * 登录消息接收
 */
@Log4j
public class Rabbit {
    public void handlerLoginAlert(JSONObject jsonObject){
        log.info("receive login alert");
    }
}
