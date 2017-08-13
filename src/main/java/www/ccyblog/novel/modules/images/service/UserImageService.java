package www.ccyblog.novel.modules.images.service;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import www.ccyblog.novel.modules.account.entity.Account;
import www.ccyblog.novel.modules.images.dao.UserCollectionDao;
import www.ccyblog.novel.modules.images.dao.UserImageDao;
import www.ccyblog.novel.modules.images.entity.UserCollection;
import www.ccyblog.novel.modules.images.entity.UserImage;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by isghost on 2017/8/12.
 */
@Log4j
@Service
public class UserImageService {
    @Autowired
    UserImageDao userImageDao;

    @Autowired
    UserCollectionDao userCollectionDao;

    @Autowired
    CloudStorageService cloudStorageService;
    //
    private final int PAGE_IMAGE_NUM = 20;

    Pattern pattern = Pattern.compile("\\.[^\\.]*$");

    /**
     *
     * @param fileName 图片名
     * @return 图片在数据库的Id
     */
    public long addImageInfo(String fileName){
        // TODO 防止图片重复添加
        UserImage userImage = new UserImage();
        userImage.setFileName(fileName);
        userImageDao.insert(userImage);
        return userImage.getImageid();
    }

    /**
     *
     * @param multipartFile 图片请求的原始对象
     * @return 是否成功
     */
    public boolean saveImage(MultipartFile multipartFile){
        byte [] contentBuffer;
        try {
            contentBuffer = multipartFile.getBytes();
        } catch (IOException e) {
            log.error("multipartFile getBytes error");
            e.printStackTrace();
            return false;
        }
        String newFileName = new Md5Hash(contentBuffer).toString();
        String fileName = multipartFile.getOriginalFilename();
        Matcher matcher = pattern.matcher(fileName);
        if(matcher.find()){
            newFileName += matcher.group();
        }
        String result = cloudStorageService.uploadFileToCloud(newFileName, contentBuffer);
        JSONObject jsonObject = JSONObject.parseObject(result);
        int code = jsonObject.getInteger("code");
        if (code == 0){
            long imageId = addImageInfo(newFileName);
            return addUserCollection(imageId);
        }
        else{
            return false;
        }
    }

    /**
     *
     * @param imageId 将图片的ID添加到个人收藏中
     * @return 返回是否成功
     */
    public boolean addUserCollection(long imageId){
        Account account = (Account)SecurityUtils.getSubject().getSession().getAttribute("account");
        ArrayList<Long> arrayList = getUserCollection();
        if(arrayList.contains(imageId)){
            return true;
        }
        try {
            arrayList.add(imageId);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new ObjectOutputStream(byteArrayOutputStream).writeObject(arrayList);
            userCollectionDao.update(new UserCollection(account.getUid(), byteArrayOutputStream.toByteArray()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 获得用户所有图片收藏信息
     * @return 图片ID集合
     */
    public ArrayList<Long> getUserCollection(){
        ArrayList<Long> arrayList = null;
        Account account = (Account)SecurityUtils.getSubject().getSession().getAttribute("account");
        UserCollection userCollection = userCollectionDao.select(account.getUid());
        try {
            if(userCollection == null){
                arrayList = new ArrayList<Long>();
            }
            else{
                arrayList = (ArrayList<Long>)new ObjectInputStream(new ByteArrayInputStream(userCollection.getFile())).readObject();
            }
            return arrayList;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<Long>();
    }

    /**
     *
     * @param page 显示第page页
     * @return 返回客户端展示所需要信息
     */
    public String getCollectionInfo(int page){
        ArrayList<Long> userCollection = getUserCollection();
        List<Long> list = null;
        if(page < 0 || (page - 1) * PAGE_IMAGE_NUM >= userCollection.size() ){
            list =  new ArrayList<Long>();
        }
        else{
            list = userCollection.subList((page - 1) * PAGE_IMAGE_NUM, Math.min(page* PAGE_IMAGE_NUM, userCollection.size()));
        }
        List<String> collectionImageUrl = null;
        if(list.size() == 0){
            collectionImageUrl = new ArrayList<String>();
        }
        else{
            collectionImageUrl = userImageDao.select(list);
        }

        int pageMax = (userCollection.size() - 1) / PAGE_IMAGE_NUM + 1;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("imageUrl", collectionImageUrl);
        jsonObject.put("baseUrl", "//res.ccyblog.com/");
        jsonObject.put("maxPage", pageMax);
        return jsonObject.toString();
    }
}
