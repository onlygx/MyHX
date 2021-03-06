package com.example.viewpagerdemo.ui;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.alibaba.wxlib.util.SysUtil;
import com.example.viewpagerdemo.ui.bean.UserBeanL;
import com.taobao.openimui.sample.InitHelper;
import com.umeng.socialize.PlatformConfig;
import com.yiw.circledemo2.bean.ListBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/19.
 */
public class MyApplication  extends MultiDexApplication {

    public static int postion=0;
    public static final String NAMESPACE = "foodapp";
    // 默认存放图片的路径
    public final static String DEFAULT_SAVE_IMAGE_PATH =
            Environment.getExternalStorageDirectory() + File.separator
            + "CircleDemo" + File.separator + "Images"
            + File.separator;
    private static Context sContext;

    public String sheng, city, qu,address;

    public static Context getContext() {
        return sContext;
    }
    public  static List<ListBean> datas,mdatas;//缓存 朋友圈信息，我的朋友圈
    static MyApplication instance;

    UserBeanL user;
    String userName;
    String userPwd;
    public static String APP_KEY = "23386286";


    public static String userPYId;

    public static int exit=0;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        datas=new ArrayList<>();
        mdatas=new ArrayList<>();
        sContext = getApplicationContext();
        initYMData();

        //todo Application.onCreate中，首先执行这部分代码，以下代码固定在此处，不要改动，这里return是为了退出Application.onCreate！！！
       /* if(mustRunFirstInsideApplicationOnCreate()){
            //todo 如果在":TCMSSevice"进程中，无需进行openIM和app业务的初始化，以节省内存
            return;
        }
        InitHelper.initYWSDK(this);
        InitHelper.initFeedBack(this);*/


    }
    private boolean mustRunFirstInsideApplicationOnCreate() {
        //必须的初始化
        SysUtil.setApplication(this);
        sContext = getApplicationContext();
        return SysUtil.isTCMSServiceProcess(sContext);
    }
    /**
     * 初始友盟分享配置
     */
    void initYMData() {
        //微信    wx12342956d1cab4f9,a5ae111de7d9ea137e88a5e02c07c94d
        PlatformConfig.setWeixin("wx56aabf3ace651b14", "adeb260f2f7553fc0ef99a958bcc5f3d");
        //豆瓣RENREN平台目前只能在服务器端配置
        //新浪微博 3213955885  8040a8e4ec9ce6c5f7724dc48e22c717
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad");
        //易信
        PlatformConfig.setYixin("yxc0614e80c9304c11b0391514d09f13bf");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        PlatformConfig.setTwitter("3aIN7fuF685MuZ7jtXkQxalyi", "MK6FEYG63eWcpDFgRYw4w9puJhzDl0tyuqWjZ3M7XJuuG7mMbO");
        PlatformConfig.setAlipay("2015111700822536");
        PlatformConfig.setLaiwang("laiwangd497e70d4", "d497e70d4c3e4efeab1381476bac4c5e");
        PlatformConfig.setPinterest("1439206");
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


   /* public List<ListBean> getDatas() {
        return datas;
    }

    public List<ListBean> getMdatas() {
        return mdatas;
    }

    public void setMdatas(List<ListBean> mdatas) {
        this.mdatas = mdatas;
    }

    public void setDatas(List<ListBean> datas) {
        this.datas = datas;
    }*/

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
