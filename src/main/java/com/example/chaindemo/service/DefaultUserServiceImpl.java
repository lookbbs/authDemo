package com.example.chaindemo.service;

import com.example.chaindemo.pojo.UserDetail;
import org.springframework.stereotype.Service;

/**
 * @author yuandongfei
 * @date 2019/5/13
 */
@Service
public class DefaultUserServiceImpl implements UserService {

    @Override
    public UserDetail getByUsername(String username) {
        if ("admin".equalsIgnoreCase(username)) {
            UserDetail vo = new UserDetail();
            vo.setUserName(username);
            return vo;
        }
        return null;
    }
}
