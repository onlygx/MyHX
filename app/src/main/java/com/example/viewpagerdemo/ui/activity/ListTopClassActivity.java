package com.example.viewpagerdemo.ui.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.example.viewpagerdemo.ui.adapter.ListTopClassAdpter;
import com.example.viewpagerdemo.ui.bean.ListTopBean;
import com.example.viewpagerdemo.ui.jlfragmenwork.Contantor;
import com.example.viewpagerdemo.ui.jlfragmenwork.baseactivitywork.JLBaseActivity;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.xingkesi.foodapp.R;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 关于
 * Created by Administrator on 2015/12/15 0015.
 */
public class ListTopClassActivity extends JLBaseActivity {

    //顶部分类
    private List<ListTopBean> mListTopDatas = new ArrayList<>();

    @Bind(R.id.refresh_view)
    RecyclerView refresh_view;

    ListTopClassAdpter mListTopAdapter;

    @Override
    public int setViewLayout() {
        return R.layout.activity_listtop_class;
    }

    @Override
    public void initID() {

        refresh_view.setLayoutManager( new GridLayoutManager(this,3));
        mListTopAdapter = new ListTopClassAdpter(this);
        mListTopAdapter.getArrayLists().addAll(mListTopDatas);
        refresh_view.setAdapter(mListTopAdapter);
        RequsetDatas();
    }

    /**
     * 请求主页商品列表             Contantor.listTop
     */
    void RequsetDatas() {
        new FinalHttp().post(Contantor.listTop, null, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                mListTopDatas = JSON.parseArray(s, ListTopBean.class);
                DD.v("主页返回数据：" + s);
                mListTopAdapter.getArrayLists().addAll(mListTopDatas);
                mListTopAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }
        });
    }

    @OnClick({})
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }
}
