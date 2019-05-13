package com.example.chaindemo.service;

import com.example.chaindemo.pojo.UserDetail;

/**
 * @author yuandongfei
 * @date 2019/5/13
 */
public interface UserService {

    /**
     * 根据用户名获取用户对象
     *
     * @param username
     * @return
     */
    UserDetail getByUsername(String username);
}
