package com.example.viewpagerdemo.ui.activity;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.viewpagerdemo.ui.MyApplication;
import com.example.viewpagerdemo.ui.adapter.ColltentItemAdpter;
import com.example.viewpagerdemo.ui.bean.ColltentBean;
import com.example.viewpagerdemo.ui.Contantor;
import com.example.viewpagerdemo.ui.bean.SearchBean;
import com.example.viewpagerdemo.ui.bean.ShopingBean;
import com.example.viewpagerdemo.ui.bean.ShoppingListBean;
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
 * 收藏列表
 */
public class ColltentMainActivit extends JLBaseActivity implements SwipeRefreshLayout.OnRefreshListener {

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
    boolean tag=true;
    ColltentItemAdpter eatReclerViewAdpter;
    List<ColltentBean.ListBean> list;

    @Override
    public void initID() {
        super.initID();
        tv_title.setText("我的收藏");
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setImageResource(R.drawable.cp_fh);
        list = new ArrayList<>();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(MyApplication.getInstan().getUser()!=null && MyApplication.getInstan().getUser().getData().getId()!=0) {
            page=1;
            RequsetDatas();
        }
    }

    @Override
    public void initObject() {
        super.initObject();
        eatReclerViewAdpter = new ColltentItemAdpter(ColltentMainActivit.this,list);
        eatRecycler.setAdapter(eatReclerViewAdpter);
        eatRecycler.setAdapter(eatReclerViewAdpter);
        eatRecycler.setHasFixedSize(true);
        manager = new LinearLayoutManager(this);
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
                        == eatReclerViewAdpter.getItemCount() && tag) {
                    // Toast.makeText(getActivity(), "到底了 可以加载最新数据了", Toast.LENGTH_SHORT).show();
                    page = page + 1;
                    num = num + 10;
                    // Log.d("LD", "startIndex:" + startIndex);
                    tag=false;
                    RequsetDatas();
                    refreshlayout.setRefreshing(true);
                }
            }


            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                scrollPostion = ((LinearLayoutManager) eatRecycler.getLayoutManager()).findLastVisibleItemPosition();
                /*refreshlayout.setEnabled(manager
                        .findFirstCompletelyVisibleItemPosition() == 0);*/
                if(scrollPostion!=eatReclerViewAdpter.getItemCount()){
                    tag=true;
                }
            }
        });
    }

    private void RequsetDatas() {
//        userId:long // 用户id
//        page:int //第几页
//        size:int //每页大小
        AjaxParams ap = new AjaxParams();
        ap.put("userId", MyApplication.getInstan().getUser().getData().getId() + "");
        ap.put("page", page + "");
        ap.put("size", num + "");
        String url = Contantor.ByUserId;
        new FinalHttp().post(url, ap, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                ColltentBean sb = JSONObject.parseObject(s, ColltentBean.class);

                if (refreshlayout.isRefreshing()) {
                    refreshlayout.setRefreshing(false);
                }
                //刷新操作
                if(sb.getPageNum()==1){
                    list.clear();
                }
                for(ColltentBean.ListBean sp:sb.getList()){
                    list.add(sp);
                }
                eatReclerViewAdpter.notifyDataSetChanged();
                if (sb.getList().size() < 10) {
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
        return R.layout.activity_colltent_main;
    }


    @Override
    public void onRefresh() {
        page = 1;
        num = 10;
        refreshlayout.setRefreshing(true);
        RequsetDatas();
    }

}
