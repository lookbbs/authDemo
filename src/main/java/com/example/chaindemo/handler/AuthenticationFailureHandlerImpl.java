package com.example.chaindemo.handler;

import com.example.chaindemo.exception.AuthenticationException;
import com.example.chaindemo.pojo.LoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author yuandongfei
 * @date 2019/4/19
 */
@Slf4j
@Service
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {
    @Override
    public void onFailure(LoginRequest request, AuthenticationException e) {
        log.error(">>> 认证失败：{}", request, e);
        throw e;
    }
}
