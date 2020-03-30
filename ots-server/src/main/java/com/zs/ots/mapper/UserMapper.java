package com.zs.ots.mapper;

import com.zs.ots.entity.User;

/**
 * @author 张帅
 * @date 2020/2/17 11:10
 * @description  用户操作mapper文件
 */
public interface UserMapper {


    User verifyUserPwd(String uid, String upwd);

    Boolean updateUserPwd(String uid, String upwd);

    Boolean addUser(User newUser);

    User validateNewAcc(String newUserAcc);
}
