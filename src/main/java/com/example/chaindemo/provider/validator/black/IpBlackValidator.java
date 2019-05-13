package com.example.chaindemo.provider.validator.black;

import com.example.chaindemo.provider.validator.Validator;
import com.example.chaindemo.provider.validator.exception.ValidatorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * IP 黑名单验证
 *
 * @author yuandongfei
 * @date 2019/4/11
 */
@Slf4j
@Order
@Component
public class IpBlackValidator implements Validator<String> {

    @Override
    public boolean isValid(String ipAddress) {
        log.info(">>> IP 黑名单验证器开始验证IP地址：{}", ipAddress);
        if (!"0:0:0:0:0:0:0:1".equalsIgnoreCase(ipAddress)) {
            throw new ValidatorException("IP黑名单校验失败");
        }
        return true;
    }
}
