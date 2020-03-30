package com.zs.ots.controller;

import com.zs.ots.entity.User;
import com.zs.ots.mapper.LoginMapper;
import com.zs.ots.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 张帅
 * @date 2020/2/8 11:51
 * @description 登录模块
 */
@RequestMapping
@RestController
@Validated
public class LoginController {

    @Autowired
    LoginService loginService;

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User verifyUser(@RequestBody User user) {
        User result = loginService.verifyUser(user);
        return result;
    }
}
