package com.example.chaindemo.provider;

import lombok.Data;

/**
 * 认证信息
 * @author yuandongfei
 * @date 2019/4/7
 */
@Data
public class AuthenticationToken {
    private String username;
    private String ip;
    private String deviceNo;
}
