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

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import com.example.viewpagerdemo.ui.MyApplication;
import com.example.viewpagerdemo.ui.activity.MainActivity;
import com.example.viewpagerdemo.ui.bean.UserBeanL;
import com.example.viewpagerdemo.ui.jlfragmenwork.Contantor;
import com.example.viewpagerdemo.ui.jlfragmenwork.baseactivitywork.JLBaseActivity;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.TS;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.Tools;
import com.xingkesi.foodapp.R;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.util.Random;

import butterknife.Bind;

/**
 * 登陆页面
 */
public class LoginActivity extends JLBaseActivity implements View.OnClickListener {

    @Bind(R.id.username)
    EditText usernameEditText;
    @Bind(R.id.password)
    EditText passwordEditText;
    @Bind(R.id.bt_logoButton)
    Button bt_logoButton;
    @Bind(R.id.tv_loginForgot)
    TextView tv_loginForgot;
    @Bind(R.id.iv_show)
    TextView iv_show;
    @Bind(R.id.rlayout)
    RelativeLayout rlayout;

    private String currentUsername;
    private String currentPassword;
    MyCount mc;



    String tag;


    @Override
    public void initID() {
        super.initID();
        tag = getIntent().getStringExtra("tag");

        usernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) {
                    passwordEditText.setText("");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    @Override
    public void initObject() {
        super.initObject();
        tv_loginForgot.setOnClickListener(this);
        iv_show.setOnClickListener(this);
        bt_logoButton.setOnClickListener(this);
        mc = new MyCount(60000, 1000);
    }

    @Override
    public int setColor() {
        return R.color.logding_bg;
    }

    @Override
    public int setViewLayout() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return R.layout.jk_activity_login;
    }


    /**
     * 必填内容验证
     */
    private boolean verfication() {


        currentUsername = usernameEditText.getText().toString().trim();
        currentPassword = passwordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(currentUsername)) {
            TS.shortTime("请输入账号");
            return false;
        }


        if (TextUtils.isEmpty(currentPassword)) {
            TS.shortTime("请输入验证码");
            return false;
        }


        if (!Tools.isNetwork(getApplicationContext())) {
            TS.PlaseNet();
            return false;
        }

        return true;
    }


    /**
     * 登录
     */
    public void login() {
        if (verfication()) {
            showWait();
            final String name = usernameEditText.getText().toString();
            final String paws = passwordEditText.getText().toString();
            AjaxParams ajaxParams = new AjaxParams();
            ajaxParams.put("thinksId", name);
            ajaxParams.put("password", paws);
            DD.d(Contantor.logdin + "?" + ajaxParams.toString());

            new FinalHttp().post(Contantor.logdin, ajaxParams, new AjaxCallBack<String>() {
                @Override
                public void onSuccess(String s) {

                    DD.d("登录0:" + s);
                    UserBeanL user = JSON.parseObject(s, UserBeanL.class);
                    // user.setUserName(name);
                    //user.setUserPws(paws);

                    if (user.isSuccess()) {
                        DD.d("登录ID:" + user.getData().getId() + "===" + user.getData().getThinksId());
                        MyApplication.getInstan().setUser(user);
                        //---------正式版请将下面的放开--------------------
                        try {
                            if(MyApplication.getInstan().getUserName()!=null && !MyApplication.getInstan().getUserName().equals("13306400282")) {
                                DD.d("上传通讯录");
                                new Tools().init(getApplicationContext());
                            }else{
                                DD.d("不去上传通讯录");

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        closeWait();
                        finish();
                        //startActivity(new );

                    } else {
                        TS.shortTime("登录失败,请重新登录");
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
    }


    /**
     * 注册
     */
    public void registers() {
        startActivityForResult(new Intent(this, RegisterActivity.class), 0);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1000) {
            usernameEditText.setText(data.getStringExtra("phone"));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            //登录
            case R.id.bt_logoButton:
                login();
                break;

            // 验证码
            case R.id.iv_show:

                //mc.start();
                //iv_show.setEnabled(false);

                break;

            /*// 注册
            case R.id.registers:

                //registers();
                break;*/

            // 忘记密码
            case R.id.tv_loginForgot:
                // startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
                registers();
                break;
        }
    }


    private void controlKeyboardLayout(final View root, final View scrollToView) {
        root.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                //获取root在窗体的可视区域
                root.getWindowVisibleDisplayFrame(rect);
                //获取root在窗体的不可视区域高度(被其他View遮挡的区域高度)
                int rootInvisibleHeight = root.getRootView().getHeight() - rect.bottom;
                //若不可视区域高度大于100，则键盘显示
                if (rootInvisibleHeight > 100) {
                    int[] location = new int[2];
                    //获取scrollToView在窗体的坐标
                    scrollToView.getLocationInWindow(location);
                    //计算root滚动高度，使scrollToView在可见区域
                    int srollHeight = (location[1] + scrollToView.getHeight()) - rect.bottom;
                    root.scrollTo(0, srollHeight);
                } else {
                    //键盘隐藏
                    root.scrollTo(0, 0);
                }
            }
        });
    }


    class MyCount extends CountDownTimer {

        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            iv_show.setText(millisUntilFinished / 1000 + "S");
        }

        @Override
        public void onFinish() {
            iv_show.setEnabled(true);
            iv_show.setText("获取验证码");
        }
    }




}
