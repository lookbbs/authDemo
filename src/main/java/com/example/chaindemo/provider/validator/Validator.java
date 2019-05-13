package com.example.chaindemo.provider.validator;

/**
 * 验证器接口
 *
 * @author yuandongfei
 * @date 2019/4/11
 */
public interface Validator<T> {

    /**
     * 验证是否有效
     *
     * @param data 待校验的参数
     * @return
     */
    boolean isValid(T data);
}
