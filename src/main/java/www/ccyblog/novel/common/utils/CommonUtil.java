package www.ccyblog.novel.common.utils;

import org.apache.commons.codec.binary.Hex;
import org.apache.shiro.crypto.hash.Md5Hash;
import sun.security.provider.MD5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * Created by ccy on 2017/7/31.
 * 能用工具类
 */
public class CommonUtil {
    /**
     * 随机生成一个字符串，长度为20
     * @return 随机字符串
     */
    private static Random rand = new Random();
    public static String generateRandomSalt(){
        final int length = 20;
        char [] str = new char[length];
        for(int i = 0;i < length;i++){
            // 只生成能显示的字符
            str[i] = (char)(33 + rand.nextInt(94));
        }
        return new String(str);
    }

    /**
     * 根据文件内容生成对应的名字
     */
    public static String getFileNameByBytes(byte[] bytes){
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.reset();
            md5.update(bytes);
            return new String(Hex.encodeHex(md5.digest()));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

}
