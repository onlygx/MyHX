package com.example.viewpagerdemo.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.viewpagerdemo.ui.MyApplication;
import com.example.viewpagerdemo.ui.bean.BaseBean;
import com.example.viewpagerdemo.ui.Contantor;
import com.example.viewpagerdemo.ui.jlfragmenwork.baseactivitywork.JLBaseActivity;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.xingkesi.foodapp.R;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import butterknife.Bind;
import butterknife.OnClick;

public class UpdataSexActivity extends JLBaseActivity {
    int sexTag = 1;//1，代表男，0代表女
    @Bind(R.id.iv_back)
    ImageView iv_back;
    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.man)
    RadioButton man;
    @Bind(R.id.woman)
    RadioButton woman;
    @Bind(R.id.sex)
    RadioGroup sex;


    @Override
    public int setViewLayout() {
        return R.layout.activity_updata_sex;
    }

    @Override
    public void initID() {
        super.initID();
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setImageResource(R.drawable.cp_fh);
        tv_title.setText("修改性别");

        sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.man:
                        sexTag = 1;
                        break;
                    case R.id.woman:
                        sexTag = 0;
                        break;
                }

            }
        });
    }



    @OnClick(R.id.name_submit)
    public void onClick() {

        AjaxParams params = new AjaxParams();
        params.put("id", MyApplication.getInstan().getUser().getData().getId() + "");
        params.put("sex", sexTag + "");
        params.put("nick",MyApplication.getInstan().getUser().getData().getNickName()!=null?MyApplication.getInstan().getUser().getData().getNickName():"");

        params.put("wechat",MyApplication.getInstan().getUser().getData().getWechat()!=null?MyApplication.getInstan().getUser().getData().getWechat():"11");
        params.put("qq",MyApplication.getInstan().getUser().getData().getQq()!=null?MyApplication.getInstan().getUser().getData().getQq():"0");
        params.put("intro",MyApplication.getInstan().getUser().getData().getIntro()!=null?MyApplication.getInstan().getUser().getData().getIntro():"");

        DD.v("头：" + params.toString());
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
                if (js.isSuccess()) {
                    String se;
                    if(sexTag==1){
                        se="男";
                        MyApplication.getInstan().getUser().getData().setSex(se);
                    }else{
                        se="女";
                        MyApplication.getInstan().getUser().getData().setSex(se);

                    }
                    DD.d("性别:"+sexTag);
                    Intent it = new Intent(UpdataSexActivity.this, FindActivity.class);
                    it.putExtra("sex", se);
                    setResult(104, it);
                    finish();
                }
            }
        });


    }
}
