package com.zs.ots.mapper;

import com.zs.ots.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 张帅
 * @date 2020/2/20 11:46
 * @description 用户信息mapper文件
 */
@Repository
public interface UserInfoMapper {

    List<User> getPageUserInfo();

    List<User> getAllUserInfo();

    User verifyAccount(String uaccount, String uid);

    boolean alterUserInfo(User user);

    Boolean removeStudent(Integer id);

    List<User> searchUserByAcc(String userAcc);

    List<User> getAllStuInfo();

    List<User> getPageStuInfo(Integer pageNum, Integer pageSize);
}
