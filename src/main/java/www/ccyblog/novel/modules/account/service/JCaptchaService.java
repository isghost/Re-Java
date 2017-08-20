package www.ccyblog.novel.modules.account.service;

import com.octo.captcha.service.captchastore.MapCaptchaStore;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;
import org.springframework.stereotype.Service;

/**
 * Created by ccy on 2017/8/3.
 * 验证码服务
 */
@Service
public class JCaptchaService extends DefaultManageableImageCaptchaService {
    private JCaptchaService() {
        super(
                new MapCaptchaStore(), new SimpleImageCaptchaEngine(), 180,
                100000 , 75000);
    }

}
