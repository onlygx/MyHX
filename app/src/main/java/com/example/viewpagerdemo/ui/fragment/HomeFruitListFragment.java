package com.example.viewpagerdemo.ui.fragment;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.example.viewpagerdemo.ui.MyApplication;
import com.example.viewpagerdemo.ui.adapter.IndexyReleaseListAdpter;
import com.example.viewpagerdemo.ui.bean.IndexRaleaseBean;
import com.example.viewpagerdemo.ui.Contantor;
import com.example.viewpagerdemo.ui.jlfragmenwork.basefregmetwork.JLBaseFragment;
import com.xingkesi.foodapp.R;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/5/19.
 */
public class HomeFruitListFragment extends JLBaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.eat_recycler)
    RecyclerView eatRecycler;
    @Bind(R.id.refreshlayout)
    SwipeRefreshLayout refreshlayout;
    LinearLayoutManager manager;
    int scrollPostion = 0;
    int page = 1;
    int num = 10;
    boolean tag = true;

    IndexyReleaseListAdpter eatReclerViewAdpter;
    List<IndexRaleaseBean.ListBean> list;

    @Override
    public int setViewLayout() {
        return R.layout.homfrutelistfragmet;
    }


    @Override
    public void InitObject() {
        super.InitObject();
        eatReclerViewAdpter = new IndexyReleaseListAdpter(getActivity());
        eatRecycler.setAdapter(eatReclerViewAdpter);
        eatRecycler.setHasFixedSize(true);
        manager = new LinearLayoutManager(getActivity());
        eatRecycler.setLayoutManager(manager);
        refreshlayout.setProgressBackgroundColorSchemeResource(R.color.white);
        refreshlayout.setColorSchemeColors(Color.BLUE);
        refreshlayout.setSize(SwipeRefreshLayout.DEFAULT);
        refreshlayout.setOnRefreshListener(this);
        getlistByRegion();
        eatRecycler.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE && scrollPostion + 1
                        == eatReclerViewAdpter.getItemCount() && tag) {
                    page = page + 1;
                    num = num + 10;
                    tag = false;
                    getlistByRegion();
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


    void getlistByRegion() {
        String url = Contantor.listByRegion;
        AjaxParams ap = new AjaxParams();
        ap.put("province", MyApplication.getInstan().getSheng());
        ap.put("city", MyApplication.getInstan().getCity());
        ap.put("district", MyApplication.getInstan().getQu());
        ap.put("page", page + "");
        ap.put("size", num + "");

       // DD.v("首页需求：" + url + "?" + ap.toString());
        new FinalHttp().post(url, ap, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                IndexRaleaseBean cb = JSONObject.parseObject(s, IndexRaleaseBean.class);
                if (cb.getList().size() > 0) {
                    list = cb.getList();
                    eatReclerViewAdpter = new IndexyReleaseListAdpter(getActivity());
                    eatRecycler.setAdapter(eatReclerViewAdpter);
                    eatReclerViewAdpter.notifyDataSetChanged();
                    eatReclerViewAdpter.getArrayLists().addAll(list);

                } else {
                    /*if (num == 0) {
                        ll_sc.setVisibility(View.VISIBLE);
                    } else {*/
                    page = page - 1;
                    num = num - 10;
                  //  TS.shortTime("没有数据");
                    // }
                }
                refreshlayout.setRefreshing(false);
              //  DD.d("首页需求s：" + s);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                refreshlayout.setRefreshing(false);
            }
        });


    }

    @Override
    public void onRefresh() {
        page = 1;
        num = 10;
        refreshlayout.setRefreshing(true);
        getlistByRegion();
    }
}
