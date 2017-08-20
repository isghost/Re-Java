package www.ccyblog.novel.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import www.ccyblog.novel.common.service.LoginService;

import java.util.Properties;


/**
 * Created by isghost on 2017/8/19.
 * rmi 配置文件
 */
@Configuration
public class RMIServerConfig {

    @Bean
    public HttpInvokerServiceExporter httpInvokerServiceExporter(@Qualifier("loginServiceServer") LoginService loginService){
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(loginService);
        exporter.setServiceInterface(LoginService.class);
        return exporter;
    }

//    @Bean
//    public SimpleUrlHandlerMapping handlerMapping(HttpInvokerServiceExporter httpInvokerServiceExporter){
//        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
//        Properties mappings = new Properties();
////        mappings.setProperty("login.service", "httpInvokerServiceExporter");
//        mappings.put("/login.service", httpInvokerServiceExporter);
//        mapping.setMappings(mappings);
//        mapping.setAlwaysUseFullPath(true);
//        return mapping;
//    }

}
