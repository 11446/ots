package com.zs.ots.service.impl;

import com.zs.ots.entity.User;
import com.zs.ots.mapper.LoginMapper;
import com.zs.ots.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 张帅
 * @date 2020/3/26 21:14
 * @description
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginMapper loginMapper;


    @Override
    public User verifyUser(User user) {
        return loginMapper.verifyUser(user);
    }
}
