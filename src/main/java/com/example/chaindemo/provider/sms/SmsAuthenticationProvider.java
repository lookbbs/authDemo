package com.example.chaindemo.provider.sms;

import com.example.chaindemo.pojo.LoginRequest;
import com.example.chaindemo.pojo.LoginVo;
import com.example.chaindemo.provider.AuthenticationProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 手机号登录
 *
 * @author yuandongfei
 * @date 2019/4/7
 */
@Slf4j
@Component
public class SmsAuthenticationProvider implements AuthenticationProvider {


    @Override
    public LoginVo authenticate(LoginRequest request) {
        log.info(">>> 手机号验证码登录。。。。。");
        // 手机号登陆身份验证
        LoginVo vo = login(request);

        return vo;
    }

    private LoginVo login(LoginRequest request) {
        log.info(">>> 手机号【{}】登陆", request.getUserName());
        //判断用户账号是否被禁用

        // 返回用户的主键UUID
        // 返回用户名
        // 返回账户类型（和登录类型一致）
        return null;
    }

    @Override
    public boolean supports(Integer loginType) {
        return 3 == loginType;
    }
}
