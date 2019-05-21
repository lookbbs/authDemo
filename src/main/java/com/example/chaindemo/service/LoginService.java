package com.example.chaindemo.service;

import com.example.chaindemo.pojo.LoginRequest;
import com.example.chaindemo.pojo.UserDetails;

/**
 * @author yuandongfei
 * @date 2019/4/10
 */
public interface LoginService {

    UserDetails login(LoginRequest request);
}
