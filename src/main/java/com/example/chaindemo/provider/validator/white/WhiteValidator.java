package com.example.chaindemo.provider.validator.white;

import com.example.chaindemo.provider.validator.data.ValidData;

/**
 * @author yuandongfei
 * @date 2019/4/11
 */
public interface WhiteValidator<T> {

    /**
     * 白名单认证
     *
     * @param param 认证参数
     * @return true：通过，不通过
     */
    boolean isPermitted(T param);

    /**
     * 白名单认证
     *
     * @param param          认证参数
     * @param throwException 认证不通过是否抛出异常。true：不通过抛出对应的异常，false：不通过返回false
     * @return
     */
    boolean isPermitted(T param, Boolean throwException);

    /**
     * 匹配合适的验证器
     *
     * @param clazz
     * @return
     */
    boolean supports(Class<? extends ValidData> clazz);
}
