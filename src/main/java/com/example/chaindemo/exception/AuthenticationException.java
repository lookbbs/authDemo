package com.example.chaindemo.exception;

/**
 * 登录认证异常
 * @author yuandongfei
 * @date 2019/4/10
 */
public class AuthenticationException extends RuntimeException {

    public AuthenticationException() {
        super();
    }

    public AuthenticationException(String message) {
        super(message);
    }
}
