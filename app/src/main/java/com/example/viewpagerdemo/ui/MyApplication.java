package com.example.viewpagerdemo.ui;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.support.multidex.MultiDex;

import com.example.viewpagerdemo.ui.bean.UserBeanL;

import java.io.File;

/**
 * Created by Administrator on 2016/5/19.
 */
public class MyApplication extends Application {

    public static int postion=0;

    // 默认存放图片的路径
    public final static String DEFAULT_SAVE_IMAGE_PATH = Environment.getExternalStorageDirectory() + File.separator + "CircleDemo" + File.separator + "Images"
            + File.separator;
    private static Context sContext;

    public String sheng, city, qu,address;

    public static Context getContext() {
        return sContext;
    }

    static MyApplication instance;

    UserBeanL user;
    String userName;
    String userPwd;
    public static String APP_KEY = "23386286";


    public static String userPYId;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        sContext = getApplicationContext();



    }



    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public String getSheng() {
        return sheng;
    }

    public void setSheng(String sheng) {
        this.sheng = sheng;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getQu() {
        return qu;
    }

    public void setQu(String qu) {
        this.qu = qu;
    }

    public UserBeanL getUser() {
        return user;
    }

    public void setUser(UserBeanL user) {
        this.user = user;
    }


    public static MyApplication getInstan() {
        return instance;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static String getUserPYId() {
        return userPYId;
    }

    public static void setUserPYId(String userPYId) {
        MyApplication.userPYId = userPYId;
    }
}
