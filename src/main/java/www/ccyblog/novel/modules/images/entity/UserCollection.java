package www.ccyblog.novel.modules.images.entity;

import lombok.Data;
import org.apache.ibatis.annotations.Param;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by isghost on 2017/8/12.
 * 用户收集实体
 */
@Data
public class UserCollection {
    private byte [] file;
    private int uid;

    public UserCollection(){

    }

    public UserCollection(int uid, byte[] file){
        this.uid = uid;
        this.file = file;
    }
}
