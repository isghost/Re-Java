package www.ccyblog.novel.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

/**
 * Created by Administrator on 2017/7/16.
 * spring mvc 初始化类
 */
public class NovelWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?> []{RootConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class<?> []{WebConfig.class, RMIServerConfig.class};
    }

    protected String[] getServletMappings() {
        return new String[]{"/", "*.service"};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
//        registration.setMultipartConfig(new MultipartConfigElement("D:\\web\\workspace\\tmp", 4194304, 4194304, 0));
        registration.setMultipartConfig(new MultipartConfigElement("/root/tomcat/tomcat_tmp", 4194304, 4194304, 0));
    }

}
