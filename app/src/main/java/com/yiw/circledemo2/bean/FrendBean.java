package com.yiw.circledemo2.bean;

import android.util.Log;

import java.util.List;

/**
 * Created by huaping on 2016/6/20.
 */

public class FrendBean {



    private List<ListBean> list;

    public List<ListBean> getList() {
        Log.d("LD","调用");

        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }


}
