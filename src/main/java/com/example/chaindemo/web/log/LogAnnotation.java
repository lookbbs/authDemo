package com.example.chaindemo.web.log;

import java.lang.annotation.*;

/**
 * @author yuandongfei
 * @date 2019/5/21
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {

    String message();

    OperationTypeEnum opt();
}
