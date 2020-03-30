package com.zs.ots.entity;

/**
 * @author 张帅
 * @date 2020/2/24 18:11
 * @description
 */
public class Message {
    private Integer id;
    private Integer mesUid;
    private String mesUname;
    private String mesTitle;
    private String mesContent;
    private String mesTime;
    private String mesUrole;

    public Message(Integer id, Integer mesUid, String mesUname, String mesTitle, String mesContent, String mesTime, String mesUrole) {
        this.id = id;
        this.mesUid = mesUid;
        this.mesUname = mesUname;
        this.mesTitle = mesTitle;
        this.mesContent = mesContent;
        this.mesTime = mesTime;
        this.mesUrole = mesUrole;
    }

    public Message() {
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", mesUid=" + mesUid +
                ", mesUname='" + mesUname + '\'' +
                ", mesTitle='" + mesTitle + '\'' +
                ", mesContent='" + mesContent + '\'' +
                ", mesTime='" + mesTime + '\'' +
                ", mesUrole='" + mesUrole + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMesUid() {
        return mesUid;
    }

    public void setMesUid(Integer mesUid) {
        this.mesUid = mesUid;
    }

    public String getMesUname() {
        return mesUname;
    }

    public void setMesUname(String mesUname) {
        this.mesUname = mesUname;
    }

    public String getMesTitle() {
        return mesTitle;
    }

    public void setMesTitle(String mesTitle) {
        this.mesTitle = mesTitle;
    }

    public String getMesContent() {
        return mesContent;
    }

    public void setMesContent(String mesContent) {
        this.mesContent = mesContent;
    }

    public String getMesTime() {
        return mesTime;
    }

    public void setMesTime(String mesTime) {
        this.mesTime = mesTime;
    }

    public String getMesUrole() {
        return mesUrole;
    }

    public void setMesUrole(String mesUrole) {
        this.mesUrole = mesUrole;
    }
}
