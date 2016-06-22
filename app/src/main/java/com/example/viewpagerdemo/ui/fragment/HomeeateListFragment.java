package com.example.viewpagerdemo.ui.fragment;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.viewpagerdemo.ui.bean.EatOneBaen;
import com.example.viewpagerdemo.ui.bean.ShopingBean;
import com.example.viewpagerdemo.ui.bean.ShoppingListBean;
import com.example.viewpagerdemo.ui.MyApplication;
import com.example.viewpagerdemo.ui.adapter.AnnounceItemAdpter;
import com.example.viewpagerdemo.ui.adapter.EateMainAotuAdapter;
import com.example.viewpagerdemo.ui.jlfragmenwork.Contantor;
import com.example.viewpagerdemo.ui.jlfragmenwork.basefregmetwork.JLBaseFragment;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.TS;

import com.xingkesi.foodapp.R;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.Bind;

/**
 * Created by Administrator on 2016/5/20.
 * 首页商品
 */
public class HomeeateListFragment extends JLBaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.eat_vpone)
    ViewPager eatVpone;

    @Bind(R.id.eat_recycler)
    RecyclerView eatRecycler;
    @Bind(R.id.refreshlayout)
    SwipeRefreshLayout refreshlayout;

    @Bind(R.id.dotone)
    LinearLayout dot_layout;


    @Bind(R.id.sc)
    ScrollView sc;

    int page = 1;
    int num = 10;
    boolean tag = true;


    //-------------第1轮-----------------------
    private ArrayList<View> dots; // 图片标题正文的那些点
    private int currentItem = 0; // 当前图片的索引号
    ScheduledExecutorService scheduledExecutorService;
    EateMainAotuAdapter eateAdapter;
    // 轮播banner的数据
    private List<EatOneBaen> adList;


    //--------------列表---------------------------------
    ArrayList<ShoppingListBean> list;
    AnnounceItemAdpter eatReclerViewAdpter;
    int scrollPostion = 0;
    LinearLayoutManager manager;


    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            eatVpone.setCurrentItem(currentItem);
        }
    };


    @Override
    public int setViewLayout() {
        return R.layout.homeeatelistfragmet;
    }

    @Override
    public void SetData() {
        RequestBanner();
        RequsetDatas();
        sc.smoothScrollBy(0, 0);
        eatRecycler.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE && scrollPostion + 1
                        == eatReclerViewAdpter.getItemCount() && tag) {
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
                scrollPostion = ((LinearLayoutManager) eatRecycler.getLayoutManager()).findLastVisibleItemPosition();
                if (scrollPostion != eatReclerViewAdpter.getItemCount()) {
                    tag = true;
                }
            }
        });

    }


    @Override
    public void onPause() {
        super.onPause();
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdown();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    /**
     * 主页Banner图片
     */
    void RequestBanner() {
        new FinalHttp().post(Contantor.main_Banner, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                DD.v("轮播图:" + s);
                if (s != null) {
                    adList = JSONArray.parseArray(s, EatOneBaen.class);
                    for (int i = 0; i < adList.size(); i++) {
                        ImageView view = new ImageView(getActivity());
                        view.setBackgroundResource(R.drawable.dot_normal);
                        LinearLayout.LayoutParams viewL = new LinearLayout.LayoutParams(10, 10);
                        viewL.setMargins(5, 0, 5, 0);
                        view.setLayoutParams(viewL);
                        dots.add(view);
                        dot_layout.addView(view);
                    }
                    eateAdapter = new EateMainAotuAdapter(getActivity(), adList);
                    eatVpone.setAdapter(eateAdapter);// 设置填充ViewPager页面的适配器
                    eateAdapter.notifyDataSetChanged();
                    // 设置一个监听器，当ViewPager中的页面改变时调用
                    han.sendEmptyMessage(0);


                }
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }
        });

    }

    Handler han = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (eatVpone != null)
                eatVpone.setOnPageChangeListener(new MyPageChangeListener());

            startAd();
        }
    };


    /**
     * 请求主页商品列表
     */
    void RequsetDatas() {

        //showWait();
        AjaxParams map = new AjaxParams();
        map.put("page", page + "");//页码，第几页
        map.put("size", num + "");//每页几条
        map.put("city", com.example.viewpagerdemo.ui.MyApplication.getInstan().getCity());//
        DD.d("主页商品列表参数：" + map.toString());
        new FinalHttp().post(Contantor.main_eat, map, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                ShopingBean sb = JSON.parseObject(s, ShopingBean.class);
                list = sb.getList();
                DD.v("主页返回数据：" + s);
                if (refreshlayout.isRefreshing()) {
                    refreshlayout.setRefreshing(false);
                }
                if (list.size() > 0) {
                    eatReclerViewAdpter = new AnnounceItemAdpter(getActivity());
                    eatRecycler.setAdapter(eatReclerViewAdpter);
                    eatReclerViewAdpter.notifyDataSetChanged();
                    eatReclerViewAdpter.getArrayLists().addAll(list);
                   /* for (int i = 0; i < list.size(); i++) {
                        DD.d("主页数据打印：" + i + "===list==" + list.get(i).getId() + "==" + list.get(i).getName() + "==");
                    }*/
                } else {
                    page = page - 1;
                    num = num - 10;
                    TS.shortTime("没有数据");
                }
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                //closeWait();
                if (refreshlayout.isRefreshing()) {
                    refreshlayout.setRefreshing(false);
                }
            }
        });


    }


    @Override
    public void InitObject() {
        dots = new ArrayList<>();
        // 广告数据
        adList = new ArrayList<>();// = testgetBannerAd();
        list = new ArrayList<>();
        eatReclerViewAdpter = new AnnounceItemAdpter(getActivity());
        eatRecycler.setAdapter(eatReclerViewAdpter);
        eatRecycler.setHasFixedSize(true);
        manager = new LinearLayoutManager(getActivity());
        eatRecycler.setLayoutManager(manager);
        refreshlayout.setProgressBackgroundColorSchemeResource(R.color.white);
        refreshlayout.setColorSchemeColors(Color.BLUE);
        refreshlayout.setSize(SwipeRefreshLayout.DEFAULT);
        refreshlayout.setOnRefreshListener(this);
    }


    private void startAd() {
//        if(scheduledExecutorService==null)
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // 当Activity显示出来后，每两秒切换一次图片显示
        scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 2, TimeUnit.SECONDS);
    }

    @Override
    public void onRefresh() {
        page = 1;
        num = 10;
        refreshlayout.setRefreshing(true);
        RequsetDatas();
    }

    private class ScrollTask implements Runnable {

        @Override
        public void run() {
            synchronized (eatVpone) {
                currentItem = (currentItem + 1) % adList.size();
                handler.obtainMessage().sendToTarget();
            }
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    class MyPageChangeListener implements ViewPager.OnPageChangeListener {

        int oldPosition = 0;

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int position) {
            currentItem = position;
            dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
            dots.get(position).setBackgroundResource(R.drawable.dot_focused);
            oldPosition = position;
        }
    }


}
