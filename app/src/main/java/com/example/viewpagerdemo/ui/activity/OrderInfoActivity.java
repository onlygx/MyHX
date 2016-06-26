package com.example.viewpagerdemo.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.viewpagerdemo.ui.adapter.OderInfoListAdpter;
import com.example.viewpagerdemo.ui.bean.OderInfoBean;
import com.example.viewpagerdemo.ui.Contantor;
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

    String moneys, name;
    @Bind(R.id.order_cancel)
    TextView orderCancel;
    @Bind(R.id.order_fk)
    TextView orderFk;
    @Bind(R.id.ddstate)
    TextView ddstate;

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
        //订单状态从0-5分别代表“已取消”、“待支付”、“已支付”、“已发货”、“待评价”、“已完成”
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setImageResource(R.drawable.cp_fh);
        tv_title.setText("订单详情");
        idNum = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        Status = getIntent().getIntExtra("st", -1);
        LinearLayoutManager man = new LinearLayoutManager(OrderInfoActivity.this);
        man.setOrientation(LinearLayoutManager.VERTICAL);
        orderListview.setLayoutManager(man);


        if (Status == 4) {//已完成显示评价
            orderCancel.setVisibility(View.GONE);
            orderFk.setText("待评价");
            ddstate.setText("已完成");
        } else if (Status == 3 || Status == 2) {
            ddstate.setText("已发货");
            orderFk.setText("确认收货");
        } else if (Status == 5) {
            orderCancel.setVisibility(View.GONE);
            orderFk.setEnabled(false);
            orderFk.setText("已完成");
            ddstate.setText("已完成");
        } else if (Status == 1) {
            ddstate.setText("待支付");
            orderFk.setText("付款");
        }

        if (idNum != null && !idNum.equals("")) {
            getOrderInfo(idNum);
        }

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(101,new Intent(OrderInfoActivity.this,FindDDListActivity.class));
                finish();
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            setResult(101,new Intent(OrderInfoActivity.this,FindDDListActivity.class));
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (resultCode) {
                case 101:
                    String id = data.getStringExtra("id");
                    getOrderInfo(id);
                    break;
                case 102:
                    setResult(101,new Intent(OrderInfoActivity.this,FindDDListActivity.class));
                    finish();
                    break;
            }
        }
    }

    void getOrderInfo(String idNum) {
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
                    orderDdnum.setText(OrderInfoActivity.this.idNum);//订单号

                    int Statuse = oder.getStatus();
                    Status=Statuse;

//订单状态从0-5分别代表“已取消”、“待支付”、“已支付”、“已发货”、“待评价”、“已完成”
                    if (Statuse == 4) {//已完成显示评价
                        orderCancel.setVisibility(View.GONE);
                        orderFk.setText("待评价");
                        ddstate.setText("已完成");
                    } else if (Statuse == 3 || Statuse == 2) {
                        orderCancel.setVisibility(View.GONE);
                        ddstate.setText("已发货");
                        orderFk.setText("确认收货");
                    } else if (Statuse == 5) {
                        orderCancel.setVisibility(View.GONE);
                        orderFk.setEnabled(false);
                        orderFk.setText("已完成");
                        ddstate.setText("已完成");
                    } else if (Statuse == 1) {
                        ddstate.setText("待支付");
                        orderFk.setText("付款");
                    }

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
                if (Status == 3 || Status==2) {//确认收货
                    OKOder();
                } else if (Status == 1) {//去付款
                    Intent it = new Intent(OrderInfoActivity.this, PlayMainActivity.class);
                    it.putExtra("id", idNum + "");
                    it.putExtra("mo", moneys + "");
//                    startActivity(it);
                    startActivityForResult(it, 101);
                } else if (Status == 4) {//去评价
                    Intent it = new Intent(OrderInfoActivity.this, PingJiaMainActivity.class);
                    it.putExtra("id", list.get(0).getId()+ "");
                    it.putExtra("name", name);
                    startActivityForResult(it, 102);
//                    startActivity(it);
//                    finish();
                }
                break;
        }
    }

    //确认收货
    void OKOder() {
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
                        Status = 4;
                        orderFk.setText("去评价");
                        ddstate.setText("已完成");
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
