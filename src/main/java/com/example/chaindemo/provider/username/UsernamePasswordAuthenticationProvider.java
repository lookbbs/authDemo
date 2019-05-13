package com.example.chaindemo.provider.username;

import com.example.chaindemo.pojo.LoginRequest;
import com.example.chaindemo.pojo.LoginVo;
import com.example.chaindemo.exception.UserNotExistException;
import com.example.chaindemo.provider.AuthenticationProvider;
import com.example.chaindemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户名密码登录服务处理
 *
 * @author yuandongfei
 * @date 2019/4/7
 */
@Slf4j
@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public LoginVo authenticate(LoginRequest request) {
        log.info(">>> 用户名密码登录开始。。。。。");
        // 用户名密码登录错误次数检查确认
        checkImageCode(request);
        log.info(">>> 用户名密码登录错误次数检查确认 通过");
        LoginVo user = userService.getByUsername(request.getUserName());
        if (user == null) {
            throw new UserNotExistException("用户不存在");
        }
        // 如果用户名获取用户对象存在
        // 判断用户密码是否匹配
        // 如果密码不匹配，走密码错误处理流程
        passwordCheckFailureProcessor(request);
        // 判断用户密码匹配，返回用户对象

        // 返回用户的主键UUID
        // 返回用户名
        // 返回账户类型（和登录类型一致）
        return user;
    }

    /**
     * 校验图片验证码
     * @param request
     */
    private void checkImageCode(LoginRequest request) {
        // 获取系统配置的验证码校验的最低版本号（验版号）
        //      符合验版号条件：获取登录错误次数
        //          判断当前错误次数是否达到显示图形验证码的次数
        //              小于等于显示图片验证码次数时：校验通过
        //              大于显示图片验证码次数时：
        //                  获取当前验证码登录错误次数（图错数）
        //                  判断图错数是否大于系统配置的最大允许次数
        //                      如何小于等于最大允许次数，校验通过
        //                      如何大于最大允许次数，直接抛出错误次数达到上限，禁止登录异常，并附带密码登录的错误次数
        //                  校验用户提交验证码非空/格式校验
        //                  校验系统验证码是否过期
        //                  校验用户提交验证码和系统验证码相等匹配
        //          删除系统验证码
    }

    /**
     * 密码校验失败处理
     * @param request
     */
    private void passwordCheckFailureProcessor(LoginRequest request) {
        // 密码错误次数递增+1
        // 判断当前错误次数是否达到显示图形验证码的次数
        //      小于等于显示图片验证码次数时：直接抛出密码校验失败异常，并附带错误次数
        //      大于显示图片验证码次数时：
        //          带图片验证码登录错误次数递增+1（后面取名：图错次数）
        //          抛出密码校验失败异常，并附带密码登录的错误次数
    }

    @Override
    public boolean supports(Integer loginType) {
        return 2 == loginType;
    }
}
