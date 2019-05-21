package com.example.chaindemo.pojo;

import lombok.Data;

/**
 * @author yuandongfei
 * @date 2019/4/10
 */
@Data
public class LoginRequest extends AbstractRequest {
    /**
     * 设备类型
     */
    private String deviceType;
    /**
     * 设备号
     */
    private String deviceNo;
    /**
     * 登陆类型
     */
    private Integer loginType;

    private String loginIp;
    /**
     * 游戏版本号
     */
    private String gameVersion;

    /**
     * 设备详情
     */
    private String deviceInfo;

    /**
     * app名称
     */
    private String userName;

    /**
     * 注册渠道
     */
    private String registerSource;
    /**
     * 密码
     */
    private String password;

    /**
     * 短信验证码 / 随机验证码（用户名密码登录）
     */
    private String code;

    /**
     * 手机号自动登录校验密文
     */
    private String autoCode;

    /**
     * 短信业务类型
     */
    private String msgcodeBizKey;

    /**
     * 是否是模拟器
     */
    private String mulatorFlag;
}
