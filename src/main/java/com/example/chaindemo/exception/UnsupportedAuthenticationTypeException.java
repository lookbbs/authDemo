package com.example.chaindemo.exception;

/**
 * 未支持的认证类型异常
 * @author yuandongfei
 * @date 2019/4/10
 */
public class UnsupportedAuthenticationTypeException extends AuthenticationException {

    public UnsupportedAuthenticationTypeException(String error) {
        super(error);
    }
}
