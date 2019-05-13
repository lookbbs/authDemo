package com.example.chaindemo.provider.validator.exception;

import com.example.chaindemo.exception.AuthenticationException;

/**
 * @author yuandongfei
 * @date 2019/4/11
 */
public class ValidatorException extends AuthenticationException {
    public ValidatorException(String error) {
        super(error);
    }
}
