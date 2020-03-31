package com.zs.ots.controller;

import com.zs.ots.entity.User;
import com.zs.ots.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 张帅
 * @date 2020/2/16 15:59
 * @description 用户信息的管理
 */
@RestController
@RequestMapping
@Api(tags = "个人信息管理")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "修改密码时核实原密码")
    @GetMapping(value = "/verifyUserPwd")
    public Boolean verifyUserPwd(@RequestParam("uid") String uid, @RequestParam("oldPwd") String upwd) {
        User user = userService.verifyUserPwd(uid, upwd);
        return user != null;
    }

    @ApiOperation(value = "个人修改密码")
    @GetMapping(value = "/updateUserPwd")
    public Boolean alterUserPwd(@RequestParam("uid") String uid, @RequestParam("newPwd") String upwd) {

        if (uid == "" || upwd == "") {
            return false;
        } else {
            return userService.updateUserPwd(uid, upwd);
        }
    }

    @ApiOperation(value = "添加新用户")
    @PostMapping(value = "/addUser")
    public Boolean addUser(@RequestBody User newUser) {
        return userService.addUser(newUser);
    }

    @ApiOperation(value = "新用户账户是否已存在")
    @GetMapping(value = "/validateNewAcc")
    public Boolean validateNewAcc(@RequestParam(value = "newUserAcc",
            required = false) String newUserAcc) {
        if (newUserAcc == null || newUserAcc == "") {
            return true;
        }
        User user = userService.validateNewAcc(newUserAcc);
        return user == null;
    }
}
