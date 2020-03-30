package com.zs.ots.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.zs.ots.entity.User;
import com.zs.ots.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

/**
 * @author 张帅
 * @date 2020/2/16 15:59
 * @description 用户信息的管理
 */
@RestController
@RequestMapping
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 个人修改密码时验证原密码信息
     *
     * @param uid
     * @param upwd
     * @return
     */
    @GetMapping(value = "/verifyUserPwd")
    public Boolean verifyUserPwd(@RequestParam("uid") String uid, @RequestParam("oldPwd") String upwd) {
        User user = userService.verifyUserPwd(uid, upwd);
        return user != null;
    }

    /**
     * 个人修改密码
     *
     * @param uid
     * @param upwd
     * @return
     */
    @GetMapping(value = "/updateUserPwd")
    public Boolean alterUserPwd(@RequestParam("uid") String uid, @RequestParam("newPwd") String upwd) {

        if (uid == "" || upwd == "") {
            return false;
        } else {
            return userService.updateUserPwd(uid, upwd);
        }
    }

    /**
     * 添加用户
     *
     * @param newUser
     * @return
     */
    @PostMapping(value = "/addUser")
    public Boolean addUser(@RequestBody User newUser) {
        return userService.addUser(newUser);
    }

    /**
     * 验证账户是否重复
     *
     * @param newUserAcc
     * @return
     */
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
