package com.example.order.exception;

import com.example.order.common.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @ClassName ExceptionCatch
 * @Description 统一异常处理类
 * @Author xionggy
 * @Date 2020/8/21
 * @Version 1.0
 */
@ControllerAdvice
public class ExceptionCatch {

    protected static final Logger logger = LoggerFactory.getLogger(ExceptionCatch.class);

    @ExceptionHandler(CustomException.class)
    public ServerResponse customException(CustomException customException){
        //TODO:此处做该异常的统一处理
        String message = customException.getMessage();
        return ServerResponse.createByErrorMessage(message);
    }

    @ExceptionHandler(LoginException.class)
    public void customException(LoginException loginException){
        //TODO:此处做该异常的统一处理
        String message = loginException.getMessage();
        logger.error("用户登录失败:{}",message);
    }

}
