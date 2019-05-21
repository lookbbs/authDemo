package com.example.chaindemo.web.controller;

import com.example.chaindemo.pojo.LoginRequest;
import com.example.chaindemo.service.LoginService;
import com.example.chaindemo.web.Constant;
import com.example.chaindemo.web.log.LogAnnotation;
import com.example.chaindemo.web.log.OperationTypeEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author yuandongfei
 * @date 2019/4/10
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @LogAnnotation(opt = OperationTypeEnum.LOGIN, message = "用户登录")
    @PostMapping("/login")
    public void login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        String ip = getIp2(request);
        loginRequest.setLoginIp(ip);
        loginService.login(loginRequest);
    }

    @LogAnnotation(opt = OperationTypeEnum.VIEW, message = "获取防重请求的token")
    @GetMapping("/login/token")
    public ResponseEntity<String> loginToken(HttpServletRequest request) {
        // 这里可以传递自定义的参数用于生成token
        String token = UUID.randomUUID().toString();
        request.getSession().setAttribute(Constant.TOKEN_SESSION_KEY, token);
        return ResponseEntity.ok(token);
    }

    private String getIp2(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }
}
