package com.zs.ots.service.impl;

import com.zs.ots.entity.User;
import com.zs.ots.mapper.UserMapper;
import com.zs.ots.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 张帅
 * @date 2020/3/26 21:23
 * @description
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User verifyUserPwd(String uid, String upwd) {
        return userMapper.verifyUserPwd(uid, upwd);
    }

    @Override
    public Boolean updateUserPwd(String uid, String upwd) {
        return userMapper.updateUserPwd(uid, upwd);
    }

    @Override
    public Boolean addUser(User newUser) {
        return userMapper.addUser(newUser);
    }

    @Override
    public User validateNewAcc(String newUserAcc) {
        return userMapper.validateNewAcc(newUserAcc);
    }
}
