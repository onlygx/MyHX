package com.example.viewpagerdemo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.viewpagerdemo.ui.MyApplication;
import com.example.viewpagerdemo.ui.bean.AddressListBean;
import com.example.viewpagerdemo.ui.jlfragmenwork.Contantor;
import com.example.viewpagerdemo.ui.jlfragmenwork.baseactivitywork.JLBaseActivity;
import com.example.viewpagerdemo.ui.jlfragmenwork.city.widget.SelectPicPopupWindow;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.TS;
import com.xingkesi.foodapp.R;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 添加收货地址
 */
public class AddressActivity extends JLBaseActivity {
    @Bind(R.id.iv_back)
    ImageView iv_back;
    @Bind(R.id.tv_title)
    TextView tv_title;


    @Bind(R.id.tv_right_text)
    TextView tv_right_text;
    SelectPicPopupWindow menuWindow;
    @Bind(R.id.name)
    EditText name;
    @Bind(R.id.tel)
    EditText tel;

    @Bind(R.id.addinfo)
    EditText addinfo;
    @Bind(R.id.privoers)
    TextView privoers;

    @Bind(R.id.del)
    TextView del;

    String pro, city, dro;//省市县
    String Qnames, Qtels, Qadd, Qaddinfoa;//姓名 电话 省市区 详细地址

    AddressListBean listBean;

    boolean isAdd_Edit;//添加--true  编辑--false

    String id;

    @Override
    public int setViewLayout() {
        return R.layout.activity_address;
    }

    @Override
    public void initID() {
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setImageResource(R.drawable.cp_fh);
        tv_title.setText("添加收货地址");

        tv_right_text.setVisibility(View.VISIBLE);
        tv_right_text.setTextColor(getResources().getColor(R.color.logding_bg));
        tv_right_text.setText("保存");

        isAdd_Edit = getIntent().getBooleanExtra("tag", false);
        DD.w("添加地址："+isAdd_Edit);
        if (isAdd_Edit) {
            //添加地址就隐藏删除按钮
            del.setVisibility(View.INVISIBLE);
        } else {
            id = getIntent().getStringExtra("id");
            Qnames = getIntent().getStringExtra("name");
            Qtels = getIntent().getStringExtra("tel");
            pro = getIntent().getStringExtra("pro");
            city = getIntent().getStringExtra("city");
            dro = getIntent().getStringExtra("dro");

            Qaddinfoa = getIntent().getStringExtra("addres");
            Qadd = pro + " " + city + dro;
            name.setText(Qnames);
            tel.setText(Qtels);
            privoers.setText(pro + " " + city + " " + dro);
            addinfo.setText(Qaddinfoa);


        }
    }

    @Override
    public void initObject() {
        super.initObject();
    }


    @OnClick({R.id.privoers, R.id.del, R.id.tv_right_text})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.privoers:
                menuWindow = new SelectPicPopupWindow(AddressActivity.this, han);
                menuWindow.showAtLocation(AddressActivity.this.findViewById(R.id.add), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

                break;
            case R.id.del:
                //删除
                AjaxParams map1 = new AjaxParams();
                map1.put("id", id);
                new FinalHttp().post(Contantor.FindInfoADelAddr, map1, new AjaxCallBack<String>() {
                    @Override
                    public void onSuccess(String s) {
                        super.onSuccess(s);

                        try {
                            JSONObject js = new JSONObject(s);
                            DD.v("333收货地址:" + s);
                            if (js.getBoolean("success")) {
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onFailure(Throwable t, int errorNo, String strMsg) {
                        super.onFailure(t, errorNo, strMsg);
                    }
                });


                break;

            case R.id.tv_right_text:
                DD.w("dianjile添加地址："+isAdd_Edit);
                //保存 String  Qnames,Qtels,Qadd,Qaddinfoa;//姓名 电话 省市区 详细地址
                Qnames = name.getText().toString().trim();
                Qtels = tel.getText().toString().trim();
                Qadd = privoers.getText().toString().trim();
                Qaddinfoa = addinfo.getText().toString().trim();//详细地址

                if (TextUtils.isEmpty(Qnames)) {
                    TS.shortTime("请填写收货人姓名");
                    return;
                }
                if (TextUtils.isEmpty(Qtels)) {
                    TS.shortTime("请填电话");
                    return;
                }
                if (TextUtils.isEmpty(Qadd)) {
                    TS.shortTime("请填选择地址");
                    return;
                }
                if (TextUtils.isEmpty(Qaddinfoa)) {
                    TS.shortTime("请填填写详细地址");
                    return;
                }

                //pro,city,dro;
                AjaxParams map = new AjaxParams();
                String url;
                if (!isAdd_Edit) {
                    map.put("id", id);
                    url=Contantor.FindInfoAUpAddr;
                }else{
                    url=Contantor.FindInfoADDAddr;
                }
                map.put("title", "11");
                map.put("province", pro);//省份
                map.put("city", city);//城市
                map.put("district", dro);//区县
                map.put("address", Qaddinfoa);//
                map.put("phone", Qtels);//
                map.put("name", Qnames);//
                map.put("userId", MyApplication.getInstan().getUser().getData().getId() + "");

                DD.d("添加收货地址:" + url +"?"+map.toString());

                new FinalHttp().post(url, map, new AjaxCallBack<String>() {
                    @Override
                    public void onSuccess(String s) {
                        super.onSuccess(s);

                        try {
                            JSONObject js = new JSONObject(s);
                            DD.v("添加收货地址:" + s);
                            if (js.getBoolean("success")) {
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onFailure(Throwable t, int errorNo, String strMsg) {
                        super.onFailure(t, errorNo, strMsg);
                    }
                });




               /* Intent addrees = new Intent(AddressActivity.this, ShoppingDDActivity.class);
                addrees.putExtra("add", names + " " + tels + " " + add);
                setResult(102, addrees);
                finish();*/

                break;

        }

    }


    Handler han = new Handler() {

        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                Bundle data = msg.getData();
//                String pro,city,dro//省市县
                pro = data.getString("pro");
                city = data.getString("city");
                dro = data.getString("dro");
                Toast.makeText(AddressActivity.this, pro + "" + city + "" + dro, Toast.LENGTH_SHORT).show();
                privoers.setText(pro + "" + city +" " + dro);
            }

        }

        ;

    };

    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {

        public void onClick(View v) {
            menuWindow.dismiss();
        }

    };


}
