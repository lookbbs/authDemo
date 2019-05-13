package com.example.chaindemo.pojo;

import lombok.Data;

/**
 * @author yuandongfei
 * @date 2019/4/10
 */
@Data
public class ServletHeader {
    /**
     * 系统版本
     */
    private String version;

    /**
     * 签名
     */
    private String sign;

    /**
     * ip号
     */
    private String ip;

    /**
     * 加密埋点数据
     */
    private String buriedStr;
}
