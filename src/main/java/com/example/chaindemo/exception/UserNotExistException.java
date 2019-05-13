package com.example.chaindemo.exception;

/**
 * 用户不存在的异常
 * @author yuandongfei
 * @date 2019/4/11
 */
public class UserNotExistException extends AuthenticationException {
    public UserNotExistException(String error) {
        super(error);
    }
}
