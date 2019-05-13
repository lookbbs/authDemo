package com.example.chaindemo.web.controller;

import com.example.chaindemo.exception.AuthenticationException;
import com.example.chaindemo.web.Constant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author yuandongfei
 * @date 2019/5/13
 */
@Controller
public class ErrorController {


    @RequestMapping("/500")
    public void handlerFilterError(HttpServletRequest request) {
        AuthenticationException t = (AuthenticationException) request.getAttribute(Constant.FILTER_EXCEPTION_HANDLER_KEY);
        if (!Objects.isNull(t)) {
            throw t;
        }
    }
}
