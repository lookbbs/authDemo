package com.example.chaindemo.provider.validator.white;

import com.example.chaindemo.provider.validator.Validator;
import com.example.chaindemo.provider.validator.data.UsernameValidData;
import com.example.chaindemo.provider.validator.data.ValidData;
import com.example.chaindemo.provider.validator.exception.ValidatorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 用户白名单验证器
 *
 * @author yuandongfei
 * @date 2019/4/11
 */
@Slf4j
@Order
@Component
public class UsernameWhiteValidator implements WhiteValidator<String>, Validator<String> {

    @Override
    public boolean isValid(String username) {
        log.info(">>> 用户白名单验证器开始验证用户名：{}", username);
        return "admin".equalsIgnoreCase(username);
    }

    @Override
    public boolean isPermitted(String param) {
        return isPermitted(param, Boolean.TRUE);
    }

    @Override
    public boolean isPermitted(String param, Boolean throwException) {
        boolean valid = isValid(param);
        if (valid) {
            log.info(">>> 用户名：{}白名单校验通过", param);
            return Boolean.TRUE;
        }
        if (throwException) {
            throw new ValidatorException("用户名白名单校验失败");
        }
        return false;
    }

    @Override
    public boolean supports(Class<? extends ValidData> clazz) {
        return UsernameValidData.class.isAssignableFrom(clazz);
    }
}
