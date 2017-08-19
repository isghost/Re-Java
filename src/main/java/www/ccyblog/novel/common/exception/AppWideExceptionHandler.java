package www.ccyblog.novel.common.exception;

import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;

/**
 * Created by ccy on 2017/7/20.
 */

@ControllerAdvice
@Log4j
public class AppWideExceptionHandler {
    @ExceptionHandler(MultipartException.class)
    public String sizeError(RuntimeException runtimeException){
        log.warn(runtimeException.getMessage());
        return "errors/errorPictureSize";
    }
}
