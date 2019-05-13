package com.example.chaindemo.provider.validator.white;

import com.example.chaindemo.provider.validator.Validator;
import com.example.chaindemo.provider.validator.data.IpValidData;
import com.example.chaindemo.provider.validator.data.ValidData;
import com.example.chaindemo.provider.validator.exception.ValidatorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * IP 白名单验证
 *
 * @author yuandongfei
 * @date 2019/4/11
 */
@Slf4j
@Order
@Component
public class IpWhiteValidator implements WhiteValidator<String>, Validator<String> {

    @Override
    public boolean isValid(String ipAddress) {
        log.info(">>> IP 白名单验证器开始验证IP地址：{}", ipAddress);
        return "0:0:0:0:0:0:0:1".equalsIgnoreCase(ipAddress);
    }

    @Override
    public boolean isPermitted(String ipAddress) {
        return isPermitted(ipAddress, Boolean.TRUE);
    }

    @Override
    public boolean isPermitted(String ipAddress, Boolean throwException) {
        boolean valid = isValid(ipAddress);
        if (valid) {
            log.info(">>> IP：{}白名单校验通过", ipAddress);
            return Boolean.TRUE;
        }
        if (throwException) {
            throw new ValidatorException("IP白名单校验失败");
        }
        return false;
    }

    @Override
    public boolean supports(Class<? extends ValidData> clazz) {
        return IpValidData.class.isAssignableFrom(clazz);
    }
}
