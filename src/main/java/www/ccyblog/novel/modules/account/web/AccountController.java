package www.ccyblog.novel.modules.account.web;

import com.octo.captcha.service.CaptchaServiceException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import lombok.extern.log4j.Log4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import www.ccyblog.novel.common.security.UserAuthenticationToken;
import www.ccyblog.novel.modules.account.service.AccountService;
import www.ccyblog.novel.modules.account.service.AccountService.REGISTER_ERROR_INFO;
import www.ccyblog.novel.modules.account.service.JCaptchaService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


/**
 * Created by ccy on 2017/7/31.
 * 帐号Controller
 */
//TODO 完成授权
@Log4j
@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private JCaptchaService jCaptchaService;

    /**
     * 显示注册表单
     * @return 视图名称
     */
    @RequestMapping(value = "/register", method = GET)
    public String registerForm(){
        return "register";
    }

    /**
     * 提交注册
     * @param username 用户名
     * @param password 密码
     * @param rePassword 重复用户名
     * @param captcha 验证码
     * @param model model
     * @return 视图名称
     */
    @RequestMapping(value = "/register", method = POST)
    public String registerAccount(@RequestParam(value = "username",defaultValue = "") String username ,
                                  @RequestParam(value = "password",defaultValue = "") String password ,
                                  @RequestParam(value = "rePassword",defaultValue = "") String rePassword ,
                                  @RequestParam(value = "captcha",defaultValue = "") String captcha ,
                                  Model model){

        REGISTER_ERROR_INFO status =  accountService.createAccount(username, password, rePassword, captcha);
        switch (status){
            case NORMAL: return "redirect:/account/login";
            case CAPTCHA: return "redirect:register?error=captcha";
            case USERNAME: return "redirect:register?error=username";
            case PASSWORD: return "redirect:register?error=password";
            case OTHER: return "redirect:register?error=other";
            default: return "redirect:register?error=other";
        }
    }

    /**
     * 获取验证码图
     * @param httpServletRequest
     * @param httpServletResponse
     * @throws IOException
     */
    @RequestMapping(value="/captcha.jpeg")
    public void getJCaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        byte[] captchaChallengeAsJpeg = null;
        // the output stream to render the captcha image as jpeg into
        // 将图片写入输出流
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            // 获得session Id用于验证码生成.
            //必须使用相当的id 来验证验证码， session Id是一个好的选择!
            String captchaId = httpServletRequest.getSession().getId();
            // 获得验证码图片
            BufferedImage challenge =
                    jCaptchaService.getImageChallengeForID(captchaId,
                            httpServletRequest.getLocale());

            // jpeg编码器

            JPEGImageEncoder jpegEncoder =
                    JPEGCodec.createJPEGEncoder(jpegOutputStream);
            jpegEncoder.encode(challenge);

        } catch (IllegalArgumentException e) {
            try {
                httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return;
        } catch (CaptchaServiceException e) {
            try {
                httpServletResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }

        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();

        // 写入响应
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = null;
        try {
            responseOutputStream = httpServletResponse.getOutputStream();
            responseOutputStream.write(captchaChallengeAsJpeg);
            responseOutputStream.flush();
            responseOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示登录表单
     * @param model
     * @return 视图名称
     */
    @RequestMapping(value = "/login", method = GET)
    public String showLoginForm(Model model){
        return "login";
    }

    /**
     * 提交登录
     * @param username 用户名
     * @param password 密码
     * @param model
     * @return 视图名称
     */
    @RequestMapping(value="/login", method = POST)
    public String login(@RequestParam String username, @RequestParam String password, RedirectAttributes model){
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UserAuthenticationToken(username, password, accountService);
        try{
            currentUser.login(usernamePasswordToken);
            usernamePasswordToken.setRememberMe(true);
        }catch (AuthenticationException e){
            model.addFlashAttribute("error", true);
            return "redirect:/account/login";
        }
        return "redirect:/";
    }

    /**
     * 查询用户名是否存在
     * @param username 用户名
     * @return 是否存在
     */
    //《sping in action》没有提示依赖哪些库，尝试一下午，终于确定是jackson-core 和 jackson-databind
    @RequestMapping(value="/query.json", method = POST)
    public @ResponseBody Map queryUsername(@RequestParam(value = "username") String username){
        boolean isRepeat = accountService.hasUsername(username);
        HashMap hashMap = new HashMap<String, Boolean>();
        hashMap.put("repeat", isRepeat);
        return hashMap;
    }

    /**
     * 显示条款
     * @return
     */
    @RequestMapping(value="/terms")
    public String getTerms(){
        return "terms";
    }

    /**
     * 退出登录
     * @return 首页视图名称
     */
    @RequestMapping(value="/logout")
    public String logout(){
        Subject currentUser = SecurityUtils.getSubject();
        if(currentUser.isAuthenticated()){
            currentUser.logout();
        }
        return "redirect:/index";
    }
}
