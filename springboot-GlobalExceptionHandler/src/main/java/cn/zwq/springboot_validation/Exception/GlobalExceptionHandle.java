package cn.zwq.springboot_validation.Exception;

import cn.zwq.springboot_validation.model.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandle {

    /**
     *  捕获404异常
     * @return
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ExceptionResponse notFoundException(NoHandlerFoundException e){
        log.error("资源未找到",e);
        return new ExceptionResponse<>("你好，你要的资源找不到！");
    }

    /**
     * 400——Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
     public ExceptionResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException  e) {
        log.error("参数解析失败", e);
        return new ExceptionResponse<>("bad request");
    }

    /**
     *  405——Method Not Allowed
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ExceptionResponse<String> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException  e){
        log.error("不支持当前请求方法",e);
        return new ExceptionResponse<>("request_method_not_supported");
    }

    /**
     * 415——Unsupported Media Type
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ExceptionResponse handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e){
        log.error("不支持当前媒体",e);
        return new ExceptionResponse("content_type_not_supported");
    }

    /**
     * 500：服务器内部异常
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ExceptionResponse internalServerError(Exception e){
        log.error("服务器内部异常",e);
        return new ExceptionResponse("你好，请稍等会...");
    }
}