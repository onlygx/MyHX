package com.example.viewpagerdemo.ui.fragment;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.viewpagerdemo.ui.bean.EatOneBaen;
import com.example.viewpagerdemo.ui.bean.ShopingBean;
import com.example.viewpagerdemo.ui.bean.ShoppingListBanerBean;
import com.example.viewpagerdemo.ui.bean.ShoppingListBean;
import com.example.viewpagerdemo.ui.adapter.AnnounceItemAdpter;
import com.example.viewpagerdemo.ui.adapter.EateMainAotuAdapter;
import com.example.viewpagerdemo.ui.Contantor;
import com.example.viewpagerdemo.ui.jlfragmenwork.basefregmetwork.JLBaseFragment;

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

    int page = 1;
    int num = 10;
    boolean tag = true;

    private ShoppingListBean  bannerBean;

    ArrayList<ShoppingListBean> mDatas = new ArrayList<>();
    AnnounceItemAdpter mAdpter;
    int scrollPostion = 0;

    @Override
    public int setViewLayout() {
        return R.layout.homeeatelistfragmet;
    }


    @Override
    public void InitObject() {
        // 广告数据
        recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdpter = new AnnounceItemAdpter(getActivity(),mDatas);
        recycler_view.setAdapter(mAdpter);
        recycler_view.setAdapter(mAdpter);
/*        recycler_view.setHasFixedSize(true);
        refreshlayout.setProgressBackgroundColorSchemeResource(R.color.white);
        refreshlayout.setColorSchemeColors(Color.BLUE);
        refreshlayout.setSize(SwipeRefreshLayout.DEFAULT);*/
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
                 List<ShoppingListBanerBean>  bannerList = JSONArray.parseArray(s, ShoppingListBanerBean.class);

                    ShoppingListBean bannerListBean = new ShoppingListBean();
                    bannerListBean.setBannerList(bannerList);
                    bannerListBean.setIsBanner(1);
                    bannerBean = bannerListBean;
                    List<ShoppingListBean> listBanner = new ArrayList<>();
                    listBanner.add(bannerListBean);
                    for(int i=0;i<mDatas.size();i++){
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
                ArrayList<ShoppingListBean> list = sb.getList();

                if (refreshlayout.isRefreshing()) {
                    refreshlayout.setRefreshing(false);
                }
                if(sb!=null && sb.getList().size()>0) {
                    //第一页
                    if(page ==1){
                        mDatas.clear();
                        if(bannerBean!=null){
                            mDatas.add(bannerBean);
                        }
                    }

                    for (int i = 0; i < sb.getList().size(); i++) {
                        mDatas.add(sb.getList().get(i));
                    }

                    if (sb.getList().size()<10) {
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

}
