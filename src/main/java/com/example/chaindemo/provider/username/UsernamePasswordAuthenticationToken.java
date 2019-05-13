package com.example.chaindemo.provider.username;

import com.example.chaindemo.provider.AuthenticationToken;
import lombok.Data;

/**
 * @author yuandongfei
 * @date 2019/4/7
 */
@Data
public class UsernamePasswordAuthenticationToken extends AuthenticationToken {
    private String password;
}
