package com.example.chaindemo.provider.validator;

import com.example.chaindemo.pojo.LoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 登录接口请求登录/注册时参数格式校验
 *
 * @author yuandongfei
 * @date 2019/4/12
 */
@Slf4j
@Component
public class LoginRequestValidator implements Validator<LoginRequest> {
    @Override
    public boolean isValid(LoginRequest request) {
        // appId, deviceNo 判断
        // loginType 类型存在判断
        // 用户名格式判断
        // 注册源判断
        // 设备类型空判断
        // 登录类型为用户名密码时，判断密码是否为空
        return true;
    }
}
