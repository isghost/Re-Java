package www.ccyblog.novel.modules.account.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by ccy on 2017/7/31.
 * 帐号实体
 */
@Data
public class Account implements Serializable{
    private int uid;
    private String username;
    private String password;
    private String salt;
}
