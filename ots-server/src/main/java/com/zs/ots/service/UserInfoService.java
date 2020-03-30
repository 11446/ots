package com.zs.ots.service;

import com.zs.ots.entity.User;
import com.zs.ots.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 张帅
 * @date 2020/2/20 11:46
 * @description  用户信息Service层
 */

public interface UserInfoService {

    /**
     * 分页获取用户信息
     * @return
     */
    public List<User> getPageUserInfo();

    /**
     * 获取所有用户信息
     * @return
     */
    public List<User> getAllUserInfo();

    /**
     * 验证账号是否重复
     * @param uaccount
     * @param uid
     * @return
     */
    public User verifyAccount(String uaccount, String uid) ;

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    public boolean alterUserInfo(User user) ;

    /**
     * 移除学生
     * @param id
     * @return
     */
    public Boolean removeStudent(Integer id) ;

    /**
     * 根据账号模糊查找用户
     * @param userAcc
     * @return
     */
    public List<User> searchUserByAcc(String userAcc) ;

    /**
     * 获取所有学生信息
     * @return
     */
    public List<User> getAllStuInfo();

    /**
     * 分页获取学生信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<User> getPageStuInfo(Integer pageNum, Integer pageSize);
}
