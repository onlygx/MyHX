package com.yiw.circledemo2.bean;

/**
 * Created by huaping on 2016/6/20.
 */

public  class ChildBean {
    private long id;
    private int type;
    private String content;
    private String myName;
    private UserBean user;
    private long receiveId;
    private ReceiveUserBean receiveUser;
    private long setTime;
    private long userId;
    private long infoId;

    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public long getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(long receiveId) {
        this.receiveId = receiveId;
    }

    public ReceiveUserBean getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(ReceiveUserBean receiveUser) {
        this.receiveUser = receiveUser;
    }

    public long getSetTime() {
        return setTime;
    }

    public void setSetTime(long setTime) {
        this.setTime = setTime;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getInfoId() {
        return infoId;
    }

    public void setInfoId(long infoId) {
        this.infoId = infoId;
    }




}
