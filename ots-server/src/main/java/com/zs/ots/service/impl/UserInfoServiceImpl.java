package com.zs.ots.service.impl;

import com.zs.ots.entity.User;
import com.zs.ots.mapper.UserInfoMapper;
import com.zs.ots.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 张帅
 * @date 2020/3/26 21:18
 * @description
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public List<User> getPageUserInfo() {
        return userInfoMapper.getPageUserInfo();
    }

    @Override
    public List<User> getAllUserInfo() {
        return userInfoMapper.getAllUserInfo();

    }

    @Override
    public User verifyAccount(String uaccount, String uid) {
        return userInfoMapper.verifyAccount(uaccount, uid);

    }

    @Override
    public boolean alterUserInfo(User user) {
        return userInfoMapper.alterUserInfo(user);

    }

    @Override
    public Boolean removeStudent(Integer id) {
        return userInfoMapper.removeStudent(id);

    }

    @Override
    public List<User> searchUserByAcc(String userAcc) {
        return userInfoMapper.searchUserByAcc(userAcc);

    }

    @Override
    public List<User> getAllStuInfo() {
        return userInfoMapper.getAllStuInfo();
    }

    @Override
    public List<User> getPageStuInfo(Integer pageNum, Integer pageSize) {
        return userInfoMapper.getPageStuInfo(pageNum,pageSize);
    }
}
