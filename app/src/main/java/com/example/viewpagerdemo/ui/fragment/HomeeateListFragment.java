package com.example.viewpagerdemo.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.mobileim.YWChannel;
import com.alibaba.mobileim.channel.event.IWxCallback;
import com.alibaba.mobileim.channel.util.YWLog;
import com.alibaba.mobileim.login.YWLoginCode;
import com.alibaba.mobileim.utility.IMPrefsTools;
import com.example.viewpagerdemo.ui.MyApplication;
import com.example.viewpagerdemo.ui.activity.MainActivity;
import com.example.viewpagerdemo.ui.bean.EatOneBaen;
import com.example.viewpagerdemo.ui.bean.ShopingBean;
import com.example.viewpagerdemo.ui.bean.ShoppingListBanerBean;
import com.example.viewpagerdemo.ui.bean.ShoppingListBean;
import com.example.viewpagerdemo.ui.adapter.AnnounceItemAdpter;
import com.example.viewpagerdemo.ui.adapter.EateMainAotuAdapter;
import com.example.viewpagerdemo.ui.Contantor;
import com.example.viewpagerdemo.ui.bean.UserBeanL;
import com.example.viewpagerdemo.ui.jlfragmenwork.actvity.RegisterActivity;
import com.example.viewpagerdemo.ui.jlfragmenwork.basefregmetwork.JLBaseFragment;

import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.TS;
import com.taobao.openimui.sample.LoginSampleHelper;
import com.taobao.openimui.sample.NotificationInitSampleHelper;
import com.taobao.openimui.sample.UserProfileSampleHelper;
import com.taobao.openimui.sample.YWSDKGlobalConfigSample;
import com.xingkesi.foodapp.R;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/5/20.
 * 首页商品
 */
