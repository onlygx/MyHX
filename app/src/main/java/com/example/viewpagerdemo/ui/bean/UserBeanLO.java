package com.example.viewpagerdemo.ui.bean;

/**
 * Created by Administrator on 2016/5/26.
 */
public class UserBeanLO {


    /**
     * ids : null
     * seCode : null
     * id : 18660132803
     * name : 18660132803
     * head : /images/account/defaultHead.jpg
     * sex : null
     * intro : null
     * thinksId : null
     */

//    private String ids;
//    private String seCode;
    private long id;
    private String name;
    private String head;
    private String sex;
    private String intro;
    private String thinksId;
    private String phone;
    private String wechat;
    private String qq;
    private String nickName;


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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getThinksId() {
        return thinksId;
    }

    public void setThinksId(String thinksId) {
        this.thinksId = thinksId;
    }
}
