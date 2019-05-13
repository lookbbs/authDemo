package com.example.chaindemo.handler;

import com.example.chaindemo.pojo.LoginRequest;
import com.example.chaindemo.exception.AuthenticationException;

/**
 * 认证成功后处理器
 * @author yuandongfei
 * @date 2019/4/11
 */
public interface AuthenticationFailureHandler {
    void onFailure(LoginRequest request, AuthenticationException e);
}
