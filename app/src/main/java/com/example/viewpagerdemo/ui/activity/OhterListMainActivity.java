package com.example.viewpagerdemo.ui.activity;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.viewpagerdemo.ui.adapter.OthorShopListItemAdpter;
import com.example.viewpagerdemo.ui.bean.ShopingBean;
import com.example.viewpagerdemo.ui.bean.ShoppingListBean;
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
 * 其他页面列表
 */
public class OhterListMainActivity extends JLBaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.iv_back)
    ImageView iv_back;
    @Bind(R.id.tv_title)
    TextView tv_title;

    @Bind(R.id.eat_recycler)
    RecyclerView eatRecycler;
    @Bind(R.id.refreshlayout)
    SwipeRefreshLayout refreshlayout;

    int page = 1;
    int num = 10;

    //--------------列表---------------------------------
    ArrayList<ShoppingListBean> list;
    OthorShopListItemAdpter eatReclerViewAdpter;
    LinearLayoutManager manager;

    private String typeId;

    @Override
    public int setViewLayout() {
        return R.layout.activity_ohter_list_main;
    }

    @Override
    public void onRefresh() {
        page = 1;
        num = 10;
        refreshlayout.setRefreshing(true);
        RequsetDatas();
    }

    @Override
    public void initObject() {
        super.initObject();

        typeId = getIntent().getStringExtra("Type_Id");

        list = new ArrayList<>();
        eatReclerViewAdpter = new OthorShopListItemAdpter(this);
        eatRecycler.setAdapter(eatReclerViewAdpter);
        eatRecycler.setHasFixedSize(true);
        manager = new LinearLayoutManager(this);
        eatRecycler.setLayoutManager(manager);
        refreshlayout.setProgressBackgroundColorSchemeResource(R.color.white);
        refreshlayout.setColorSchemeColors(Color.BLUE);
        refreshlayout.setSize(SwipeRefreshLayout.DEFAULT);
        refreshlayout.setOnRefreshListener(this);
        RequsetDatas();
    }

    /**
     * 请求主页商品列表
     */
    void RequsetDatas() {

        //showWait();
        AjaxParams map = new AjaxParams();
        map.put("page", page + "");//页码，第几页
        map.put("size", num + "");//每页几条
        map.put("typeId",typeId+"");
        map.put("city", com.example.viewpagerdemo.ui.MyApplication.getInstan().getCity());//
        DD.d("主页其他商品列表参数：" + map.toString());
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
                    eatReclerViewAdpter = new OthorShopListItemAdpter(OhterListMainActivity.this);
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
                //closeWait();
                if (refreshlayout.isRefreshing()) {
                    refreshlayout.setRefreshing(false);
                }
            }
        });
    }

    @Override
    public void initID() {
        super.initID();
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setImageResource(R.drawable.cp_fh);
        tv_title.setText("商品");
    }
}
