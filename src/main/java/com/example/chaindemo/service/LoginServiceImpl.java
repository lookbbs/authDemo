package com.example.chaindemo.service;

import com.example.chaindemo.provider.ProviderManager;
import com.example.chaindemo.pojo.LoginRequest;
import com.example.chaindemo.pojo.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yuandongfei
 * @date 2019/4/10
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private ProviderManager providerManager;

    @Override
    public UserDetails login(LoginRequest request) {
        // 防重复提交验证（）
        // 数据完整性验证（MD5签名）
        providerManager.authenticate(request);
        return null;
    }
}
