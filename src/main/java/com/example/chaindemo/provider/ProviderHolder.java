package com.example.chaindemo.provider;

import com.example.chaindemo.exception.UnsupportedAuthenticationTypeException;
import com.example.chaindemo.provider.AuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * 认证服务管理类
 *
 * @author yuandongfei
 * @date 2019/4/10
 */
@Component
public class ProviderHolder {

    @Autowired
    private List<AuthenticationProvider> providers;

    /**
     * 获取认证服务类
     *
     * @param loginType
     * @return
     */
    public AuthenticationProvider find(Integer loginType) {
        Optional<AuthenticationProvider> optional = providers.stream().filter(provider -> provider.supports(loginType)).findFirst();
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new UnsupportedAuthenticationTypeException("不支持该认证类型: " + loginType);
    }
}
