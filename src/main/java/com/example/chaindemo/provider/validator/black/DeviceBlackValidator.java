package com.example.chaindemo.provider.validator.black;

import com.example.chaindemo.provider.validator.Validator;
import com.example.chaindemo.provider.validator.exception.ValidatorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 设备黑名单验证器
 *
 * @author yuandongfei
 * @date 2019/4/11
 */
@Slf4j
@Order
@Component
public class DeviceBlackValidator implements Validator<String> {

    @Override
    public boolean isValid(String deviceNo) {
        log.info(">>> 设备黑名单验证器开始验证设备号：{}", deviceNo);
        if (!"123456".equalsIgnoreCase(deviceNo)) {
            throw new ValidatorException("设备黑名单校验失败");
        }
        return true;
    }
}
