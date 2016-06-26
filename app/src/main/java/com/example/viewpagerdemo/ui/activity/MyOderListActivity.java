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
import com.example.viewpagerdemo.ui.adapter.MyOderItemAdpter;
import com.example.viewpagerdemo.ui.bean.IndexRaleaseBean;
import com.example.viewpagerdemo.ui.bean.MyOderListBean;
import com.example.viewpagerdemo.ui.bean.MyOderOBean;
import com.example.viewpagerdemo.ui.Contantor;
import com.example.viewpagerdemo.ui.bean.SearchBean;
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
 * 我的承接列表
 */
public class MyOderListActivity extends JLBaseActivity implements SwipeRefreshLayout.OnRefreshListener, MyOderItemAdpter.delData {
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
    MyOderItemAdpter eatReclerViewAdpter;
    List<MyOderListBean> list;//

    @Override
    public void initID() {
        super.initID();
        tv_title.setText("我的任务");
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setImageResource(R.drawable.cp_fh);
        list = new ArrayList<>();
    }

    @Override
    public void initObject() {
        super.initObject();
        eatReclerViewAdpter = new MyOderItemAdpter(MyOderListActivity.this,list);
        eatRecycler.setAdapter(eatReclerViewAdpter);
        eatRecycler.setHasFixedSize(true);
        manager = new LinearLayoutManager(this);
        eatRecycler.setLayoutManager(manager);
        refreshlayout.setProgressBackgroundColorSchemeResource(R.color.white);
        refreshlayout.setColorSchemeColors(Color.BLUE);
        refreshlayout.setSize(SwipeRefreshLayout.DEFAULT);
        refreshlayout.setOnRefreshListener(this);

        eatReclerViewAdpter.setOnOrderItemClickListener(new MyOderItemAdpter.OnOrderItemClickListener() {
            @Override
            public void onCLick(int position, int type) {
                switch (type){
                    case 1:
                        pay(position,list.get(position).getId()+"");
                        break;
                    case 2:
                    case 3:
                        OKOder(position,list.get(position).getId()+"");
                        break;
                }
            }
        });

        RequsetDatas();


        eatRecycler.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE && scrollPostion + 1
                        == eatReclerViewAdpter.getItemCount()) {
                    // Toast.makeText(getActivity(), "到底了 可以加载最新数据了", Toast.LENGTH_SHORT).show();
                    page = page + 1;
                    num = num + 10;
                    // Log.d("LD", "startIndex:" + startIndex);
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
        ap.put("receiveUserId", MyApplication.getInstan().getUser().getData().getId() + "");
        ap.put("page", page + "");
        ap.put("size", num + "");
        String url = Contantor.Cjcommentreceive;
        new FinalHttp().post(url, ap, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                MyOderOBean cb = JSONObject.parseObject(s, MyOderOBean.class);

                if (refreshlayout.isRefreshing()) {
                    refreshlayout.setRefreshing(false);
                }
                //刷新操作
                if (page == 1) {
                    list.clear();
                }
                for (MyOderListBean sp : cb.getList()) {
                    list.add(sp);
                }
                eatReclerViewAdpter.notifyDataSetChanged();

                if (cb.getList().size() < 10) {
                    TS.shortTime("没有更多了~");
                } else {
                    page++;
                }

            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                refreshlayout.setRefreshing(false);
            }
        });

    }

    @Override
    public int setViewLayout() {
        return R.layout.activity_my_oder_list;
    }

    @Override
    protected void onResume() {
        super.onResume();
        RequsetDatas();
    }

    @Override
    public void onRefresh() {
        page = 1;
        num = 10;
        refreshlayout.setRefreshing(true);
        RequsetDatas();
    }

    void pay(final int position, String orderid) {
        showWait();
        AjaxParams map = new AjaxParams();
        map.put("id", orderid + "");
        map.put("thinksId", MyApplication.getInstan().getUser().getData().getThinksId());
        String url = Contantor.pay;
        DD.v("付款：" + url + "?" + map.toString());
        new FinalHttp().post(url, map, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                DD.v("付款s：" + s);
                try {
                    org.json.JSONObject js = new org.json.JSONObject(s);
                    if (js.getBoolean("success")) {
                        TS.shortTime("付款成功");
                        //更新状态
                        list.get(position).setStatus(2);
                        eatReclerViewAdpter.notifyDataSetChanged();
                    } else {
                        if (js.getInt("code") == 3) {
                            TS.shortTime("余额不足");
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                closeWait();
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                closeWait();
            }
        });
    }

    //确认收货
    void OKOder(final int position, String id){
        showWait();
        String url = Contantor.Orderfinish;
        AjaxParams ap = new AjaxParams();
        ap.put("id", id);
        DD.v("确认收货：" + url + ap.toString());
        new FinalHttp().post(url, ap, new AjaxCallBack<String>() {

            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                try {
                    org.json.JSONObject js = new org.json.JSONObject(s);
                    if (js.getBoolean("success")) {
                        list.get(position).setStatus(4);
                        eatReclerViewAdpter.notifyDataSetChanged();
                    }
                    closeWait();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                closeWait();
            }
        });


    }




    @Override
    public void del(final int postion) {
        MyOderListBean mob=list.get(postion);
        String url = Contantor.DelMyRalesepay;
        AjaxParams ap = new AjaxParams();
        ap.put("id",mob.getId()+ "");
        DD.d("删除我承接的:" + url + ap.toString());
        new FinalHttp().post(url, ap, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                DD.d("删除我承接的s:" + s);
                try {
                    org.json.JSONObject js =new org.json.JSONObject(s);
                    if(js.getBoolean("success")){
                        list.remove(postion);
                        eatReclerViewAdpter.notifyDataSetChanged();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }
        });
    }
}
