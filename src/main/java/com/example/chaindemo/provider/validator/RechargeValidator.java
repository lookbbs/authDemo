package com.example.chaindemo.provider.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 重置额度验证器
 * @author yuandongfei
 * @date 2019/4/11
 */
@Slf4j
@Order
@Component
public class RechargeValidator implements Validator<String> {

    @Override
    public boolean isValid(String username) {
        log.info(">>> 充值额度验证器开始验证用户名：{}", username);

        // 返回额度是不是符合标准值
        return false;
    }
}
