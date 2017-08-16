package www.ccyblog.novel.config;

import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by Administrator on 2017/7/16.
 */
@Configuration
@ComponentScan(basePackages = {"www.ccyblog.novel"}, excludeFilters = {
        @Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)
})
@ImportResource("WEB-INF/dispatcher-servlet.xml")
public class RootConfig {
}
