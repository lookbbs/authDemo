package com.example.chaindemo.provider;

import com.example.chaindemo.pojo.LoginRequest;
import com.example.chaindemo.pojo.LoginVo;

/**
 * @author yuandongfei
 * @date 2019/4/7
 */
public interface AuthenticationProvider {
    /**
     * 登录认证
     *
     * @param request 登录请求
     * @return
     */
    LoginVo authenticate(LoginRequest request);


    /**
     * 是否支持当前provider
     *
     * @param loginType 登录类型 2：账号密码登陆，3：手机号验证码登陆
     * @return
     */
    boolean supports(Integer loginType);
}
