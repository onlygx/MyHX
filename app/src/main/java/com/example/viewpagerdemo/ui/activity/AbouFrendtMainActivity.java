package com.example.viewpagerdemo.ui.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.viewpagerdemo.ui.Contantor;
import com.example.viewpagerdemo.ui.MyApplication;
import com.example.viewpagerdemo.ui.adapter.EatInfoContentReclerAdatper;
import com.example.viewpagerdemo.ui.adapter.FindDDShopReclerViewAdpter;
import com.example.viewpagerdemo.ui.adapter.TalkViewAdpter;
import com.example.viewpagerdemo.ui.bean.ContentListBean;
import com.example.viewpagerdemo.ui.bean.FindDDListBean;
import com.example.viewpagerdemo.ui.bean.TalkingBean;
import com.example.viewpagerdemo.ui.jlfragmenwork.baseactivitywork.JLBaseActivity;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.TS;
import com.xingkesi.foodapp.R;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


/**
 * 朋友圈  与我相关
 */
public class AbouFrendtMainActivity extends JLBaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.iv_back)
    ImageView iv_back;
    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.eat_recycler)
    RecyclerView eatRecycler;
    @Bind(R.id.refreshlayout)
    SwipeRefreshLayout refreshlayout;
    LinearLayoutManager manager;
    int page = 1;
    int scrollPostion = 0;
    int num = 10;
    ArrayList<ContentListBean> contentList;
    TalkViewAdpter ddv;

    @Override
    public int setViewLayout() {
        return R.layout.activity_abou_frendt_main;
    }

    @Override
    public void initObject() {
        super.initObject();
        manager = new LinearLayoutManager(AbouFrendtMainActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        eatRecycler.setLayoutManager(manager);
        refreshlayout.setProgressBackgroundColorSchemeResource(R.color.white);
        refreshlayout.setColorSchemeColors(Color.BLUE);
        refreshlayout.setSize(SwipeRefreshLayout.DEFAULT);
        refreshlayout.setOnRefreshListener(this);


        eatRecycler.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE && scrollPostion + 1
                        == ddv.getItemCount()) {
                    page = page + 1;
                    num = num + 10;
                    getContent();
                    refreshlayout.setRefreshing(true);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                scrollPostion = ((LinearLayoutManager) eatRecycler.getLayoutManager()).findLastVisibleItemPosition();
                refreshlayout.setEnabled(manager
                        .findFirstCompletelyVisibleItemPosition() == 0);
            }
        });

        refreshlayout.setRefreshing(true);
        getContent();
    }

    @Override
    public void initID() {
        super.initID();
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setImageResource(R.drawable.cp_fh);
        tv_title.setText("与我相关");
    }
    @Override
    public void onRefresh() {
        page=1;
        num=10;
        refreshlayout.setRefreshing(true);
        getContent();
    }
    void  getContent() {

        String url = Contantor.listByUserId;
        AjaxParams map1 = new AjaxParams();
        map1.put("page", page+"");
        map1.put("userId", MyApplication.getInstan().getUser().getData().getId()+"");
        map1.put("size", num+"");

        DD.v("与我相关:"+url+"?"+map1.toString());
        new FinalHttp().post(url, map1, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                if (refreshlayout.isRefreshing()) {
                    refreshlayout.setRefreshing(false);
                }
                List<TalkingBean> find = JSON.parseArray(s, TalkingBean.class);
                if (find.size() > 0) {
                    //显示商铺
                    ddv = new TalkViewAdpter(AbouFrendtMainActivity.this);
                    ddv.getArrayList().addAll(find);
                    eatRecycler.setAdapter(ddv);
                    ddv.notifyDataSetChanged();
                } else {
                    page = page - 1;
                    num = num - 10;
                    TS.shortTime("没有数据");
                }

               DD.v("与我相关s："+s);


            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }
        });


    }
}
