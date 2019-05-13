package com.example.chaindemo.provider.validator.white;

import com.example.chaindemo.provider.validator.Validator;
import com.example.chaindemo.provider.validator.data.DeviceValidData;
import com.example.chaindemo.provider.validator.data.ValidData;
import com.example.chaindemo.provider.validator.exception.ValidatorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 设备白名单验证器
 *
 * @author yuandongfei
 * @date 2019/4/11
 */
@Slf4j
@Order
@Component
public class DeviceWhiteValidator implements WhiteValidator<String>, Validator<String> {

    @Override
    public boolean isValid(String deviceNo) {
        log.info(">>> 设备白名单验证器开始验证设备号：{}", deviceNo);
        return "123456".equalsIgnoreCase(deviceNo);
    }

    @Override
    public boolean isPermitted(String deviceNo) {
        return isPermitted(deviceNo, Boolean.TRUE);
    }

    @Override
    public boolean isPermitted(String deviceNo, Boolean throwException) {
        boolean valid = isValid(deviceNo);
        if (valid) {
            log.info(">>> 设备：{}白名单校验通过", deviceNo);
            return Boolean.TRUE;
        }
        if (throwException) {
            throw new ValidatorException("设备白名单校验失败");
        }
        return false;
    }

    @Override
    public boolean supports(Class<? extends ValidData> clazz) {
        return DeviceValidData.class.isAssignableFrom(clazz);
    }
}
