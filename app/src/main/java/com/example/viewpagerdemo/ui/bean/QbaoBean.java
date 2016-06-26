package com.example.viewpagerdemo.ui.bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/18.
 */
public class QbaoBean {


    private String msg;
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

      /*  ArrayList<WallBean> detail;


        public ArrayList<WallBean> getDetail() {
            return detail;
        }

        public void setDetail(ArrayList<WallBean> detail) {
            this.detail = detail;
        }*/

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

    }
}
