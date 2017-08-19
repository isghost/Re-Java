package www.ccyblog.novel.common.service;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import www.ccyblog.novel.modules.account.entity.Account;
import www.ccyblog.novel.modules.account.service.AccountService;

/**
 * Created by isghost on 2017/8/19.
 */
@Service
@Qualifier("loginServiceServer")
public class LoginServiceImpl implements LoginService {

    @Autowired
    AccountService accountService;
    public Account login(String username, String password) {
        Account account = accountService.getAccount(username);
        if(account == null){
            return null;
        }
        else if(!new Md5Hash(password + username + account.getSalt()).toString().equals(account.getPassword())){
            return null;
        }
        return account;
    }
}
