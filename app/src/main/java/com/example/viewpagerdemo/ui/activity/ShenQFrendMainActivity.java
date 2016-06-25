package com.example.viewpagerdemo.ui.activity;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.viewpagerdemo.ui.adapter.ShenQAdpter;
import com.example.viewpagerdemo.ui.bean.ShenQBean;
import com.example.viewpagerdemo.ui.Contantor;
import com.example.viewpagerdemo.ui.jlfragmenwork.baseactivitywork.JLBaseActivity;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.xingkesi.foodapp.R;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

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

        eatRecycler.setHasFixedSize(true);
        manager = new LinearLayoutManager(this);
        eatRecycler.setLayoutManager(manager);
        refreshlayout.setProgressBackgroundColorSchemeResource(R.color.white);
        refreshlayout.setColorSchemeColors(Color.BLUE);
        refreshlayout.setSize(SwipeRefreshLayout.DEFAULT);
        refreshlayout.setOnRefreshListener(this);


        list = (List<ShenQBean>) getIntent().getSerializableExtra("list");
        if (list.size() > 0) {
            eatReclerViewAdpter = new ShenQAdpter(this, this);
            eatReclerViewAdpter.getArrayLists().addAll(list);
            eatRecycler.setAdapter(eatReclerViewAdpter);
            eatReclerViewAdpter.notifyDataSetChanged();
        }
    }

    @Override
    public void initID() {
        super.initID();
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setImageResource(R.drawable.cp_fh);
        tv_title.setText("好友申请列表");


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onRefresh() {
        // page = 1;
        // num = 10;
        // refreshlayout.setRefreshing(true);
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
                if (list.size() == 1) {
                    list.clear();
                } else {
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
