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

import org.json.JSONException;

import butterknife.Bind;
import butterknife.OnClick;

public class ABooksZActivity extends JLBaseActivity {

    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.iv_back)
    ImageView iv_back;
    @Bind(R.id.zz_userName)
    EditText zzUserName;


    @Override
    public void initObject() {
        super.initObject();
    }

    @Override
    public void initID() {
        super.initID();
        tv_title.setText("好友申请");
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setImageResource(R.drawable.cp_fh);
    }

    @Override
    public int setViewLayout() {
        return R.layout.activity_wakket_zhuang_zs;
    }


    @OnClick(R.id.zz_submit)
    public void onClick() {
        String name = zzUserName.getText().toString();
        if (TextUtils.isEmpty(name)) {
            TS.shortTime("请输入对方账号");
            return;
        }
        showWait();
        userThinksId(name);


    }

    /***
     * 使用thinksId加好友
     *
     * @param thinksId 对方的thinksId
     */
    void userThinksId(String thinksId) {
        AjaxParams ap = new AjaxParams();
        ap.put("userId", MyApplication.getInstan().getUser().getData().getId() + "");
        ap.put("thinksId", thinksId);
        String url = Contantor.ByThinksId;
        DD.d("thinksId+++好友：" + url + "?" + ap.toString());
        new FinalHttp().post(url, ap, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                DD.d("thinksId+++好友s：" + s);
                try {
                    org.json.JSONObject js =new org.json.JSONObject(s);
                    if(js.getBoolean("success")){
                        TS.shortTime("申请成功等待对方确认");
                    }else{
                        TS.shortTime("申请失败");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                closeWait();
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }
        });

    }
}
