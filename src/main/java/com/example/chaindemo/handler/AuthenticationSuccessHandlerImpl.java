package com.example.chaindemo.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author yuandongfei
 * @date 2019/4/11
 */
@Slf4j
@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    @Override
    public void onSuccess() {
        log.info(">>> 用户登录成功。。。。。。。  ");
        log.info(">>> 用户登录成功后回调埋点数据。。。。。。");

    }
}
