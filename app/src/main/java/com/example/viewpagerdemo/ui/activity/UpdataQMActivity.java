package com.example.viewpagerdemo.ui.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.viewpagerdemo.ui.MyApplication;
import com.example.viewpagerdemo.ui.bean.BaseBean;
import com.example.viewpagerdemo.ui.jlfragmenwork.Contantor;
import com.example.viewpagerdemo.ui.jlfragmenwork.baseactivitywork.JLBaseActivity;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.TS;
import com.xingkesi.foodapp.R;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdataQMActivity extends JLBaseActivity {
    @Bind(R.id.iv_back)
    ImageView iv_back;
    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.edit_name)
    EditText editName;
    @Bind(R.id.numz)
    TextView numz;
    int MAX_LENGTH = 100;                   //最大输入字符数500
    int Rest_Length = MAX_LENGTH;
    @Override
    public int setViewLayout() {
        return R.layout.activity_updata_qm;
    }

    @Override
    public void initID() {
        super.initID();
        ButterKnife.bind(this);
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setImageResource(R.drawable.cp_fh);
        tv_title.setText("个性签名");

        editName.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if(Rest_Length > 0){
                    Rest_Length = MAX_LENGTH - editName.getText().length();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
                numz.setText(Rest_Length+"");
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                numz.setText(Rest_Length+"");
            }
        });

    }

    @OnClick(R.id.name_submit)
    public void onClick() {


        final String name = editName.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            TS.shortTime("请输入签名");
            return;
        }
        //提交服务器代码


        AjaxParams params = new AjaxParams();
        params.put("id", MyApplication.getInstan().getUser().getData().getId() + "");
        params.put("wechat",MyApplication.getInstan().getUser().getData().getWechat()!=null?MyApplication.getInstan().getUser().getData().getWechat():"");
        params.put("nick",MyApplication.getInstan().getUser().getData().getNickName()!=null?MyApplication.getInstan().getUser().getData().getNickName():"");
        params.put("wechat",MyApplication.getInstan().getUser().getData().getWechat()!=null?MyApplication.getInstan().getUser().getData().getWechat():"11");
        params.put("qq",MyApplication.getInstan().getUser().getData().getQq()!=null?MyApplication.getInstan().getUser().getData().getQq():"0");
        params.put("sex",MyApplication.getInstan().getUser().getData().getSex()!=null?MyApplication.getInstan().getUser().getData().getSex():"1");
        params.put("intro",name);


        FinalHttp fh = new FinalHttp();
        fh.get(Contantor.FindInfo, params, new AjaxCallBack<String>() {
            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                DD.e("失败:" + strMsg);
            }

            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                DD.d("传:" + s);
                BaseBean js = JSON.parseObject(s, BaseBean.class);
                if(js.isSuccess()){
                    MyApplication.getInstan().getUser().getData().setIntro(name);
                    Intent it = new Intent(UpdataQMActivity.this, FindActivity.class);
                    it.putExtra("qm", name);
                    setResult(107, it);
                    finish();
                }
            }
        });




    }
}
