package com.zs.ots.controller;

import com.github.pagehelper.PageHelper;
import com.zs.ots.entity.User;
import com.zs.ots.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 张帅
 * @date 2020/2/20 10:40
 * @description 查询操作用户信息
 */
@RestController
@RequestMapping
@Api(tags = "用户信息管理")
public class UserInfoController {

    @Autowired
    UserInfoService userInfoService;


    @ApiOperation(value = "分页查询用户信息")
    @GetMapping(value = "/getPageUserInfo")
    public List<User> getPageUserInfo(@RequestParam("PageNum") Integer PageNum,
                                      @RequestParam("PageSize") Integer PageSize,
                                      @RequestParam("urole") String urole,
                                      HttpServletRequest request) {
        PageHelper.startPage(PageNum, PageSize);
        if ("admin".equals(urole)) {
            List<User> userPageInfo = userInfoService.getPageUserInfo();
            return userPageInfo;
        } else {
            List<User> stuPageInfo = userInfoService.getPageStuInfo(PageNum, PageSize);
            return stuPageInfo;
        }
    }

    @ApiOperation(value = "获取所有用户信息")
    @GetMapping(value = "/getAllUserInfo")
    public List<User> getAllUserInfo(@RequestParam("urole") String urole) {

        if ("admin".equals(urole)) {
            List<User> allUserInfo = userInfoService.getAllUserInfo();
            return allUserInfo;
        } else {
            List<User> allStuInfo = userInfoService.getAllStuInfo();
            return allStuInfo;
        }
    }


    @ApiOperation(value = "修改用户信息时验证信息是否重复")
    @GetMapping(value = "/verifyAccount")
    public boolean verifyAccount(@RequestParam(value = "uaccount", required = false) String uaccount,
                                 @RequestParam(value = "uid", required = false) String uid) {

        if (uaccount == "" || uid == "" || uaccount == null || uid == null) {
            return true;
        } else {
            User user = userInfoService.verifyAccount(uaccount, uid);
            return user == null;
        }
    }

    @ApiOperation(value = "修改用户信息")
    @PostMapping(value = "/alterUserInfo")
    public boolean alterUserInfo(@RequestBody User user) {
        return userInfoService.alterUserInfo(user);
    }


    @ApiOperation(value = "移除用户")
    @DeleteMapping(value = "/removeStudent")
    public Boolean removeStudent(@RequestParam("id") Integer id) {
        return userInfoService.removeStudent(id);
    }

    @ApiOperation(value = "用户模糊查找")
    @GetMapping(value = "/searchUserByAcc")
    public List<User> searchUserByAcc(@RequestParam("userAcc") String userAcc) {
        userAcc = "%" + userAcc + "%";
        return userInfoService.searchUserByAcc(userAcc);
    }

}
