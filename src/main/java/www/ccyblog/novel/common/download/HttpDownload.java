package www.ccyblog.novel.common.download;

import lombok.extern.log4j.Log4j;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import www.ccyblog.novel.common.utils.CommonUtil;
import www.ccyblog.novel.modules.images.entity.ImageDownloadInfo;
import www.ccyblog.novel.modules.images.service.CloudStorageService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.*;

/**
 * Created by isghost on 2017/8/29.
 */
@Log4j
@Service
public class HttpDownload implements InitializingBean, DisposableBean{
    ThreadPoolExecutor threadPoolExecutor;
    BlockingQueue<Runnable> runnables;
    @Autowired
    CloudStorageService cloudStorageService;
    @Override
    public void afterPropertiesSet() throws Exception {
        runnables = new ArrayBlockingQueue<Runnable>(10);
        threadPoolExecutor = new ThreadPoolExecutor(
                3,
                6,
                20,
                TimeUnit.SECONDS,
                runnables,
                (r, executor) -> {
                    ImageWorker imageWorker = (ImageWorker) r;
                    ImageDownloadInfo imageDownloadInfo = imageWorker.getImageDownloadInfo();
                }
        );
//        threadPoolExecutor.execute(new ImageWorker("http://res.ccyblog.com/b9fe42134d5714f6053c94123dddffde.gif"));
    }

    public void crawlerImage(String imageUrl, int uid){
        ImageDownloadInfo imageDownloadInfo = new ImageDownloadInfo(imageUrl, uid, 1);
        threadPoolExecutor.execute(new ImageWorker(imageDownloadInfo));
    }

    @Override
    public void destroy() throws Exception {
        threadPoolExecutor.shutdown();
    }
}

class ImageWorker implements Runnable{
    private ImageDownloadInfo imageDownloadInfo;
    public ImageWorker(ImageDownloadInfo imageDownloadInfo){
        this.imageDownloadInfo = imageDownloadInfo;
    }

    public ImageDownloadInfo getImageDownloadInfo() {
        return imageDownloadInfo;
    }

    @Autowired
    CloudStorageService cloudStorageService;

    @Override
    public void run() {
        Request request = new Request.Builder()
                .url(this.imageDownloadInfo.getImageUrl())
                .build();
        OkHttpClient client = new OkHttpClient();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()){
                int code = response.code();
                return ;
            }

            Headers responseHeaders = response.headers();
            for (int i = 0; i < responseHeaders.size(); i++) {
                System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
            }
            byte[] bytes = response.body().bytes();
            String fileName = CommonUtil.getFileNameByBytes(bytes);
            FileOutputStream fileOutputStream = new FileOutputStream("123.gif");
            fileOutputStream.write(bytes);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
