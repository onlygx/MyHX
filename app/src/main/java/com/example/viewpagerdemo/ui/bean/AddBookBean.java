package com.example.viewpagerdemo.ui.bean;

/**
 * Created by Administrator on 2016/6/18.
 */
public class AddBookBean {


    /**
     * name : 小唐人
     * id : 8493957300801915172
     * friendId : 8988110878234332042
     * friendStatus : 0
     * friendUser : {"id":8988110878234332042,"head":"/images/account/defaultHead.jpg","phone":"13306400285","nickName":"13306400285","thinksId":"13306400285"}
     * phone : 13306400285
     * userId : 8984249178456606085
     */

    private String name;
    private long id;
    private long friendId;
    private int friendStatus;
    /**
     * id : 8988110878234332042
     * head : /images/account/defaultHead.jpg
     * phone : 13306400285
     * nickName : 13306400285
     * thinksId : 13306400285
     */

    private FriendUserBean friendUser;
    private String phone;
    private long userId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFriendId() {
        return friendId;
    }

    public void setFriendId(long friendId) {
        this.friendId = friendId;
    }

    public int getFriendStatus() {
        return friendStatus;
    }

    public void setFriendStatus(int friendStatus) {
        this.friendStatus = friendStatus;
    }

    public FriendUserBean getFriendUser() {
        return friendUser;
    }

    public void setFriendUser(FriendUserBean friendUser) {
        this.friendUser = friendUser;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public static class FriendUserBean {
        private long id;
        private String head;
        private String phone;
        private String nickName;
        private String thinksId;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getThinksId() {
            return thinksId;
        }

        public void setThinksId(String thinksId) {
            this.thinksId = thinksId;
        }
    }
}
