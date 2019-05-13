package com.example.chaindemo.pojo;

import lombok.Data;

/**
 * @author yuandongfei
 * @date 2019/4/7
 */
@Data
public class UserDetail {
    private String uuid;
    private String sessionId;
    private String userName;
    private String gmUserId;
    private String accountType;
    private String autoCode;
}
