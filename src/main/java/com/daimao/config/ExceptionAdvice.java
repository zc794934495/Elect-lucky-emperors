package com.daimao.config;

import com.daimao.base.JSONResponse;
import com.daimao.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;



@ControllerAdvice
@Slf4j
public class ExceptionAdvice {

    //自定义异常报错错误码和错误消息
    @ExceptionHandler(AppException.class)
    @ResponseBody
    public Object handle1(AppException e){
        JSONResponse json = new JSONResponse();
        json.setCode(e.getCode());
        json.setMessage(e.getMessage());
        log.debug("自定义异常", e);
        return json;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handle2(Exception e){
        JSONResponse json = new JSONResponse();
        json.setCode("ERR000");
        json.setMessage("未知错误");
        log.error("未知错误", e);
        return json;
    }
}
