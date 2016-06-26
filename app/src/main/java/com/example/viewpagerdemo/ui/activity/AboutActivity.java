package com.example.viewpagerdemo.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.viewpagerdemo.ui.jlfragmenwork.baseactivitywork.JLBaseActivity;
import com.xingkesi.foodapp.R;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 关于
 * Created by Administrator on 2015/12/15 0015.
 */
public class AboutActivity extends JLBaseActivity {

    @Bind(R.id.iv_back)
    ImageView iv_back;
    @Bind(R.id.tv_title)
    TextView tv_title;

    @Bind(R.id.layout_about_xieyi)
    LinearLayout layout_about_xieyi;//用户协议

    @Override
    public int setViewLayout() {
        return R.layout.activity_about;
    }

    @Override
    public void initID() {
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setImageResource(R.drawable.cp_fh);
        tv_title.setText("关于");
    }

    @OnClick({ R.id.layout_about_xieyi})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_about_xieyi:
                //用户协议
                //Intent intent=new Intent(AboutActivity.this,UserYongHuXieYi.class);
               // startActivity(intent);
                break;
        }
    }
}
