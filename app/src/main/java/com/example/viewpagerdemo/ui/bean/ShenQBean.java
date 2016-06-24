package com.example.viewpagerdemo.ui.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/22.
 */
public class ShenQBean implements Serializable {

    /**
     * name :
     * id : 1018401784510169664
     * userId : 6337053024532152024
     * phone : 13306400282
     * friendUser : {"name":"","id":6337053024532152024,"head":"/images/headImage/user_6250885874746899921.jpg","intro":"","nickName":"18363088168","phone":"18363088168","sex":"","qq":"","thinksId":"18363088168","wechat":""}
     * friendStatus : 2
     * friendUserId : 7227619999906416263
     */

    private String name;
    private String id;
    private String userId;
    private String phone;
    /**
     * name :
     * id : 6337053024532152024
     * head : /images/headImage/user_6250885874746899921.jpg
     * intro :
     * nickName : 18363088168
     * phone : 18363088168
     * sex :
     * qq :
     * thinksId : 18363088168
     * wechat :
     */

    private FriendUserBean friendUser;
    private int friendStatus;
    private String friendUserId;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public FriendUserBean getFriendUser() {
        return friendUser;
    }

    public void setFriendUser(FriendUserBean friendUser) {
        this.friendUser = friendUser;
    }

    public int getFriendStatus() {
        return friendStatus;
    }

    public void setFriendStatus(int friendStatus) {
        this.friendStatus = friendStatus;
    }

    public String getFriendUserId() {
        return friendUserId;
    }

    public void setFriendUserId(String friendUserId) {
        this.friendUserId = friendUserId;
    }

    public static class FriendUserBean implements Serializable{
        private String name;
        private String id;
        private String head;
        private String intro;
        private String nickName;
        private String phone;
        private String sex;
        private String qq;
        private String thinksId;
        private String wechat;

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

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
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

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
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

        public String getWechat() {
            return wechat;
        }

        public void setWechat(String wechat) {
            this.wechat = wechat;
        }
    }
}
