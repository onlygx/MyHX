package com.example.viewpagerdemo.ui.bean;

/**
 * Created by Administrator on 2016/6/18.
 */
public class QbaoBean {

    /**
     * msg :
     * data : {"balance":"0.0000","detail":""}
     * state : true
     */

    private String msg;
    /**
     * balance : 0.0000
     * detail :
     */

    private DataBean data;
    private String state;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public static class DataBean {
        private String balance;
        private String detail;

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }
    }
}
