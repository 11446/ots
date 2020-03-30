package com.zs.ots.service;

import com.zs.ots.entity.User;
import com.zs.ots.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 张帅
 * @date 2020/2/17 11:10
 * @description
 */
public interface UserService {

    /**
     * 验证用户密码
     *
     * @param uid
     * @param upwd
     * @return
     */
    public User verifyUserPwd(String uid, String upwd);

    /**
     * 修改用户密码
     *
     * @param uid
     * @param upwd
     * @return
     */
    public Boolean updateUserPwd(String uid, String upwd);

    /**
     * 添加用户
     *
     * @param newUser
     * @return
     */
    public Boolean addUser(User newUser);

    /**
     * 验证新账号是否重复
     *
     * @param newUserAcc
     * @return
     */
    public User validateNewAcc(String newUserAcc);


}