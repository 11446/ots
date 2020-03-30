package com.zs.ots.service;

import com.zs.ots.entity.User;
import com.zs.ots.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 张帅
 * @date 2020/2/8 12:39
 * @description
 */

public interface LoginService {

    /**
     * 验证用户信息
     * @param user
     * @return
     */
    public User verifyUser(User user);
}
