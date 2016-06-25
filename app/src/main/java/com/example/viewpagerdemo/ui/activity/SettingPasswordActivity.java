package com.example.viewpagerdemo.ui.activity;

import android.os.CountDownTimer;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.alibaba.fastjson.JSON;
import com.example.viewpagerdemo.ui.MyApplication;
import com.example.viewpagerdemo.ui.bean.CodeBean;
import com.example.viewpagerdemo.ui.bean.UserBeanLO;
import com.example.viewpagerdemo.ui.Contantor;
import com.example.viewpagerdemo.ui.jlfragmenwork.baseactivitywork.JLBaseActivity;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.xingkesi.foodapp.R;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 修改密码
 */
public class SettingPasswordActivity extends JLBaseActivity {

    @Bind(R.id.et_old)
    EditText etOld;

    @Bind(R.id.iv_back)
    ImageView iv_back;
    @Bind(R.id.tv_title)
    TextView tv_title;

    boolean bl_old, bl_new;
    @Bind(R.id.iv_old)
    ImageView ivOld;


    @Bind(R.id.tv_registerCode)
    TextView tv_registerCode;
    @Bind(R.id.et_registerCode)
    EditText et_registerCode;// 验证码
    String df;


    MyCount mc;
    UserBeanLO user;

    @Override
    public int setViewLayout() {
        return R.layout.activity_setting_updataps;
    }

    @Override
    public void initID() {
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setImageResource(R.drawable.cp_fh);
        tv_title.setText("修改密码");
        mc = new MyCount(60000, 1000);

        if (MyApplication.getInstan().getUser() != null) {
            user = MyApplication.getInstan().getUser().getData();
        }
    }


    @OnClick({R.id.iv_old, R.id.tv_sim, R.id.tv_registerCode})
    public void onClick(View view) {
        switch (view.getId()) {
            //验证码
            case R.id.tv_registerCode:
                mc.start();
                tv_registerCode.setEnabled(false);
                // String username = userNameEditText.getText().toString().trim();// 帐号

                AjaxParams map = new AjaxParams();
                map.put("phone", user.getPhone());

                DD.v("修改密码:：" + Contantor.code2 + "?" + map.toString());
                new FinalHttp().post(Contantor.code2, map, new AjaxCallBack<String>() {
                    @Override
                    public void onFailure(Throwable t, int errorNo, String strMsg) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        DD.d("修改密码的验证码：" + s);
                        CodeBean code = JSON.parseObject(s, CodeBean.class);
                        df = code.getData();
                        DD.d("修改密码的验证码df：" + df);

                        et_registerCode.setText(df);
                    }
                });

                break;
            case R.id.iv_old:
                String oldPW = etOld.getText().toString();
                if (oldPW.length() > 0) {
                    if (!bl_old) { //显示
                        bl_old = true;
                        ivOld.setImageResource(R.drawable.iv_show);
                        etOld.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    } else {  //隐藏
                        bl_old = false;
                        ivOld.setImageResource(R.drawable.iv_close);
                        etOld.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                    etOld.setSelection(oldPW.length());
                }
                break;

            case R.id.tv_sim:

                UpdataPassword();

                break;
        }
    }

    boolean isCheck() {
        String oldP = etOld.getText().toString();
        if (TextUtils.isEmpty(oldP)) {
            Toast.makeText(SettingPasswordActivity.this, "请填写旧密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    void UpdataPassword() {
        //    if (isCheck()) {
        String oldP = etOld.getText().toString();
        String code = et_registerCode.getText().toString();

        AjaxParams map = new AjaxParams();
        map.put("thinksId", user.getPhone());
        map.put("newPwd", oldP);
        map.put("code", code);
        DD.d("更改密码提交：" + Contantor.UpdataPWD + "?" + map.toString());
        new FinalHttp().post(Contantor.UpdataPWD, map, new AjaxCallBack<String>() {
            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {

            }

            @Override
            public void onSuccess(String s) {
                DD.d("更改密码：" + s);
//                    MyApplication.getInstan().getUser().getData().set
                // CodeBean code = JSON.parseObject(s, CodeBean.class);
                //df = code.getData();
                // DD.d("验证码s：" + df);
                // et_registerCode.setText(df);
            }
        });
        // }
    }

    class MyCount extends CountDownTimer {

        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            tv_registerCode.setText(millisUntilFinished / 1000 + "S");
        }

        @Override
        public void onFinish() {
            tv_registerCode.setEnabled(true);
            tv_registerCode.setText("获取验证码");
        }
    }
}
