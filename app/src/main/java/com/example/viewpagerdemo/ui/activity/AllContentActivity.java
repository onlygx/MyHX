package com.example.viewpagerdemo.ui.activity;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.viewpagerdemo.ui.bean.ContentBean;
import com.example.viewpagerdemo.ui.bean.ContentListBean;
import com.example.viewpagerdemo.ui.adapter.EatInfoContentReclerAdatper;
import com.example.viewpagerdemo.ui.Contantor;
import com.example.viewpagerdemo.ui.jlfragmenwork.baseactivitywork.JLBaseActivity;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.TS;
import com.xingkesi.foodapp.R;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.util.ArrayList;

import butterknife.Bind;


/**
 * 全部评论
 */
public class AllContentActivity extends JLBaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.iv_back)
    ImageView iv_back;
    @Bind(R.id.tv_title)
    TextView tv_title;

    @Bind(R.id.eat_recycler)
    RecyclerView eatRecycler;
    @Bind(R.id.refreshlayout)
    SwipeRefreshLayout refreshlayout;


    ArrayList<ContentListBean> contentList;
    EatInfoContentReclerAdatper ec;
    LinearLayoutManager manager;
    int page=1;
    int scrollPostion = 0;
    int num=10;

    String id;

    @Override
    public int setViewLayout() {
        return R.layout.activity_all_content;
    }

    @Override
    public void initID() {
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setImageResource(R.drawable.cp_fh);
        tv_title.setText("全部评论");
        id=getIntent().getStringExtra("id");
    }

    @Override
    public void initObject() {
        ec =new EatInfoContentReclerAdatper(AllContentActivity.this);
        eatRecycler.setAdapter(ec);
       //eatRecycler.setHasFixedSize(true);
        manager = new LinearLayoutManager(AllContentActivity.this);
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
                        == ec.getItemCount()) {
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
    public void onRefresh() {
        page=1;
        num=10;
        refreshlayout.setRefreshing(true);
        getContent();
    }

    //评论列表
    void getContent() {
        AjaxParams map = new AjaxParams();
        map.put("goodsId", id+"");
        map.put("page", page+"");
        map.put("size", num+"");
        String url = Contantor.shopInfoPL;
        new FinalHttp().post(url, map, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                ContentBean bean = JSON.parseObject(s, ContentBean.class);
                contentList = bean.getList();
                if(refreshlayout.isRefreshing()){
                    refreshlayout.setRefreshing(false);
                }
                if (contentList.size() > 0) {
                    DD.d("有评论：" + contentList.size());
                    ec = new EatInfoContentReclerAdatper(AllContentActivity.this);
                    eatRecycler.setAdapter(ec);
                    ec.getArrayLists().addAll(contentList);
                    ec.notifyDataSetChanged();
                    for(int i=0;i<contentList.size();i++){
                        DD.d("评："+contentList.get(i).getId()+"=="+contentList.get(i).getUserName());
                    }
                }else{
                    page=page-1;
                    num=num-10;
                    TS.shortTime("没有数据");
                }
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                if(refreshlayout.isRefreshing()){
                    refreshlayout.setRefreshing(false);
                }
            }
        });
    }
}
