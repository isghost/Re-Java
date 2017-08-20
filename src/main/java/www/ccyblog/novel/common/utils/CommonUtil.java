package www.ccyblog.novel.common.utils;

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

}
