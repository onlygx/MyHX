package com.example.viewpagerdemo.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.viewpagerdemo.ui.MyApplication;
import com.example.viewpagerdemo.ui.adapter.DDCarReclerViewAdpter;
import com.example.viewpagerdemo.ui.bean.ShopInfoListBean;
import com.example.viewpagerdemo.ui.Contantor;
import com.example.viewpagerdemo.ui.jlfragmenwork.baseactivitywork.JLBaseActivity;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.TS;
import com.example.viewpagerdemo.ui.units.StringUtils;
import com.xingkesi.foodapp.R;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * 订单
 */
public class ShoppingDDCarActivity extends JLBaseActivity implements DDCarReclerViewAdpter.Shope_jian_add {

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


    @Bind(R.id.dd_list)
    RecyclerView ddList;
    DDCarReclerViewAdpter ddv;

    int fnum = 0;//总数量
    double nmoneys = 0;
    @Bind(R.id.text_add)
    TextView textAdd;
    @Bind(R.id.text_beiz)
    TextView textBeiz;

    String id, shopId, receiptAddressId;//商铺ID  地址ID

    // ShoppingInfoBean info;
    ArrayList<ShopInfoListBean> carLiat;

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
        id = getIntent().getStringExtra("id");
        shopId = getIntent().getStringExtra("Sid");
        carLiat = (ArrayList<ShopInfoListBean>) getIntent().getSerializableExtra("car");

        for (ShopInfoListBean ib : carLiat) {
            DD.v("订单中:" + id + "===" + ib.getCurrNum());
        }

        LinearLayoutManager man = new LinearLayoutManager(ShoppingDDCarActivity.this);
        man.setOrientation(LinearLayoutManager.VERTICAL);
        ddList.setLayoutManager(man);

        /*radiog.check(R.id.ps);
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
        });*/

    }

    @Override
    public void initObject() {
        //数据
        for (int i = 0; i < carLiat.size(); i++) {
            ShopInfoListBean dd = carLiat.get(i);
            double zhe = Double.parseDouble(dd.getZheyou() + "");
            fnum = fnum + dd.getCurrNum();//获取总数量
            double tempMoney = dd.getCurrNum() * dd.getPrice();//商品重甲
            if (zhe > 0) {
                double zz = (zhe / 100.00);
                double tem = nmoneys + tempMoney;
                nmoneys = zz * tem;
            } else {
                nmoneys = nmoneys + tempMoney;
            }
            dd.setFnum(fnum);
            dd.setFmoney(nmoneys);

        }
        ddv = new DDCarReclerViewAdpter(ShoppingDDCarActivity.this, carLiat, this);
        ddList.setAdapter(ddv);
        dd_sc.smoothScrollBy(0, 0);
        num.setText(fnum + "");

        BigDecimal b = new BigDecimal(nmoneys);
        double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        money.setText(StringUtils.toTwoDouble(f1)  + "");
    }


    @OnClick({R.id.addr, R.id.copyContent, R.id.tv_money})
    public void onClick(View view) {
        Intent it = new Intent();
        switch (view.getId()) {
            case R.id.addr://打开地址列表进行选择
                it.putExtra("check", true);//标记 如果是true启动点击选择地址
                it.setClass(ShoppingDDCarActivity.this, FindAddreesActivity.class);
                startActivityForResult(it, 102);
                break;
            case R.id.copyContent://备注
                it.setClass(ShoppingDDCarActivity.this, NoteMainActivity.class);
                startActivityForResult(it, 103);
                break;
            case R.id.tv_money://下单

                int nums = Integer.parseInt(num.getText().toString());
                double moneys = Double.valueOf(money.getText().toString());
                if (nums <= 0 && moneys <= 0) {
                    TS.shortTime("请选择商品数量");
                } else {


                    if (receiptAddressId == null) {
                        TS.shortTime("请选择收货地址");
                        return;
                    }
                    showWait();
                    AjaxParams ap = new AjaxParams();
                    ap.put("userId", MyApplication.getInstan().getUser().getData().getId() + "");
                    ap.put("shopId", shopId);
                    ap.put("intro", textBeiz.getText().toString());
                    ap.put("sendType", "1");//PEISONG + "");
                    ap.put("receiptAddressId", receiptAddressId + "");

                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < carLiat.size(); i++) {
                        ShopInfoListBean sbf = carLiat.get(i);
                        int id = sbf.getId();
                        int num = sbf.getCurrNum();
                        if (i == 0) {
                            sb.append(id + ":" + num);
                        } else {
                            sb.append(";" + id + ":" + num);
                        }

                    }
                    ap.put("goodsList", sb.toString());
                    String url = Contantor.submitAddr;

                    new FinalHttp().post(url, ap, new AjaxCallBack<String>() {
                        @Override
                        public void onSuccess(String s) {
                            super.onSuccess(s);
                            try {
                                JSONObject js = new JSONObject(s);
                                if (js.getBoolean("success")) {
                                    DD.v("下单成功");
                                    startActivity(new Intent(ShoppingDDCarActivity.this,FindDDListActivity.class));
                                    finish();
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
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {

            case 102://地址
                String add = data.getStringExtra("add");
                receiptAddressId = data.getStringExtra("addID");
                textAdd.setText(add);
                break;
            case 103://备注
                String bz = data.getStringExtra("bz");
                textBeiz.setText(bz);

                break;


        }
    }

    /**
     * @param pos     位置
     * @param moneye  单价
     * @param nume    数量
     * @param fnume   总价
     * @param fmoneye 总金额
     */
    @Override
    public void ject(int pos, double moneye, int nume, int fnume, double fmoneye) {
        DD.w("减:" + pos + "--" + moneye + "--" + nume + "--" + fnume + "--" + fmoneye);
        //设置当前
        ShopInfoListBean db = carLiat.get(pos);
        int shopNum = nume - 1;
        double shopNMoney = shopNum * moneye;
        db.setFnum(shopNum);
        db.setFmoney(shopNMoney);
        db.setCurrNum(shopNum);


        //设置总数
        ShopInfoListBean last = carLiat.get(carLiat.size() - 1);
        int xnum = fnume - 1;
        num.setText(xnum + "");
        double xmoney = fmoneye - moneye;
        money.setText(StringUtils.toTwoDouble(xmoney) + "");
        last.setFnum(xnum);
        last.setFmoney(xmoney);
        DD.w("jian后:" + xnum + "--" + xmoney);
        ddv.notifyDataSetChanged();

    }

    @Override
    public void adds(int pos, double moneye, int nume, int fnume, double fmoneye) {

        DD.w("加:" + pos + "--" + moneye + "--" + nume + "--" + fnume + "--" + fmoneye);


        //设置当前
        ShopInfoListBean db = carLiat.get(pos);
        int shopNum = nume + 1;
        double shopNMoney = shopNum * moneye;
        db.setFnum(shopNum);
        db.setFmoney(shopNMoney);
        db.setCurrNum(shopNum);


        //设置总数
        ShopInfoListBean last = carLiat.get(carLiat.size() - 1);
        int xnum = fnume + 1;
        num.setText(xnum + "");
        double xmoney = fmoneye + moneye;
        money.setText(StringUtils.toTwoDouble(xmoney) + "");
        last.setFnum(xnum);
        last.setFmoney(xmoney);
        DD.w("加后:" + xnum + "--" + xmoney);
        ddv.notifyDataSetChanged();

    }
}
