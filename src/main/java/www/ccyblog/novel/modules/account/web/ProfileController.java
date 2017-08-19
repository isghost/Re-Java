package www.ccyblog.novel.modules.account.web;

import lombok.extern.log4j.Log4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import www.ccyblog.novel.modules.account.entity.Account;
import www.ccyblog.novel.modules.account.service.AccountService;

/**
 * Created by isghost on 2017/8/6.
 */
@Log4j
@Controller
public class ProfileController {
    @Autowired
    private AccountService accountService;

    @RequiresRoles("user")
    @RequestMapping("/profile")
    public String showProfile(Model model){
        Session session = SecurityUtils.getSubject().getSession();
        Account account = (Account)session.getAttribute("account");
        model.addAttribute("username",account.getUsername());
        model.addAttribute("uid",account.getUid());
        model.addAttribute("salt",account.getSalt());
        return "profile";
    }
}
