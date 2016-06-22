package com.yiw.circledemo2.utils;

/**
 * Created by huaping on 2016/6/17.
 * 可删除
 */

public class UserOne {

    /**
     * success : true
     */

    private boolean success;
    UserInfo data;


    public UserInfo getData() {
        return data;
    }

    public void setData(UserInfo data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
