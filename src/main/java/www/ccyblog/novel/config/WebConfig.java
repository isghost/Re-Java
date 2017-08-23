package www.ccyblog.novel.config;

import lombok.extern.log4j.Log4j;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import www.ccyblog.novel.common.logs.LoginAspect;
import www.ccyblog.novel.common.service.LoginService;

import java.io.IOException;

/**
 * Created by Administrator on 2017/7/16.
 * spring mvc的初始化
 */
@Configuration
@EnableWebMvc
@ComponentScan("www.ccyblog.novel")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableCaching
@Log4j
public class WebConfig extends WebMvcConfigurerAdapter{
    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }


    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        super.configureDefaultServletHandling(configurer);
        configurer.enable();
    }

    @Bean
    MultipartResolver multipartResolver() throws IOException{
        return new StandardServletMultipartResolver();
    }

}
