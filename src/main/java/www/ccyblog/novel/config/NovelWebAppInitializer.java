package www.ccyblog.novel.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
import java.io.File;

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
        String imageTmpDir = "/root/tomcat/tomcat_tmp";
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("win")){
            imageTmpDir = "D:\\web\\workspace\\tmp";
        }
        File file = new File(imageTmpDir);
        if(!file.exists()){
            file.mkdirs();
        }
        registration.setMultipartConfig(new MultipartConfigElement(imageTmpDir, 4194304, 4194304, 0));
    }

}
