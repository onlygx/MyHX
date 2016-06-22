package com.yiw.circledemo2.bean;

/**
 * Created by huaping on 2016/6/20.
 */

public class UserBean {
    private String name;
    private String id;
    private String head;
    private String nickName;
    private String phone;
    private String wechat;
    private String qq;
    private String thinksId;

    public UserBean(){};

    public UserBean(String s, String name, String s1) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getThinksId() {
        return thinksId;
    }

    public void setThinksId(String thinksId) {
        this.thinksId = thinksId;
    }


}