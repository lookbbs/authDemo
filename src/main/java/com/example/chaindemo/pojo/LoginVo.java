package com.example.chaindemo.pojo;

import lombok.Data;

/**
 * @author yuandongfei
 * @date 2019/4/10
 */
@Data
public class LoginVo {
    /**
     * 返回给CP的用户主键id
     */
    private String uuid;
    /**
     * 用户的sessionId
     */
    private String sessionId;
    /**
     * 账号
     */
    private String userName;

    private String gmUserId;
    /**
     * 账号类型
     */
    private String accountType;

    /**
     * 自动登录验证码
     */
    private String autoCode;

    /**
     * 登录错误次数
     */
    private int count;
}
