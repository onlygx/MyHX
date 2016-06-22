package com.example.viewpagerdemo.ui.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.example.viewpagerdemo.ui.MyApplication;
import com.example.viewpagerdemo.ui.adapter.CityAdapter;
import com.example.viewpagerdemo.ui.adapter.OthorShopListItemAdpter;
import com.example.viewpagerdemo.ui.adapter.ShenQAdpter;
import com.example.viewpagerdemo.ui.bean.AddBookBean;
import com.example.viewpagerdemo.ui.bean.ShenQBean;
import com.example.viewpagerdemo.ui.bean.ShoppingListBean;
import com.example.viewpagerdemo.ui.jlfragmenwork.Contantor;
import com.example.viewpagerdemo.ui.jlfragmenwork.baseactivitywork.JLBaseActivity;
import com.example.viewpagerdemo.ui.jlfragmenwork.city.CityData;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.TS;
import com.xingkesi.foodapp.R;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


/**
 * 处理好友申请
 */
public class ShenQFrendMainActivity extends JLBaseActivity implements SwipeRefreshLayout.OnRefreshListener, ShenQAdpter.ShenqC {
    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.iv_right_image)
    ImageView iv_right_image;
    @Bind(R.id.iv_back)
    ImageView iv_back;

    @Bind(R.id.eat_recycler)
    RecyclerView eatRecycler;
    @Bind(R.id.refreshlayout)
    SwipeRefreshLayout refreshlayout;

    int page = 1;
    int num = 10;
    //--------------列表---------------------------------
    List<ShenQBean> list;
    ShenQAdpter eatReclerViewAdpter;
    LinearLayoutManager manager;

    @Override
    public int setViewLayout() {
        return R.layout.activity_shen_qfrend_main;
    }

    @Override
    public void initObject() {
        super.initObject();
    }

    @Override
    public void initID() {
        super.initID();
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setImageResource(R.drawable.cp_fh);
        tv_title.setText("好友申请列表");

        eatReclerViewAdpter = new ShenQAdpter(this,this);
        eatRecycler.setAdapter(eatReclerViewAdpter);
        eatRecycler.setHasFixedSize(true);
        manager = new LinearLayoutManager(this);
        eatRecycler.setLayoutManager(manager);
        refreshlayout.setProgressBackgroundColorSchemeResource(R.color.white);
        refreshlayout.setColorSchemeColors(Color.BLUE);
        refreshlayout.setSize(SwipeRefreshLayout.DEFAULT);
        refreshlayout.setOnRefreshListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (MyApplication.getInstan().getUser() != null
                && MyApplication.getInstan().getUser().getData().getId() != 0
                ) {
            getFrd();
        }
    }

    @Override
    public void onRefresh() {
        page = 1;
        num = 10;
        refreshlayout.setRefreshing(true);
        getFrd();
    }

    public void getFrd() {

        AjaxParams pa = new AjaxParams();
        pa.put("userId", MyApplication.getInstan().getUser().getData().getId() + "");
        String url = Contantor.applyListByUserId;
        DD.d("通讯录请求:" + url + "?" + pa.toString());
        new FinalHttp().post(url, pa, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                DD.d("通讯录s:" + s);
                list = JSONArray.parseArray(s, ShenQBean.class);
                if (refreshlayout.isRefreshing()) {
                    refreshlayout.setRefreshing(false);
                }
                if (list.size() > 0) {
                    eatReclerViewAdpter = new ShenQAdpter(ShenQFrendMainActivity.this, ShenQFrendMainActivity.this);
                    eatRecycler.setAdapter(eatReclerViewAdpter);
                    eatReclerViewAdpter.notifyDataSetChanged();
                    eatReclerViewAdpter.getArrayLists().addAll(list);
                } else {
                    page = page - 1;
                    num = num - 10;
                    TS.shortTime("没有数据");
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
    public void request(final int pos, String id, String state) {
        showWait();
        AjaxParams pa = new AjaxParams();
        pa.put("id", id);
        pa.put("friendStatus", state);
        String url = Contantor.applyFriend;
        DD.d("处理好友申请请求:" + url + "?" + pa.toString());
        new FinalHttp().post(url, pa, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                DD.d("处理好友申请s:" + s);
                if(list.size()==1){
                    list.clear();
                }else {
                    list.remove(pos);
                }
                eatReclerViewAdpter = new ShenQAdpter(ShenQFrendMainActivity.this, ShenQFrendMainActivity.this);
                eatRecycler.setAdapter(eatReclerViewAdpter);
                eatReclerViewAdpter.getArrayLists().clear();
                eatReclerViewAdpter.getArrayLists().addAll(list);
                eatReclerViewAdpter.notifyDataSetChanged();

                closeWait();
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                closeWait();

            }
        });
    }
}
