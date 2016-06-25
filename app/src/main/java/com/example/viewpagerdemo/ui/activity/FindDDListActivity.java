package com.example.viewpagerdemo.ui.activity;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.viewpagerdemo.ui.MyApplication;
import com.example.viewpagerdemo.ui.adapter.FindDDShopReclerViewAdpter;
import com.example.viewpagerdemo.ui.bean.FindDDListBean;
import com.example.viewpagerdemo.ui.bean.FindDDListShopBean;
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
 * 订单列表
 */
public class FindDDListActivity extends JLBaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.iv_back)
    ImageView iv_back;
    @Bind(R.id.tv_title)
    TextView tv_title;

    @Bind(R.id.refreshlayout)
    SwipeRefreshLayout refreshlayout;
    @Bind(R.id.dd_list)
    RecyclerView ddList;
    FindDDShopReclerViewAdpter ddv;
    //    FindDDReclerViewAdpter ddv;
//    ArrayList<FindDDListShopGoodsBean> list;//订单 商铺下商品做显示
    ArrayList<FindDDListShopBean> list;//订单商品做订单显示
    LinearLayoutManager man;
    int page = 1;
    int scrollPostion = 0;
    int num = 10;
    boolean isRuest = false;

    @Override
    public int setViewLayout() {
        return R.layout.activity_find_ddlist;
    }

    @Override
    public void initID() {
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setImageResource(R.drawable.cp_fh);
        tv_title.setText("订单");
        tv_title.setTextColor(getResources().getColor(R.color.black));
    }

    @Override
    public void initObject() {
        man = new LinearLayoutManager(FindDDListActivity.this);
        man.setOrientation(LinearLayoutManager.VERTICAL);
        ddList.setLayoutManager(man);

        //数据
        list = new ArrayList<>();
        refreshlayout.setProgressBackgroundColorSchemeResource(R.color.white);
        refreshlayout.setColorSchemeColors(Color.BLUE);
        refreshlayout.setSize(SwipeRefreshLayout.DEFAULT);
        refreshlayout.setOnRefreshListener(this);

        ddList.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE && scrollPostion + 1
                        == ddv.getItemCount()) {
                    page = page + 1;
                    num = num + 10;
                    getDDList();
                    refreshlayout.setRefreshing(true);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                scrollPostion = ((LinearLayoutManager) ddList.getLayoutManager()).findLastVisibleItemPosition();
                refreshlayout.setEnabled(man
                        .findFirstCompletelyVisibleItemPosition() == 0);
            }
        });

        ddv = new FindDDShopReclerViewAdpter(FindDDListActivity.this);
        ddList.setAdapter(ddv);


        refreshlayout.setRefreshing(true);
        getDDList();


    }

    void getDDList() {
        if (isRuest) {
            TS.shortTime("数据正在路上。。。请稍等");
            return;
        }
        isRuest = true;
        AjaxParams map = new AjaxParams();
        map.put("userId", MyApplication.getInstan().getUser().getData().getId() + "");
        map.put("page", page + "");
        map.put("size", num + "");
        String url = Contantor.FindDDList;
        DD.d("订单列表请求:" + url + "?" + map.toString());
        new FinalHttp().post(url, map, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                DD.d("订单列表:" + s);
                isRuest = false;
                if (refreshlayout.isRefreshing()) {
                    refreshlayout.setRefreshing(false);
                }
                FindDDListBean find = JSON.parseObject(s, FindDDListBean.class);
                if (find.getList().size() > 0) {
                    //显示商铺下商品
                /*for (int i = 0; i < find.getList().size(); i++) {
                    list.addAll(find.getList().get(i).getGoodses());
                }


                ddv = new FindDDReclerViewAdpter(FindDDListActivity.this);
                */
                    //显示商铺
                    list.addAll(find.getList());
                    ddv = new FindDDShopReclerViewAdpter(FindDDListActivity.this);
                    ddv.getArrayList().addAll(list);
                    ddList.setAdapter(ddv);
                    ddv.notifyDataSetChanged();
                } else {
                    page = page - 1;
                    num = num - 10;
                    TS.shortTime("没有数据");
                }
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                isRuest = false;
                if (refreshlayout.isRefreshing()) {
                    refreshlayout.setRefreshing(false);
                }
            }
        });
    }


    @Override
    public void onRefresh() {
        page = 1;
        num = 10;
        list.clear();
        refreshlayout.setRefreshing(true);
        getDDList();
    }
}
