package com.example.chaindemo.provider.validator.data;

import lombok.Getter;
import lombok.Setter;

/**
 * 待校验的数据
 * @author yuandongfei
 * @date 2019/4/11
 */
@Getter
@Setter
public class ValidData<T> {
    private T data;
}
