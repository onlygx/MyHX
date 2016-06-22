package com.example.viewpagerdemo.ui.bean;

/**
 * Created by Administrator on 2016/5/24.
 */
public class BaseBean {


    /**
     * success : true
     * code : null
     */

    private boolean success;
    private String code;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
