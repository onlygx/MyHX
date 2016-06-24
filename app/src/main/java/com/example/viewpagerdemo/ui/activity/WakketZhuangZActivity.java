package com.example.viewpagerdemo.ui.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.example.viewpagerdemo.ui.MyApplication;
import com.example.viewpagerdemo.ui.bean.WalletZZBean;
import com.example.viewpagerdemo.ui.jlfragmenwork.Contantor;
import com.example.viewpagerdemo.ui.jlfragmenwork.baseactivitywork.JLBaseActivity;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.TS;
import com.xingkesi.foodapp.R;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import butterknife.Bind;
import butterknife.OnClick;

public class WakketZhuangZActivity extends JLBaseActivity {

    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.iv_back)
    ImageView iv_back;
    @Bind(R.id.zz_userName)
    EditText zzUserName;
    @Bind(R.id.zz_money)
    EditText zzMoney;

    String tag,mo;
    @Override
    public void initObject() {
        super.initObject();
    }

    @Override
    public void initID() {
        super.initID();
        tv_title.setText("付款");
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setImageResource(R.drawable.cp_fh);
        mo=getIntent().getStringExtra("mo");
        tag=getIntent().getStringExtra("tag");
        if(tag.equals("no")){
            zzUserName.setVisibility(View.GONE);
        }
    }

    @Override
    public int setViewLayout() {
        return R.layout.activity_wakket_zhuang_z;
    }


    @OnClick(R.id.zz_submit)
    public void onClick() {
        String money = zzMoney.getText().toString();
        String name;
        if(tag.equals("yes")) {
             name = zzUserName.getText().toString();
            if (TextUtils.isEmpty(name)) {
                TS.shortTime("请输入对方账号");
                return;
            }
        }else{
            name=mo;
        }

        if (TextUtils.isEmpty(money)) {
            TS.shortTime("请输入金额");
            return;
        }
        double ms = Double.valueOf(money);
        if (ms <= 0) {
            TS.shortTime("金额必须大于0");
            return;
        }

        showWait();
        AjaxParams ap = new AjaxParams();
        ap.put("thinksId", MyApplication.getInstan().getUser().getData().getThinksId());
        ap.put("toUserThinksId", name);
        ap.put("num", money);
        String url = Contantor.zhuanzhang;
        DD.d("转账：" + url + "?" + ap.toString());
        new FinalHttp().post(url, ap, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                DD.d("转账s：" + s);
                WalletZZBean zzBean = JSONObject.parseObject(s,WalletZZBean.class);
                if(zzBean.getState().equals("true")){
                    TS.shortTime("转账成功");
                    finish();
                    closeWait();
                }else{
                    closeWait();
                    TS.shortTime(zzBean.getMsg());
                }
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }
        });


    }
}
