package www.ccyblog.novel.modules.account.service;

import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.GradientBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomRangeColorGenerator;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.TwistedAndShearedRandomFontGenerator;
import com.octo.captcha.component.image.textpaster.RandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.image.DefaultImageCaptchaEngine;
import com.octo.captcha.image.ImageCaptchaFactory;
import com.octo.captcha.image.gimpy.GimpyFactory;


/**
 * Created by ccy on 2017/8/4.
 * 官方默认验证码实在是太难，一般人认不出来，所以编写此简单验证码
 */
public class SimpleImageCaptchaEngine extends DefaultImageCaptchaEngine {
    static ImageCaptchaFactory[] factories;


    public SimpleImageCaptchaEngine() {
        super(factories);
    }

    static {
        WordGenerator randomWords = new RandomWordGenerator("012345679"); // 验证码生成的字符
        // 验证码颜色
        TextPaster randomPaster = new RandomTextPaster(4, 4, new RandomRangeColorGenerator(new int[]{126,255},new int[]{126,255}, new int[]{126,255}));
        // 验证码背景色
        BackgroundGenerator funkyBack = new GradientBackgroundGenerator(
                160,
                80,
                new RandomRangeColorGenerator(new int[]{0,125},new int[]{0,125}, new int[]{0,123}),
                new RandomRangeColorGenerator(new int[]{0,125},new int[]{0,125}, new int[]{0,123})
        );
        // 字体扭曲相关函数
        FontGenerator shearedFont = new TwistedAndShearedRandomFontGenerator(28, 34);
        WordToImage word2image = new ComposedWordToImage(shearedFont, funkyBack, randomPaster);
        factories = new ImageCaptchaFactory[1];
        // 记录生成的工厂
        factories[0] = new GimpyFactory(randomWords, word2image);
    }
}
