package com.example.chaindemo.pojo;

import lombok.Data;

/**
 * @author yuandongfei
 * @date 2019/4/10
 */
@Data
public class AbstractRequest {
    private String version;

    private String sessionId;

    /**
     * appId 必填
     */
    private String appId;

    private String appName;

    /**
     * 时间戳，必填
     */
    private String timeStamp;
}
