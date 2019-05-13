package com.example.chaindemo.provider.sms;

import com.example.chaindemo.pojo.LoginRequest;
import com.example.chaindemo.pojo.LoginVo;
import com.example.chaindemo.provider.validator.white.DeviceWhiteValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 手机号短信注册服务
 * @author yuandongfei
 * @date 2019/4/11
 */
@Slf4j
@Component
public class SmsRegisterProvider {

    @Autowired
    private DeviceWhiteValidator deviceWhiteValidator;

    public LoginVo register(LoginRequest request) {
        log.info(">>> 根据手机号：{}自动注册账号", request.getUserName());
        // 模拟器注册拦截(模拟器环境不允许注册)
        // 设备白名单判断
        boolean permitted = deviceWhiteValidator.isPermitted(request.getDeviceNo(), Boolean.FALSE);
        if(!permitted){
            //判断同一个游戏同一个设备下注册账号次数[防刷-注册]
            //ios不需要做该限制
            //判断该设备下24小时内注册的账号次数[防刷-注册]
        }
        // 验证密码是否输入,未输入不能注册

        // 拼装注册对象

        // 增加注册限制次数
        // 增加用户安全级别
        // 给用户在增加经验

        // 返回用户的主键UUID
        // 返回用户名
        // 返回账户类型（和登录类型一致）
        return null;
    }

}
