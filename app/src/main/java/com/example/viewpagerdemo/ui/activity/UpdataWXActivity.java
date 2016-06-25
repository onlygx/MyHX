package com.example.viewpagerdemo.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.viewpagerdemo.ui.MyApplication;
import com.example.viewpagerdemo.ui.bean.BaseBean;
import com.example.viewpagerdemo.ui.Contantor;
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

public class UpdataWXActivity extends JLBaseActivity {
    @Bind(R.id.iv_back)
    ImageView iv_back;
    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.edit_name)
    EditText editName;

    @Override
    public int setViewLayout() {
        return R.layout.activity_updata_wx;
    }

    @Override
    public void initID() {
        super.initID();
        ButterKnife.bind(this);
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setImageResource(R.drawable.cp_fh);
        tv_title.setText("微信");
    }

    @OnClick(R.id.name_submit)
    public void onClick() {


        final String name = editName.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            TS.shortTime("请输入微信号");
            return;
        }
        //提交服务器代码


        AjaxParams params = new AjaxParams();
        params.put("id", MyApplication.getInstan().getUser().getData().getId() + "");
        params.put("wechat",name);

        params.put("nick",MyApplication.getInstan().getUser().getData().getNickName()!=null?MyApplication.getInstan().getUser().getData().getNickName():"");
        params.put("wechat",MyApplication.getInstan().getUser().getData().getWechat()!=null?MyApplication.getInstan().getUser().getData().getWechat():"11");
        params.put("qq",MyApplication.getInstan().getUser().getData().getQq()!=null?MyApplication.getInstan().getUser().getData().getQq():"0");
        params.put("sex",MyApplication.getInstan().getUser().getData().getSex()!=null?MyApplication.getInstan().getUser().getData().getSex():"1");
        params.put("intro",MyApplication.getInstan().getUser().getData().getIntro()!=null?MyApplication.getInstan().getUser().getData().getIntro():"");


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
                    MyApplication.getInstan().getUser().getData().setWechat(name);
                    Intent it = new Intent(UpdataWXActivity.this, FindActivity.class);
                    it.putExtra("wx", name);
                    setResult(105, it);
                    finish();
                }
            }
        });




    }
}
