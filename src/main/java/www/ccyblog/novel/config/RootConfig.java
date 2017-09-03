package www.ccyblog.novel.config;

import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by Administrator on 2017/7/16.
 * mysql redis rabbitmq等内容的初始化
 */
@Configuration
@ComponentScan(basePackages = {"www.ccyblog.novel"}, excludeFilters = {
        @Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class),
        @Filter(type = FilterType.ANNOTATION, value = Controller.class)
})
@ImportResource(value = {"WEB-INF/dispatcher-servlet.xml", "WEB-INF/applicationContext.xml"})
public class RootConfig {
}
