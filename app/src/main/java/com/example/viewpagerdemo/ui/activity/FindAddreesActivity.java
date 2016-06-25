package com.example.viewpagerdemo.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.example.viewpagerdemo.ui.MyApplication;
import com.example.viewpagerdemo.ui.adapter.AddressReclerViewAdpter;
import com.example.viewpagerdemo.ui.bean.AddressListBean;
import com.example.viewpagerdemo.ui.jlfragmenwork.Contantor;
import com.example.viewpagerdemo.ui.jlfragmenwork.baseactivitywork.JLBaseActivity;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.xingkesi.foodapp.R;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * 收货地址列表
 */

public class FindAddreesActivity extends JLBaseActivity implements AddressReclerViewAdpter.CheckClassReq{
    @Bind(R.id.iv_back)
    ImageView iv_back;
    @Bind(R.id.tv_title)
    TextView tv_title;

    @Bind(R.id.address_recycler)
    RecyclerView address_recycler;

    AddressReclerViewAdpter adpter;
    LinearLayoutManager manager;
    List<AddressListBean> list;


    boolean check;//true 点击后返回

    @Override
    public int setViewLayout() {
        return R.layout.activity_find_addrees;
    }

    @Override
    public void initID() {
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setImageResource(R.drawable.cp_fh);
        tv_title.setText("收货地址");
        check=getIntent().getBooleanExtra("check",false);
    }


    @Override
    public void initObject() {
        super.initObject();
        list = new ArrayList<>();

        adpter = new AddressReclerViewAdpter(FindAddreesActivity.this, list,check ,FindAddreesActivity.this);
        address_recycler.setAdapter(adpter);
        //eatRecycler.setHasFixedSize(true);
        manager = new LinearLayoutManager(FindAddreesActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        address_recycler.setLayoutManager(manager);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getAddrlist();
    }

    public void getAddrlist() {

        AjaxParams map = new AjaxParams();
        map.put("userId", MyApplication.getInstan().getUser().getData().getId() + "");

        new FinalHttp().post(Contantor.FindInfoAddr, map, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                DD.v("收货地址列表:" + s);
                list= JSONObject.parseArray(s,AddressListBean.class);
               // DD.v("收货地址列表:11" + list.get(0).getAddress());
                adpter = new AddressReclerViewAdpter(FindAddreesActivity.this, list,check ,FindAddreesActivity.this);
                address_recycler.setAdapter(adpter);
                adpter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }
        });


    }




    /**
     * 添加新地址
     */
    @OnClick(R.id.add_address)
    public void onClick() {
        Intent it =new Intent(FindAddreesActivity.this, AddressActivity.class);
        it.putExtra("tag",true);
        startActivity(it);
    }

    @Override
    public void CheckCall(int pos) {
        AddressListBean alb= list.get(pos);
        Intent it =new Intent(FindAddreesActivity.this, ShoppingDDCarActivity.class);
        String str =alb.getName()+" "+alb.getPhone()+" "+alb.getProvince()+" "+alb.getCity()+" "+alb.getDistrict()+" "+alb.getAddress();
        it.putExtra("addID",alb.getId());
        it.putExtra("add",str);
        setResult(102,it);
        finish();

    }
}
