package com.zs.ots.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.zs.ots.entity.User;
import com.zs.ots.mapper.LoginMapper;
import com.zs.ots.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author 张帅
 * @date 2020/2/8 11:51
 * @description 登录模块
 */
@RequestMapping
@RestController
@Api(tags = "登录模块")
public class LoginController {

    @Autowired
    LoginService loginService;


    @ApiOperation(value = "用户登录")
    @PostMapping(value = "/login")
    public User verifyUser(@RequestBody User user) {

        User result = loginService.verifyUser(user);
        return result;
    }
}
