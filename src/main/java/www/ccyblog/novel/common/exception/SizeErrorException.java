package www.ccyblog.novel.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by ccy on 2017/7/20.
 * 未使用，暂坑位
 */
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE,
                reason = "Picture Size Error")
public class SizeErrorException extends RuntimeException {

}
