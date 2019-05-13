package com.example.chaindemo.web.controller;

import com.alibaba.fastjson.JSON;
import com.example.chaindemo.pojo.LoginRequest;
import com.example.chaindemo.pojo.ServletHeader;
import com.example.chaindemo.service.LoginService;
import com.example.chaindemo.web.Constant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author yuandongfei
 * @date 2019/4/10
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;


    @PostMapping("/login")
    public void login(@RequestBody LoginRequest request, HttpServletRequest $request) {
        ServletHeader headerInfo = getHeaderInfo($request);
        loginService.login(request, headerInfo);
    }

    @GetMapping("/login/token")
    public ResponseEntity<String> loginToken(HttpServletRequest request) {
        // 这里可以传递自定义的参数用于生成token
        String token = UUID.randomUUID().toString();
        request.getSession().setAttribute(Constant.TOKEN_SESSION_KEY, token);
        return ResponseEntity.ok(token);
    }

    public ServletHeader getHeaderInfo(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String, String> result = new HashMap<>(16);

        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String header = request.getHeader(headerName);
            result.put(headerName, header);
        }
        ServletHeader headerBo = JSON.parseObject(JSON.toJSONString(result), ServletHeader.class);
        headerBo.setIp(getIp2(request));

        return headerBo;
    }

    public static String getIp2(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        System.out.println("getIp2中获取到ip值为:" + ip);
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
