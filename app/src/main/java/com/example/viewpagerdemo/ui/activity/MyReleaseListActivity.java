package com.example.viewpagerdemo.ui.activity;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.example.viewpagerdemo.ui.MyApplication;
import com.example.viewpagerdemo.ui.adapter.MyReleaseListAdpter;
import com.example.viewpagerdemo.ui.bean.MyReleaseBean;
import com.example.viewpagerdemo.ui.jlfragmenwork.Contantor;
import com.example.viewpagerdemo.ui.jlfragmenwork.baseactivitywork.JLBaseActivity;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.TS;
import com.xingkesi.foodapp.R;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


/**
 * 我的发布列表
 */
public class MyReleaseListActivity extends JLBaseActivity implements SwipeRefreshLayout.OnRefreshListener,MyReleaseListAdpter.delData{
    @Bind(R.id.iv_back)
    ImageView iv_back;
    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.eat_recycler)
    RecyclerView eatRecycler;
    @Bind(R.id.refreshlayout)
    SwipeRefreshLayout refreshlayout;
    @Bind(R.id.ll_sc)
    LinearLayout ll_sc;

    LinearLayoutManager manager;
    int scrollPostion = 0;
    int page = 1;
    int num = 10;
    MyReleaseListAdpter eatReclerViewAdpter;
    List<MyReleaseBean.ListBean> list;

    @Override
    public int setViewLayout() {
        return R.layout.activity_my_release_list;
    }

    @Override
    public void initObject() {
        super.initObject();
        list=new ArrayList<>();
        eatRecycler.setAdapter(eatReclerViewAdpter);
        eatRecycler.setHasFixedSize(true);
        manager = new LinearLayoutManager(this);
        eatRecycler.setLayoutManager(manager);
        refreshlayout.setProgressBackgroundColorSchemeResource(R.color.white);
        refreshlayout.setColorSchemeColors(Color.BLUE);
        refreshlayout.setSize(SwipeRefreshLayout.DEFAULT);
        refreshlayout.setOnRefreshListener(this);

        RequsetDatas();


        eatRecycler.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE && scrollPostion + 1
                        == eatReclerViewAdpter.getItemCount()) {
                    page = page + 1;
                    num = num + 10;
                    RequsetDatas();
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
    }

    private void RequsetDatas() {
        AjaxParams ap = new AjaxParams();
        ap.put("sendUserId", MyApplication.getInstan().getUser().getData().getId() + "");
        ap.put("page", page + "");
        ap.put("size", num + "");
        String url = Contantor.MyRalesepay;
        DD.d("request:" + url +"?"+ ap.toString());
        new FinalHttp().post(url, ap, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                MyReleaseBean cb = JSONObject.parseObject(s, MyReleaseBean.class);
                if (cb.getList().size() > 0) {
                    list = cb.getList();
                    eatReclerViewAdpter = new MyReleaseListAdpter(MyReleaseListActivity.this,
                            MyReleaseListActivity.this);
                    eatRecycler.setAdapter(eatReclerViewAdpter);
                    eatReclerViewAdpter.notifyDataSetChanged();
                    eatReclerViewAdpter.getArrayLists().addAll(list);

                } else {
                    if (num == 0) {
                        ll_sc.setVisibility(View.VISIBLE);
                    } else {
                        page = page - 1;
                        num = num - 10;
                        TS.shortTime("没有数据");
                    }
                }
                refreshlayout.setRefreshing(false);
                DD.d("s" + s);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                refreshlayout.setRefreshing(false);
            }
        });


    }

    @Override
    public void initID() {
        tv_title.setText("我的需求");
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setImageResource(R.drawable.cp_fh);
        list = new ArrayList<>();
    }

    @Override
    public void onRefresh() {
        page = 1;
        num = 10;
        refreshlayout.setRefreshing(true);
        RequsetDatas();
    }

    @Override
    public void del(int postion) {
        long id=list.get(postion).getId();
        DelRelease(id+"",postion);
    }

    void DelRelease(String id, final int postion){
        showWait();
        AjaxParams ap = new AjaxParams();
        ap.put("id", id);
        String url = Contantor.DelMyRalesepay;
        DD.d("删除:" + url +"?"+ ap.toString());
        new FinalHttp().post(url, ap, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                DD.d("删除s" + s);
                try {
                    org.json.JSONObject js =new org.json.JSONObject(s);
                    if(js.getBoolean("success")){
                        TS.shortTime("删除成功");
                        list.remove(postion);
                        eatReclerViewAdpter.getArrayLists().clear();
                        eatReclerViewAdpter.getArrayLists().addAll(list);
                        eatReclerViewAdpter.notifyDataSetChanged();
                    }else{
                        TS.shortTime("删除失败");

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
