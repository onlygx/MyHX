package com.example.viewpagerdemo.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.viewpagerdemo.ui.MyApplication;
import com.example.viewpagerdemo.ui.jlfragmenwork.Contantor;
import com.example.viewpagerdemo.ui.jlfragmenwork.baseactivitywork.JLBaseActivity;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.TS;
import com.xingkesi.foodapp.R;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.OnClick;

public class AdviceMainActivity extends JLBaseActivity {

    @Bind(R.id.note)
    EditText note;

    @Bind(R.id.iv_back)
    ImageView iv_back;
    @Bind(R.id.tv_title)
    TextView tv_title;
    @Override
    public int setViewLayout() {
        return R.layout.activity_note_main;
    }

    @Override
    public void initID() {
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setImageResource(R.drawable.cp_fh);
        tv_title.setText("建议");
    }

    @Override
    public void initObject() {
        super.initObject();
    }



    @OnClick(R.id.note_submit)
    public void onClick() {
        //提交代码
        String  str =note.getText().toString().trim();
        DD.d(Contantor.aboe );
        AjaxParams ajaxParams = new AjaxParams();
        ajaxParams.put("content", str);
        ajaxParams.put("userId", MyApplication.getInstan().getUser().getData().getId()+"");
        new FinalHttp().post(Contantor.aboe,ajaxParams,new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                try {
                    JSONObject js =new JSONObject(s);
                    if(js.getBoolean("success")){
                        TS.shortTime("提交成功");
                        finish();
                    }
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
