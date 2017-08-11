package www.ccyblog.novel.modules.images.service;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.request.UploadFileRequest;
import com.qcloud.cos.sign.Credentials;
import lombok.extern.log4j.Log4j;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import www.ccyblog.novel.common.utils.CommonUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by isghost on 2017/8/11.
 * 腾讯云对象存储
 */
@Log4j
@Service
public class CloudStorage {
    long appId = 1252097459;
    String secretId = "AKIDLalkcIxYSCnGblrhZwBHBTqP4sX0ZST5";
    String secretKey = "gD95yfMyQgniU8MtRp7Ecko4Rl0g6Tq1";
    // 设置要操作的bucket
    String bucketName = "novel";
    COSClient cosClient = null;
    Pattern pattern = Pattern.compile("\\.[^\\.]*$");
    private final String salt = CommonUtil.generateRandomSalt();
    public CloudStorage(){
        // 初始化秘钥信息
        Credentials cred = new Credentials(appId, secretId, secretKey);
        // 初始化客户端配置
        ClientConfig clientConfig = new ClientConfig();
        // 设置bucket所在的区域，比如华南园区：gz； 华北园区：tj；华东园区：sh ；
        clientConfig.setRegion("sh");

        // 初始化cosClient
        cosClient = new COSClient(clientConfig, cred);

    }

    public String saveImage(MultipartFile multipartFile){
        byte [] contentBuffer;
        try {
            contentBuffer = multipartFile.getBytes();
        } catch (IOException e) {
            log.error("multipartFile getBytes error");
            e.printStackTrace();
            return "{'code': -1, 'message':'internal error'}";
        }
        String newFileName = new Md5Hash(contentBuffer).toString();
        String fileName = multipartFile.getOriginalFilename();
        Matcher matcher = pattern.matcher(fileName);
        String suffix = "";
        if(matcher.find()){
            newFileName += matcher.group();
        }
        return uploadFileToCloud(newFileName, contentBuffer);

    }

    public String uploadFileToCloud(String fileName, byte[] contentBuffer){
        UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName, "/" + fileName, contentBuffer);
        return  cosClient.uploadFile(uploadFileRequest);
    }

}
