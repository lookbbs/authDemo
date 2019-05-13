package com.example.chaindemo.service;

import com.example.chaindemo.pojo.LoginRequest;
import com.example.chaindemo.pojo.LoginVo;
import com.example.chaindemo.pojo.ServletHeader;

/**
 * @author yuandongfei
 * @date 2019/4/10
 */
public interface LoginService {

    LoginVo login(LoginRequest request, ServletHeader header);
}
