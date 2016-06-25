package com.example.viewpagerdemo.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.viewpagerdemo.ui.adapter.OderInfoListAdpter;
import com.example.viewpagerdemo.ui.bean.OderInfoBean;
import com.example.viewpagerdemo.ui.jlfragmenwork.Contantor;
import com.example.viewpagerdemo.ui.jlfragmenwork.baseactivitywork.JLBaseActivity;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.xingkesi.foodapp.R;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * 订单详情
 */
public class OrderInfoActivity extends JLBaseActivity {

    @Bind(R.id.iv_back)
    ImageView iv_back;
    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.order_listview)
    RecyclerView orderListview;
    @Bind(R.id.order_peisongFei)
    TextView orderPeisongFei;
    @Bind(R.id.order_money)
    TextView orderMoney;
    @Bind(R.id.order_address)
    TextView orderAddress;
    @Bind(R.id.order_name)
    TextView orderName;
    @Bind(R.id.order_note)
    TextView orderNote;
    @Bind(R.id.order_dddate)
    TextView orderDddate;
    @Bind(R.id.order_ddnum)
    TextView orderDdnum;
    @Bind(R.id.order_shopname)
    TextView order_shopname;

    String idNum;//订单号

    List<OderInfoBean.GoodsesBean> list;
    OderInfoListAdpter oa;

    String moneys,name;
    @Bind(R.id.order_cancel)
    TextView orderCancel;
    @Bind(R.id.order_fk)
    TextView orderFk;

    int Status;

    @Override
    public int setViewLayout() {
        return R.layout.activity_order_info;
    }

    @Override
    public void initObject() {
        super.initObject();
    }

    @Override
    public void initID() {
        super.initID();
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setImageResource(R.drawable.cp_fh);
        tv_title.setText("添加收货地址");
        idNum = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        Status = getIntent().getIntExtra("st",-1);
        LinearLayoutManager man = new LinearLayoutManager(OrderInfoActivity.this);
        man.setOrientation(LinearLayoutManager.VERTICAL);
        orderListview.setLayoutManager(man);

        //状态5 不在此显示
        if (Status == 4) {//已完成显示评价
            orderCancel.setVisibility(View.GONE);
            orderFk.setText("已完成");
            orderFk.setEnabled(false);
        }else if(Status == 3){
           // orderCancel.setVisibility(View.GONE);
            orderFk.setText("确认收货");
        }else if(Status == 2){
            orderFk.setText("已付款");
            orderFk.setEnabled(false);
        }

        if (idNum != null && !idNum.equals("")) {
            getOrderInfo();
        }
    }


    void getOrderInfo() {
        String url = Contantor.OrderInfo;
        AjaxParams ap = new AjaxParams();
        ap.put("id", idNum);
        DD.v("订单详情：" + url + ap.toString());
        new FinalHttp().post(url, ap, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                DD.v("订单详情s：" + s);
                OderInfoBean oder = com.alibaba.fastjson.JSONObject.parseObject(s, OderInfoBean.class);
                if (oder != null && oder.getGoodses().size() > 0) {
                    list = oder.getGoodses();
                    oa = new OderInfoListAdpter(OrderInfoActivity.this, list);
                    orderListview.setAdapter(oa);

                    order_shopname.setText(oder.getShopName());//商店名
                    moneys = oder.getPrice() + "";
                    orderMoney.setText(moneys);//总价
                    orderNote.setText(oder.getIntro().equals("") ? "无" : oder.getIntro());//留言

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:DD");
                    String date = sdf.format(new Date(oder.getSetTime()));
                    orderDddate.setText(date);//时间
                    orderName.setText(oder.getAddress().getName() + "    " + oder.getAddress().getPhone());//收货人
                    orderAddress.setText(oder.getAddress().getProvince() + oder.getAddress().getCity() +
                            oder.getAddress().getDistrict() + oder.getAddress().getAddress());//地址
                    orderDdnum.setText(idNum);//订单号




                }
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }
        });


    }

    void CacleOder() {
        showWait();
        String url = Contantor.OrderInfocancel;
        AjaxParams ap = new AjaxParams();
        ap.put("id", idNum);
        DD.v("订单详情：" + url + ap.toString());
        new FinalHttp().post(url, ap, new AjaxCallBack<String>() {

            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                try {
                    JSONObject js = new JSONObject(s);
                    if (js.getBoolean("success")) {

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


    @OnClick({R.id.order_cancel, R.id.order_fk})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.order_cancel:
                CacleOder();
                break;
            case R.id.order_fk:
                if (moneys == null || moneys.equals("")) {
                    return;
                }
                if(Status==3){//确认收货
                    OKOder();
                }else if(Status==1) {
                    Intent it = new Intent(OrderInfoActivity.this, PlayMainActivity.class);
                    it.putExtra("id", idNum + "");
                    it.putExtra("mo", moneys + "");
                    startActivity(it);
                }else if(Status==4){//去评价
                    Intent it = new Intent(OrderInfoActivity.this, PingJiaMainActivity.class);
                    it.putExtra("id", idNum + "");
                    it.putExtra("name", name);
                    startActivity(it);
                    finish();
                }
                break;
        }
    }

    //确认收货
    void OKOder(){
        showWait();
        String url = Contantor.Orderfinish;
        AjaxParams ap = new AjaxParams();
        ap.put("id", idNum);
        DD.v("确认收货：" + url + ap.toString());
        new FinalHttp().post(url, ap, new AjaxCallBack<String>() {

            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                try {
                    JSONObject js = new JSONObject(s);
                    if (js.getBoolean("success")) {
                        orderCancel.setVisibility(View.GONE);
                        Status=4;
                        orderFk.setText("去评价");
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


}
