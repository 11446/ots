package com.zs.ots.entity;

import javax.validation.constraints.NotNull;

/**
 * @author 张帅
 * @date 2020/2/8 12:12
 * @description 用户表实体类
 */

public class User {

    private Integer id;
    private String uname;

    @NotNull(message = "账号不能为空")
    private String uaccount;
    @NotNull(message = "密码不能为空")
    private String upwd;
    private String urole;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", uaccount='" + uaccount + '\'' +
                ", uname='" + uname + '\'' +
                ", upwd='" + upwd + '\'' +
                ", urole='" + urole + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUaccount() {
        return uaccount;
    }

    public void setUaccount(String uaccount) {
        this.uaccount = uaccount;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    public String getUrole() {
        return urole;
    }

    public void setUrole(String urole) {
        this.urole = urole;
    }
}
