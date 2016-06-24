package com.example.viewpagerdemo.ui.bean;

/**
 * Created by Administrator on 2016/6/18.
 */
public class AddBookBean {
    private String name;
    private long id;
    private long friendId;
    private int friendStatus;
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


}
