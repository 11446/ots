package com.zs.ots.controller;

import com.github.pagehelper.PageHelper;
import com.zs.ots.entity.User;
import com.zs.ots.service.UserInfoService;
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
public class UserInfoController {

    @Autowired
    UserInfoService userInfoService;

    /**
     * 分页查询用户信息
     *
     * @param PageNum
     * @param PageSize
     * @return
     */
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

    /**
     * 获取所有用户信息
     *
     * @return
     */
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

    /**
     * 验证账号是否重复
     *
     * @param uaccount
     * @param uid
     * @return
     */
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

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    @PostMapping(value = "/alterUserInfo")
    public boolean alterUserInfo(@RequestBody User user) {
        return userInfoService.alterUserInfo(user);
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/removeStudent")
    public Boolean removeStudent(@RequestParam("id") Integer id) {
        return userInfoService.removeStudent(id);
    }

    /**
     * 模糊查找用户
     *
     * @param userAcc
     * @return
     */
    @GetMapping(value = "/searchUserByAcc")
    public List<User> searchUserByAcc(@RequestParam("userAcc") String userAcc) {
        userAcc = "%" + userAcc + "%";
        return userInfoService.searchUserByAcc(userAcc);
    }

}
