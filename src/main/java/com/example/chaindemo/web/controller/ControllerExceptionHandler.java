package com.example.chaindemo.web.controller;

import com.example.chaindemo.exception.AuthenticationException;
import com.example.chaindemo.web.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yuandongfei
 * @date 2019/5/13
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * 统一的认证异常处理接口
     * @param e
     * @return
     */
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Map<String, Object> handleAuthenticationException(HttpServletResponse response, AuthenticationException e) {
        response.setContentType("application/json;charset=UTF-8");
        Map<String, Object> result = new HashMap<>(1);
        result.put("error", e.getMessage());
        return result;
    }
}
