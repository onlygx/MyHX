package com.example.viewpagerdemo.ui.activity;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.viewpagerdemo.ui.Contantor;
import com.example.viewpagerdemo.ui.MyApplication;
import com.example.viewpagerdemo.ui.adapter.ColltentItemAdpter;
import com.example.viewpagerdemo.ui.adapter.IndexyReleaseListAdpter;
import com.example.viewpagerdemo.ui.adapter.OthorShopListItemAdpter;
import com.example.viewpagerdemo.ui.adapter.SearchItemAdpter;
import com.example.viewpagerdemo.ui.bean.ColltentBean;
import com.example.viewpagerdemo.ui.bean.IndexRaleaseBean;
import com.example.viewpagerdemo.ui.bean.SearchBean;
import com.example.viewpagerdemo.ui.bean.ShopingBean;
import com.example.viewpagerdemo.ui.bean.ShoppingListBean;
import com.example.viewpagerdemo.ui.jlfragmenwork.baseactivitywork.JLBaseActivity;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.TS;
import com.example.viewpagerdemo.ui.units.StringUtils;
import com.xingkesi.foodapp.R;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * 收藏列表
 */
public class SearchActivity extends JLBaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    //
    @Bind(R.id.iv_back)
    ImageView iv_back;
    @Bind(R.id.btn_commodity)
    TextView btn_commodity;
    @Bind(R.id.btn_need)
    TextView btn_need;

    @Bind(R.id.recycler_view)
    RecyclerView recycler_view;
    @Bind(R.id.refreshlayout)
    SwipeRefreshLayout refreshlayout;

    @Bind(R.id.et_search)
    EditText et_search;
    int scrollPostion = 0;
    int page = 1;
    int num = 10;
    boolean tag=true;
     SearchItemAdpter mSearchAdapter;

    ArrayList<SearchBean> mDatas = new ArrayList<>();

    private int showType =0 ;//0 商品 1 需求

    @Override
    public void initID() {
        super.initID();
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setImageResource(R.drawable.cp_fh);
    }

    @Override
    public int setViewLayout() {
        return R.layout.activity_search;
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(MyApplication.getInstan().getUser()!=null && MyApplication.getInstan().getUser().getData().getId()!=0) {
        }
    }

    @Override
    public void initObject() {
        super.initObject();

        mSearchAdapter = new SearchItemAdpter(this,mDatas);

        recycler_view.setAdapter(mSearchAdapter);
        recycler_view.setHasFixedSize(true);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        refreshlayout.setOnRefreshListener(this);

        recycler_view.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE && scrollPostion + 1
                        == mSearchAdapter.getItemCount() && tag) {
                    page = page + 1;
                    num = num + 10;
                    tag=false;
                    refreshlayout.setRefreshing(true);
                }
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                scrollPostion = ((LinearLayoutManager) recycler_view.getLayoutManager()).findLastVisibleItemPosition();
                /*refreshlayout.setEnabled(manager
                        .findFirstCompletelyVisibleItemPosition() == 0);*/
                if(scrollPostion!=mSearchAdapter.getItemCount()){
                    tag=true;
                }
            }
        });
    }
    /**
     * 请求主页商品列表
     */
    void SearchCommodity(String searchName) {

        //showWait();
        AjaxParams map = new AjaxParams();
        map.put("page", page + "");//页码，第几页
        map.put("size", num + "");//每页几条
        map.put("name",searchName+"");
        map.put("city", com.example.viewpagerdemo.ui.MyApplication.getInstan().getCity());//
        new FinalHttp().post(Contantor.main_eat, map, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                if (refreshlayout.isRefreshing()) {
                    refreshlayout.setRefreshing(false);
                }
                ShopingBean sb = JSON.parseObject(s, ShopingBean.class);
                //刷新操作
                if(sb.getPage().equals("1")){
                    mDatas.clear();
                }
                for(ShoppingListBean sp:sb.getList()){
                    SearchBean bean = new SearchBean();
                    bean.setType(1);//商品数据
                    bean.setCommodityBean(sp);
                    mDatas.add(bean);
                }
                mSearchAdapter.setType(showType);
                mSearchAdapter.notifyDataSetChanged();

                if (sb.getList().size() < 10) {
                    TS.shortTime("没有更多了~");
                } else {
                    page++;
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

    void searchNeed(String searchName) {
        String url = Contantor.listByRegion;
        AjaxParams ap = new AjaxParams();
        ap.put("province", MyApplication.getInstan().getSheng());
        ap.put("city", MyApplication.getInstan().getCity());
        ap.put("district", MyApplication.getInstan().getQu());
        ap.put("title",searchName);
        ap.put("page", page + "");
        ap.put("size", num + "");

        new FinalHttp().post(url, ap, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                IndexRaleaseBean cb = JSONObject.parseObject(s, IndexRaleaseBean.class);
                if (refreshlayout.isRefreshing()) {
                    refreshlayout.setRefreshing(false);
                }
                //刷新操作
                if (cb.getPageNum() == 1) {
                    mDatas.clear();
                }
                for (IndexRaleaseBean.ListBean sp : cb.getList()) {
                    SearchBean bean = new SearchBean();
                    bean.setType(2);//商品数据
                    bean.setNeedBean(sp);
                    mDatas.add(bean);
                }
                mSearchAdapter.setType(showType);
                mSearchAdapter.notifyDataSetChanged();

                if (cb.getList().size() < 10) {
                    TS.shortTime("没有更多了~");
                } else {
                    page++;
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

    @OnClick({R.id.iv_back,R.id.btn_search,R.id.btn_commodity,R.id.btn_need})
    void onClick(View v){
        switch (v.getId()){
            case R.id.iv_back://返回
                finish();
                break;
            case R.id.btn_search://
                if(StringUtils.isEmpty(et_search.getText().toString()) ) return;
                page = 1;
                if(showType==0){
                    SearchCommodity(et_search.getText().toString());
                }else{
                    searchNeed(et_search.getText().toString());
                }
                break;
            case R.id.btn_commodity://商品
                showType = 0;
                btn_commodity.setBackgroundColor(ContextCompat.getColor(this,R.color.green));
                btn_commodity.setTextColor(ContextCompat.getColor(this,R.color.waiter));
                btn_need.setBackgroundColor(ContextCompat.getColor(this,R.color.waiter));
                btn_need.setTextColor(ContextCompat.getColor(this,R.color.green));
                if(StringUtils.isEmpty(et_search.getText().toString()) ) return;
                page = 1;
                SearchCommodity(et_search.getText().toString());
                break;
            case R.id.btn_need://需求
                btn_commodity.setBackgroundColor(ContextCompat.getColor(this,R.color.waiter));
                btn_commodity.setTextColor(ContextCompat.getColor(this,R.color.green));
                btn_need.setBackgroundColor(ContextCompat.getColor(this,R.color.green));
                btn_need.setTextColor(ContextCompat.getColor(this,R.color.waiter));
                showType = 1;
                if(StringUtils.isEmpty(et_search.getText().toString()) ) return;
                page = 1;
                searchNeed(et_search.getText().toString());
                break;
        }
    }

    @Override
    public void onRefresh() {
        page = 1;
        num = 10;
        refreshlayout.setRefreshing(true);
    }
}
