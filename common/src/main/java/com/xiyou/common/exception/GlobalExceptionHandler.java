package com.xiyou.common.exception;

import com.mysql.cj.exceptions.CJCommunicationsException;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import com.xiyou.common.enums.CodeEnum;
import com.xiyou.common.exception.CustomException;
import com.xiyou.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.net.ConnectException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @program: attendance
 * @description: 自定义异常捕获
 * @author: tangcan
 * @create: 2018-11-26 19:10
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Value("${spring.servlet.multipart.max-file-size}")
    private String fileSizeLimit;

    @ExceptionHandler({RuntimeException.class, NullPointerException.class, Exception.class})
    @ResponseBody
    public R handleCodeError(Exception e) {
        log.error(e.getMessage(), e);
        return R.error(CodeEnum.SYSTEM_ERROR);
    }

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public R handleCustomException(CustomException e) {
        return R.error(e.getCode(), e.getMsg());
    }

    /**
     * 授权失败
     */
    @ExceptionHandler(AuthorizationException.class)
    @ResponseBody
    public R handleAuthorizationException(AuthorizationException e) {
        return R.error(CodeEnum.AUTHORIZATION_FAIL);
    }

    /**
     * 身份验证失败
     */
    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    public R handleAuthenticationException(Exception e) {
        return R.error(CodeEnum.AUTHENTICATION_FAIL);
    }

    /**
     * 未经身份验证
     */
    @ExceptionHandler(UnauthenticatedException.class)
    @ResponseBody
    public R handleUnauthenticatedException(UnauthenticatedException e) {
        return R.error(CodeEnum.NO_LOGIN);
    }

    /**
     * 参数不全
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public R handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        return R.error(CodeEnum.PARAM_MISS.getCode(), "[" + CodeEnum.PARAM_MISS.getMsg() + "] " + e.getMessage());
    }

    /**
     * 请求方式错误
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public R handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return R.error(CodeEnum.REQUEST_EROOR.getCode(), "[" + CodeEnum.REQUEST_EROOR.getMsg() + "] " + e.getMessage());
    }

    /**
     * 传参方式错误
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public R handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return R.error(CodeEnum.PARAM_WAY_ERROR.getCode(), "[" + CodeEnum.PARAM_WAY_ERROR.getMsg() + "] " + e.getMessage());
    }


    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    public R handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        return R.error(CodeEnum.OTHER_ERROR.getCode(), e.getMessage());
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    @ResponseBody
    public R handleMissingServletRequestPartException(MissingServletRequestPartException e) {
        return R.error(CodeEnum.OTHER_ERROR.getCode(), e.getMessage());
    }


    /**
     * 参数校验失败
     */
    @ExceptionHandler({BindException.class, ConstraintViolationException.class, MethodArgumentNotValidException.class})
    public R handleMethodArgumentNotValidException(Exception e) {

        // 错误信息
        StringBuilder errorMsg = new StringBuilder();
        if (e instanceof ConstraintViolationException) {
            for (ConstraintViolation cv : ((ConstraintViolationException) e).getConstraintViolations()) {
                errorMsg.append(cv.getMessage()).append("；");
            }
        } else {
            List<ObjectError> errors;
            if (e instanceof BindException) {
                errors = ((BindException) e).getAllErrors();
            } else {
                errors = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors();
            }
            // 拼接错误信息（最多显示十条错误信息）
            int num = 10;
            for (ObjectError oe : errors) {
                if (num <= 0) {
                    break;
                }
                num--;
                errorMsg.append(oe.getDefaultMessage()).append("；");
            }
        }
        return R.error(CodeEnum.PARAM_ERROR.getCode(), errorMsg.toString());
    }


    /**
     * 参数类型错误
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public R handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return R.error(CodeEnum.PARAM_TYPE_ERROR.getCode(), "[" + CodeEnum.PARAM_TYPE_ERROR.getMsg() + "] " + e.getMessage());
    }

    @ExceptionHandler(MultipartException.class)
    @ResponseBody
    public R handleMultipartException() {
        return R.error(CodeEnum.FILE_UPLOAD_FAIL);
    }

    @ExceptionHandler(JSONException.class)
    @ResponseBody
    public R handleJSONException(Exception e) {
        return R.error(CodeEnum.PARAM_WAY_ERROR.getCode(), "[JSON格式错误]" + e.getMessage());
    }
}
