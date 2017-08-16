package www.ccyblog.novel.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

/**
 * Created by Administrator on 2017/7/16.
 */
public class NovelWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?> []{RootConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class<?> []{WebConfig.class};
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
//        registration.setMultipartConfig(new MultipartConfigElement("D:\\web\\workspace\\tmp", 1000000, 4194304, 0));
        registration.setMultipartConfig(new MultipartConfigElement("/tmp/webtmp", 1000000, 4194304, 0));
    }

}
