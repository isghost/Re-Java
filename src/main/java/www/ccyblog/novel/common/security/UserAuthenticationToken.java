package www.ccyblog.novel.common.security;

import org.apache.shiro.authc.UsernamePasswordToken;
import www.ccyblog.novel.modules.account.service.AccountService;

/**
 * Created by isghost on 2017/8/6.
 *
 */
public class UserAuthenticationToken extends UsernamePasswordToken {
    private AccountService accountService;
    public UserAuthenticationToken(String username, String password, AccountService accountService){
        super(username, password);
        this.accountService = accountService;
    }

    public AccountService getAccountService(){
        return this.accountService;
    }
}
