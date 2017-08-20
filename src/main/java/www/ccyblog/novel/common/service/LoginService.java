package www.ccyblog.novel.common.service;

import www.ccyblog.novel.modules.account.entity.Account;

/**
 * Created by isghost on 2017/8/19.
 * rmi 接口
 */
public interface LoginService {
    Account login(String username, String password);
}
