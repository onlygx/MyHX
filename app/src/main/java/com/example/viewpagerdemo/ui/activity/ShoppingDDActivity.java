package com.example.viewpagerdemo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.viewpagerdemo.ui.adapter.DDReclerViewAdpter;
import com.example.viewpagerdemo.ui.bean.DDListBean;
import com.example.viewpagerdemo.ui.bean.ShoppingInfoBean;
import com.example.viewpagerdemo.ui.jlfragmenwork.baseactivitywork.JLBaseActivity;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.xingkesi.foodapp.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 订单
 */
public class ShoppingDDActivity extends JLBaseActivity implements DDReclerViewAdpter.Shope_jian_add {

    @Bind(R.id.iv_back)
    ImageView iv_back;
    @Bind(R.id.tv_title)
    TextView tv_title;


    @Bind(R.id.addr)
    LinearLayout addr;
    @Bind(R.id.money)
    TextView money;//总价
    @Bind(R.id.num)
    TextView num;//总数
    @Bind(R.id.dd_sc)
    ScrollView dd_sc;
    @Bind(R.id.radiog)
    RadioGroup radiog;

    @Bind(R.id.dd_list)
    RecyclerView ddList;
    DDReclerViewAdpter ddv;
    ArrayList<DDListBean> list;
    int fnum, fmoney, nmoneys;
    int PEISONG = 0;
    @Bind(R.id.text_add)
    TextView textAdd;
    @Bind(R.id.text_beiz)
    TextView textBeiz;

    String id;

    ShoppingInfoBean info;

    @Override
    public int setViewLayout() {
        return R.layout.activity_shopping_dd;
    }


    @Override
    public void initID() {
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setImageResource(R.drawable.cp_fh);
        tv_title.setText("订单");
        tv_title.setTextColor(getResources().getColor(R.color.black));
        id=getIntent().getStringExtra("id");
        info= (ShoppingInfoBean) getIntent().getSerializableExtra("info");

        LinearLayoutManager man = new LinearLayoutManager(ShoppingDDActivity.this);
        man.setOrientation(LinearLayoutManager.VERTICAL);
        ddList.setLayoutManager(man);

        radiog.check(R.id.ps);
        radiog.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.ps:
                        PEISONG = 0;
                        break;
                    case R.id.kd:
                        PEISONG = 1;
                        break;
                    case R.id.zt:
                        PEISONG = 2;
                        break;
                }

            }
        });

    }

    @Override
    public void initObject() {
        //测试数据
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DDListBean dd = new DDListBean();
            dd.setId(i);
            dd.setShop_id(i);
            int nums = 2 + i;
            dd.setShop_num(nums);//数量
            int money = 9 + i;
            dd.setShop_money(money);
            dd.setIsshiYh(true);
            dd.setShop_name("洪七公 烤鸡店");
            int sd = i + 1;
            dd.setShop_title("洪七公香酥烤鸭 " + sd);
            dd.setShop_zmoney(money * nums);
            dd.setShop_ymoney(11);
            dd.setShop_znum(nums);
            fnum = fnum + nums;
            nmoneys = nums * money;
            fmoney = fmoney + nmoneys;
            dd.setFnum(fnum);
            dd.setFmoney(fmoney);

            list.add(dd);
        }
        ddv = new DDReclerViewAdpter(ShoppingDDActivity.this, list, this);
        ddList.setAdapter(ddv);
        dd_sc.smoothScrollBy(0, 0);


        num.setText(fnum + "");
        money.setText(fmoney + "");

    }


    @OnClick({R.id.addr, R.id.copyContent, R.id.tv_money})
    public void onClick(View view) {
        Intent it = new Intent();
        switch (view.getId()) {
            case R.id.addr://打开地址列表进行选择
                it.putExtra("check",true);//标记 如果是true启动点击选择地址
                it.setClass(ShoppingDDActivity.this, FindAddreesActivity.class);
                startActivityForResult(it,102);
                break;
            case R.id.copyContent://备注
                it.setClass(ShoppingDDActivity.this, NoteMainActivity.class);
                startActivityForResult(it,103);
                break;
            case R.id.tv_money://下单





                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {

            case 102://地址
                String add =data.getStringExtra("add");
                textAdd.setText(add);
                break;
            case 103://备注
                String bz =data.getStringExtra("bz");
                textBeiz.setText(bz);

                break;


        }
    }

    @Override
    public void ject(int pos, int moneye, int nume, int fnume, int fmoneye) {
        DD.w("减:" + pos + "--" + moneye + "--" + nume + "--" + fnume + "--" + fmoneye);
        //设置当前
        DDListBean db = list.get(pos);
        int shopNum = nume - 1;
        int shopNMoney = shopNum * moneye;
        db.setShop_num(shopNum);
        db.setShop_znum(shopNum);
        db.setShop_zmoney(shopNMoney);


        //设置总数
        DDListBean last = list.get(list.size() - 1);
        int xnum = fnume - 1;
        num.setText(xnum + "");
        int xmoney = fmoneye - moneye;
        money.setText(xmoney + "");
        last.setFnum(xnum);
        last.setFmoney(xmoney);
        DD.w("jian后:" + xnum + "--" + xmoney);
        ddv.notifyDataSetChanged();

    }

    @Override
    public void adds(int pos, int moneye, int nume, int fnume, int fmoneye) {

        DD.w("加:" + pos + "--" + moneye + "--" + nume + "--" + fnume + "--" + fmoneye);


        //设置当前
        DDListBean db = list.get(pos);
        int shopNum = nume + 1;
        int shopNMoney = shopNum * moneye;
        db.setShop_num(shopNum);
        db.setShop_znum(shopNum);
        db.setShop_zmoney(shopNMoney);


        //设置总数
        DDListBean last = list.get(list.size() - 1);
        int xnum = fnume + 1;
        num.setText(xnum + "");
        int xmoney = fmoneye + moneye;
        money.setText(xmoney + "");
        last.setFnum(xnum);
        last.setFmoney(xmoney);
        DD.w("加后:" + xnum + "--" + xmoney);
        ddv.notifyDataSetChanged();

    }
}
