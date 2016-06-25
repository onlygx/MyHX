/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.viewpagerdemo.ui.jlfragmenwork.actvity;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.viewpagerdemo.ui.bean.BaseBean;
import com.example.viewpagerdemo.ui.bean.CodeBean;
import com.example.viewpagerdemo.ui.jlfragmenwork.Contantor;
import com.example.viewpagerdemo.ui.jlfragmenwork.baseactivitywork.JLBaseActivity;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.TS;
import com.xingkesi.foodapp.R;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import butterknife.Bind;

/**
 * 注册页
 */
public class RegisterActivity extends JLBaseActivity implements View.OnClickListener {
    @Bind(R.id.et_registerNames)
    EditText et_registerNames;//用户名
    @Bind(R.id.et_registerNamese)
    EditText et_registerNamese;//昵称


    @Bind(R.id.et_registerPassword2)
    EditText et_registerPassword2;
    @Bind(R.id.et_registerName)
    EditText userNameEditText;
    @Bind(R.id.et_registerCode)
    EditText et_registerCode;// 验证码
    @Bind(R.id.et_registerPassword)
    EditText passwordEditText;
    @Bind(R.id.tv_registerCode)
    TextView tv_registerCode;
    /*@Bind(R.id.tv_title)
    TextView tv_includeTitle;*/
    @Bind(R.id.tv_haveuser)
    TextView tv_haveuser;
    @Bind(R.id.et_registerSubmit)
    Button et_registerSubmit;

    String df;

    private MyCount mc;


    @Override
    public int setViewLayout() {
        return R.layout.jk_register;
    }

    @Override
    public void initID() {
        super.initID();
    }

    @Override
    public void initObject() {
        super.initObject();
       // tv_includeTitle.setText("注册");
        tv_registerCode.setEnabled(false);
        tv_registerCode.setOnClickListener(this);
        et_registerSubmit.setOnClickListener(this);
        tv_haveuser.setOnClickListener(this);
        mc = new MyCount(60000, 1000);


        userNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // 是11位时 获取验证码可用
                if (s.length() == 11) {
                    tv_registerCode.setEnabled(true);
                    tv_registerCode.setBackgroundResource(R.drawable.shape_bt_grenn_corners);
                    tv_registerCode.setTextColor(Color.WHITE);
                } else {
                    tv_registerCode.setEnabled(false);
                    tv_registerCode.setBackgroundResource(R.drawable.shape_gray_code);
                    tv_registerCode.setTextColor(getResources().getColor(R.color.gray));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    /**
     * 注册
     */
    public void register() {
       // final String usernames = et_registerNames.getText().toString().trim();// 用户名
      //  final String usernamese = et_registerNamese.getText().toString().trim();// 昵称
        final String username = userNameEditText.getText().toString().trim();// 帐号
        final String pwd = passwordEditText.getText().toString().trim(); // 密码
        final String pwd2 = et_registerPassword2.getText().toString().trim(); // 密码
        String confirm_pwd = et_registerCode.getText().toString().trim();// 验证码
       /* if (TextUtils.isEmpty(usernames)) {
            Toast.makeText(this, getResources().getString(R.string.User_name_cannot_be_empty2), Toast.LENGTH_SHORT).show();
            et_registerNames.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(usernamese)) {
            Toast.makeText(this, getResources().getString(R.string.User_name_cannot_be_empty3), Toast.LENGTH_SHORT).show();
            et_registerNamese.requestFocus();
            return;
        }*/
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, getResources().getString(R.string.User_name_cannot_be_empty), Toast.LENGTH_SHORT).show();
            userNameEditText.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, getResources().getString(R.string.Password_cannot_be_empty), Toast.LENGTH_SHORT).show();
            passwordEditText.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(confirm_pwd)) {
            Toast.makeText(this, getResources().getString(R.string.Confirm_password_cannot_be_empty), Toast.LENGTH_SHORT).show();
            et_registerCode.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(pwd2)) {
            Toast.makeText(this, getResources().getString(R.string.Confirm_password_cannot_be_empty2), Toast.LENGTH_SHORT).show();
            return;
        }

        if (!pwd.equals(pwd2)) {
            TS.shortTime("两次密码不一致");
            return;
        }

        showWait();
        AjaxParams map = new AjaxParams();
        map.put("thinksId", username);
        map.put("phone", username);
        map.put("nick", username);
        map.put("password", pwd);
//        map.put("password", MD5Util.md5xs(pwd));
        map.put("code", confirm_pwd);
        DD.d("" + Contantor.register + "--" + map.toString());
        new FinalHttp().post(Contantor.register, map, new AjaxCallBack<String>() {

            @Override
            public void onSuccess(String s) {
                BaseBean base = JSON.parseObject(s, BaseBean.class);
                DD.d("注册:" + base.isSuccess() + "==" + base.getCode());
                if (base.isSuccess()) {
                    Intent it = new Intent(RegisterActivity.this, LoginActivity.class);
                    it.putExtra("phone", username);
                    setResult(1000, it);
                    closeWait();
                    finish();
                } else {
                    TS.shortTime("注册失败");
                    closeWait();
                }
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                closeWait();
            }
        });

    }


    public void back(View view) {
        finish();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            //验证码
            case R.id.tv_registerCode:
                mc.start();
                tv_registerCode.setEnabled(false);
                String username = userNameEditText.getText().toString().trim();// 帐号

                AjaxParams map = new AjaxParams();
                map.put("phone", username);

                new FinalHttp().post(Contantor.code, map, new AjaxCallBack<String>() {
                    @Override
                    public void onFailure(Throwable t, int errorNo, String strMsg) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        DD.d("验证码：" + s);
                        CodeBean code = JSON.parseObject(s, CodeBean.class);
                        df = code.getData();
                        DD.d("验证码s：" + df);

                        et_registerCode.setText(df);
                    }
                });

                break;

            //注册
            case R.id.et_registerSubmit:
                register();
                break;

            //已有帐号
            case R.id.tv_haveuser:
                finish();
                break;
        }

    }


    Handler han = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                et_registerCode.setText(df + "");
            }
        }
    };


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