public class HomeeateListFragment extends JLBaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.eat_recycler)
    RecyclerView recycler_view;
    @Bind(R.id.refreshlayout)
    SwipeRefreshLayout refreshlayout;
    private static final String USER_ID = "userId";
    private static final String PASSWORD = "password";
    private LoginSampleHelper loginHelper;
    int page = 1;
    int num = 10;
    boolean tag = true;

    private ShoppingListBean bannerBean;

    ArrayList<ShoppingListBean> mDatas = new ArrayList<>();
    AnnounceItemAdpter mAdpter;
    int scrollPostion = 0;

    @Override
    public int setViewLayout() {
        return R.layout.homeeatelistfragmet;
    }


    @Override
    public void InitObject() {

        //未登录时
        if (MyApplication.getInstan().getUser() == null) {
            loginHelper = LoginSampleHelper.getInstance();
            //读取登录成功后保存的用户名和密码
            String localId = IMPrefsTools.getStringPrefs(getActivity(), USER_ID, "");
            String localPassword = IMPrefsTools.getStringPrefs(getActivity(), "thinkPwd", "");
            init(localId, MyApplication.APP_KEY);
            DD.v("没有登录：" + localId + "===" + localPassword);
            if (!TextUtils.isEmpty(localId) && !TextUtils.isEmpty(localPassword)) {
                login(localId, localPassword);
            }
        }
        // 广告数据
        recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdpter = new AnnounceItemAdpter(getActivity(), mDatas);
        recycler_view.setAdapter(mAdpter);
        recycler_view.setAdapter(mAdpter);
        refreshlayout.setOnRefreshListener(this);
        mAdpter.notifyDataSetChanged();

        recycler_view.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && scrollPostion + 1
                        == mAdpter.getItemCount() && tag) {
                    page = page + 1;
                    num = num + 10;
                    RequsetDatas();
                    tag = false;
                    refreshlayout.setRefreshing(true);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                scrollPostion = ((LinearLayoutManager) recycler_view.getLayoutManager()).findLastVisibleItemPosition();
                if (scrollPostion != mAdpter.getItemCount()) {
                    tag = true;
                }
            }
        });
    }


    @Override
    public void SetData() {
        RequestBanner();
        RequsetDatas();
    }


    /**
     * 主页Banner图片
     */
    void RequestBanner() {
        new FinalHttp().post(Contantor.main_Banner, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                if (s != null) {
                    List<ShoppingListBanerBean> bannerList = JSONArray.parseArray(s, ShoppingListBanerBean.class);

                    ShoppingListBean bannerListBean = new ShoppingListBean();
                    bannerListBean.setBannerList(bannerList);
                    bannerListBean.setIsBanner(1);
                    bannerBean = bannerListBean;
                    List<ShoppingListBean> listBanner = new ArrayList<>();
                    listBanner.add(bannerListBean);
                    for (int i = 0; i < mDatas.size(); i++) {
                        listBanner.add(mDatas.get(i));
                    }
                    mDatas.clear();
                    mDatas.addAll(listBanner);
                    mAdpter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }
        });

    }


    /**
     * 请求主页商品列表
     */
    void RequsetDatas() {
        AjaxParams map = new AjaxParams();
        map.put("page", page + "");//页码，第几页
        map.put("size", num + "");//每页几条
        map.put("city", com.example.viewpagerdemo.ui.MyApplication.getInstan().getCity());//
        new FinalHttp().post(Contantor.main_eat, map, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                ShopingBean sb = JSON.parseObject(s, ShopingBean.class);
               // ArrayList<ShoppingListBean> list = sb.getList();

                if (refreshlayout.isRefreshing()) {
                    refreshlayout.setRefreshing(false);
                }
                if (sb != null && sb.getList().size() > 0) {
                    //第一页
                    if (page == 1) {
                        mDatas.clear();
                        if (bannerBean != null) {
                            mDatas.add(bannerBean);
                        }
                    }

                    for (int i = 0; i < sb.getList().size(); i++) {
                        mDatas.add(sb.getList().get(i));
                    }

                    if (sb.getList().size() < 10) {
                    } else {
                        page = page - 1;
                        num = num - 10;
                    }
                    mAdpter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                if (refreshlayout.isRefreshing()) {
                    refreshlayout.setRefreshing(false);
                }
            }
        });

    }


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    public void onRefresh() {
        page = 1;
        num = 10;
        refreshlayout.setRefreshing(true);
        RequsetDatas();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    /**
     * 登录
     */
    public void login(final String name, String paws) {
        DD.v("没有登录---读取到信息：" + name + "===" + paws);
        AjaxParams ajaxParams = new AjaxParams();
        ajaxParams.put("thinksId", name);
        ajaxParams.put("password", paws);
        DD.d("开始登陆：" + Contantor.logdin + "?" + ajaxParams.toString());
        new FinalHttp().post(Contantor.logdin, ajaxParams, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {

                DD.d("登录0:" + s);
                UserBeanL user = JSON.parseObject(s, UserBeanL.class);

                if (user.isSuccess()) {
                    DD.d("登录ID:" + user.getData().getId() + "===" + user.getData().getThinksId());
                    MyApplication.getInstan().setUser(user);
                    MyApplication.getInstan().setUserName(name);
                    //---------正式版请将下面的放开--------------------
                    LogdindOpenIME(name);

                } else {
                    TS.shortTime("登录失败,请重新登录");
                }
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);

            }
        });
    }

    private void init(String userId, String appKey) {
        //初始化imkit
        DD.i("login 旺旺 初始化1!");
        LoginSampleHelper.getInstance().initIMKit(userId, appKey);
        DD.i("login 旺旺 初始化2!");
        //自定义头像和昵称回调初始化(如果不需要自定义头像和昵称，则可以省去)
        UserProfileSampleHelper.initProfileCallback();
        //通知栏相关的初始化
        NotificationInitSampleHelper.init();

    }

    void LogdindOpenIME(final String userId) {
        LoginSampleHelper.APP_KEY = MyApplication.APP_KEY;
        String wanName = MyApplication.getInstan().getUser().getData().getThinksId();
        init(wanName, MyApplication.APP_KEY);
        DD.i("login 开始 旺旺 登录!账号:" + wanName);
        loginHelper.login_Sample(wanName, "123456", MyApplication.APP_KEY, new IWxCallback() {

            @Override
            public void onSuccess(Object... arg0) {
                saveIdPasswordToLocal(userId.toString(), "123456");
                DD.i("login 旺旺 登录成功!");
                MyApplication.getInstan().setUserName(userId.toString());
                MyApplication.getInstan().setUserPwd("123456");
            }

            @Override
            public void onProgress(int arg0) {

            }

            @Override
            public void onError(int errorCode, String errorMessage) {
                //progressBar.setVisibility(View.GONE);
                closeWait();
                DD.i("login 旺旺 失败1：" + errorMessage + "===" + errorCode);
            }
        });
    }

    /**
     * 保存登录的用户名密码到本地
     *
     * @param userId
     * @param password
     */
    private void saveIdPasswordToLocal(String userId, String password) {
        IMPrefsTools.setStringPrefs(getActivity(), USER_ID, userId);
        IMPrefsTools.setStringPrefs(getActivity(), PASSWORD, password);
    }


}
