package com.example.viewpagerdemo.ui.jlfragmenwork.util;

import android.widget.Toast;

import com.example.viewpagerdemo.ui.MyApplication;

/**
 * Created by Administrator on 2016/5/19.
 */
public class TS {

    public static void shortTime(String str){
        Toast.makeText(MyApplication.getContext(), str, Toast.LENGTH_SHORT).show();
    }
    public static void longTime(String str){
        Toast.makeText(MyApplication.getContext(), str, Toast.LENGTH_LONG).show();
    }

    public static  void PlaseNet(){
        TS.shortTime("请连接网络");
    }

}
