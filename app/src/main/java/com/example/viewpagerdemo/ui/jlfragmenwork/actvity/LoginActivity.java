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
import com.alibaba.mobileim.YWChannel;
import com.alibaba.mobileim.YWIMKit;
import com.alibaba.mobileim.channel.event.IWxCallback;
import com.alibaba.mobileim.channel.util.YWLog;
import com.alibaba.mobileim.fundamental.widget.YWAlertDialog;
import com.alibaba.mobileim.login.YWLoginCode;
import com.alibaba.mobileim.login.YWLoginState;
import com.alibaba.mobileim.utility.AccountInfoTools;
import com.alibaba.mobileim.utility.IMNotificationUtils;
import com.alibaba.mobileim.utility.IMPrefsTools;
import com.alibaba.tcms.client.ServiceChooseHelper;
import com.alibaba.tcms.env.EnvManager;
import com.alibaba.tcms.env.TcmsEnvType;
import com.alibaba.tcms.env.YWEnvManager;
import com.alibaba.tcms.env.YWEnvType;
import com.example.viewpagerdemo.ui.Contantor;
import com.example.viewpagerdemo.ui.MyApplication;
import com.example.viewpagerdemo.ui.activity.MainActivity;
import com.example.viewpagerdemo.ui.bean.UserBeanL;
import com.example.viewpagerdemo.ui.jlfragmenwork.baseactivitywork.JLBaseActivity;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.TS;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.Tools;
import com.taobao.openimui.demo.FragmentTabs;
import com.taobao.openimui.sample.LoginSampleHelper;
import com.taobao.openimui.sample.NotificationInitSampleHelper;
import com.taobao.openimui.sample.UserProfileSampleHelper;
import com.xingkesi.foodapp.R;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

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
    @Bind(R.id.forpass)
    TextView forpass;
    @Bind(R.id.rlayout)
    RelativeLayout rlayout;

    //@Bind(R.id.appkey)
    // EditText appKeyView;

    private boolean autoLogin = false;
    boolean show = false;

    private String currentUsername;
    private String currentPassword;
    MyCount mc;

    //------------------------------
    public static final String AUTO_LOGIN_STATE_ACTION = "com.openim.autoLoginStateActionn";
    private static final int GUEST = 1;
    private static final String USER_ID = "userId";
    private static final String PASSWORD = "password";
    private static final String TAG = LoginActivity.class.getSimpleName();
    private LoginSampleHelper loginHelper;

    //private ProgressBar progressBar;
    private Handler handler = new Handler(Looper.getMainLooper());
    //private ImageView lg;
    public static String APPKEY;
    private int mClickCount = 0;


    String tag;


    private BroadcastReceiver mAutoLoginStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final int state = intent.getIntExtra("state", -1);
            YWLog.i(TAG, "mAutoLoginStateReceiver, loginState = " + state);
            if (state == -1) {
                return;
            }
            handleAutoLoginState(state);
        }
    };


    @Override
    public void initID() {
        super.initID();
        tag = getIntent().getStringExtra("tag");
        loginHelper = LoginSampleHelper.getInstance();
       /*
        //读取登录成功后保存的用户名和密码
        String localId = IMPrefsTools.getStringPrefs(LoginActivity.this, USER_ID, "");
        if (!TextUtils.isEmpty(localId)) {
            usernameEditText.setText(localId);
            String localPassword = IMPrefsTools.getStringPrefs(LoginActivity.this, PASSWORD, "");
            if (!TextUtils.isEmpty(localPassword)) {
                passwordEditText.setText(localPassword);
            }
        }

        if (LoginSampleHelper.sEnvType == YWEnvType.ONLINE) {
            if (TextUtils.isEmpty(usernameEditText.getText())) {
                usernameEditText.setText(getRandAccount());
            }
            if (TextUtils.isEmpty(passwordEditText.getText())) {
                passwordEditText.setText("taobao1234");
            }
            if (TextUtils.isEmpty(appKeyView.getText())) {
                appKeyView.setText(LoginSampleHelper.APP_KEY);
            }
        } else if (LoginSampleHelper.sEnvType == YWEnvType.TEST) {
            if (TextUtils.isEmpty(usernameEditText.getText())) {
                Random r = new Random();
                int suffix = Math.abs(r.nextInt() % 100);
                usernameEditText.setText("fk" + suffix);
            }
            if (TextUtils.isEmpty(passwordEditText.getText())) {
                passwordEditText.setText("taobao1234");
            }
            if (TextUtils.isEmpty(appKeyView.getText())) {
                appKeyView.setText(LoginSampleHelper.APP_KEY_TEST);
            }

        } else if (LoginSampleHelper.sEnvType == YWEnvType.PRE) {
            if (TextUtils.isEmpty(usernameEditText.getText())) {
                usernameEditText.setText("testpro74");
            }
            if (TextUtils.isEmpty(passwordEditText.getText())) {
                passwordEditText.setText("taobao1234");
            }
            if (TextUtils.isEmpty(appKeyView.getText())) {
                appKeyView.setText(LoginSampleHelper.APP_KEY);
            }
        }


        init(usernameEditText.getText().toString(), appKeyView.getText().toString());

        myRegisterReceiver();

        //一些其它的初始化
        //自定义消息处理初始化(如果不需要自定义消息，则可以省去)
//				CustomMessageSampleHelper.initHandler();

        // bt_logoButton = (Button) findViewById(R.id.login);
        // annoybt_logoButton = (Button) findViewById(R.id.annoylogin);


        //  lg = (ImageView) findViewById(R.id.logo);
        //  lg.setImageBitmap(logo);*/
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
        //String userid = "testpro1";
        // String password = "taobao1234";
//        usernameEditText.setText("13306400280");
        usernameEditText.setText("13306400282");
        passwordEditText.setText("1");
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

        if (TextUtils.isEmpty(currentUsername) || currentUsername.length() != 11) {
            TS.shortTime("请输入11位手机号");
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
//            ajaxParams.put("password", MD5Util.md5xs(paws));
            ajaxParams.put("password", paws);
            DD.d(Contantor.logdin + "?" + ajaxParams.toString());
           // LogdindOpenIME();
            new FinalHttp().post(Contantor.logdin, ajaxParams, new AjaxCallBack<String>() {
                @Override
                public void onSuccess(String s) {

                    DD.d("登录0:" + s);
                    UserBeanL user = JSON.parseObject(s, UserBeanL.class);

                    if (user.isSuccess()) {
                        DD.d("登录ID:" + user.getData().getId() + "===" + user.getData().getThinksId());
                        MyApplication.getInstan().setUser(user);
                        MyApplication.getInstan().setUserName(name);
                      //  MyApplication.getInstan().setUserPwd(paws);
                        finish();
                        //---------正式版请将下面的放开--------------------
                        LogdindOpenIME();

                    } else {
                        TS.shortTime("登录失败,请重新登录");
                    }
                    closeWait();


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
        if (autoLogin) {
            return;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.forpass:

                DD.v("忘记密码---");

                break;

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



    private YWEnvType envType = YWEnvType.ONLINE;
    private AlertDialog dialog;

    private void switchAndSaveEnv() {
        final String[] items = {"线上", "预发", "测试"};
        if (dialog == null) {
            dialog = new YWAlertDialog.Builder(this)
                    .setTitle("设置网络")
                    .setItems(items,
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(
                                        DialogInterface dialog,
                                        int which) {

                                    TcmsEnvType tcmsEnvType = TcmsEnvType.ONLINE;
                                    if (which == 0) {
                                        envType = YWEnvType.ONLINE;
                                    } else if (which == 1) {
                                        envType = YWEnvType.PRE;
                                        tcmsEnvType = TcmsEnvType.PRE;
                                    } else if (which == 2) {
                                        envType = YWEnvType.TEST;
                                        tcmsEnvType = TcmsEnvType.TEST;
                                    }

                                    EnvManager.getInstance().resetEnvType(MyApplication.getContext(), tcmsEnvType);
                                    YWEnvManager.prepare(MyApplication.getContext(), envType);
                                    IMNotificationUtils.showToast("切换环境，程序退出，请再次启动", LoginActivity.this);
                                    ServiceChooseHelper.exitService(LoginActivity.this);//xianzhen: service must restart too.

                                    AccountInfoTools.saveAnnoyAccount("", "");


                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            System.exit(0);
                                        }
                                    }, 1000);
                                }
                            }).setNegativeButton(
                            getResources().getString(R.string.cancel),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    dialog.cancel();
                                }
                            }).create();
        }
        dialog.show();
    }

    private void handleAutoLoginState(int loginState) {
        if (loginState == YWLoginState.logining.getValue()) {
           /* if (progressBar.getVisibility() != View.VISIBLE){
                progressBar.setVisibility(View.VISIBLE);
            }*/
            showWait();
            bt_logoButton.setClickable(false);
        } else if (loginState == YWLoginState.success.getValue()) {
            bt_logoButton.setClickable(true);
            // progressBar.setVisibility(View.GONE);
            closeWait();
            Intent intent = new Intent(LoginActivity.this, FragmentTabs.class);
            LoginActivity.this.startActivity(intent);
            LoginActivity.this.finish();
        } else {
            YWIMKit ywimKit = LoginSampleHelper.getInstance().getIMKit();
            if (ywimKit != null) {
                if (ywimKit.getIMCore().getLoginState() == YWLoginState.success) {
                    bt_logoButton.setClickable(true);
                    //progressBar.setVisibility(View.GONE);
                    closeWait();
                    Intent intent = new Intent(LoginActivity.this, FragmentTabs.class);
                    LoginActivity.this.startActivity(intent);
                    LoginActivity.this.finish();
                    return;
                }
            }
            //当作失败处理
            // progressBar.setVisibility(View.GONE);
            closeWait();
            bt_logoButton.setClickable(true);
        }
    }

    private void init(String userId, String appKey) {
        //初始化imkit
        DD.i("login 旺旺 初始化1!");
        LoginSampleHelper.getInstance().initIMKit(userId, appKey);
        DD.i("login 旺旺 初始化2!");
        //自定义头像和昵称回调初始化(如果不需要自定义头像和昵称，则可以省去)
        UserProfileSampleHelper.initProfileCallback();
        //通知栏相关的初始化
        NotificationInitSampleHelper.init();

    }

    /**
     * 保存登录的用户名密码到本地
     *
     * @param userId
     * @param password
     */
    private void saveIdPasswordToLocal(String userId, String password) {
        IMPrefsTools.setStringPrefs(LoginActivity.this, USER_ID, userId);
        IMPrefsTools.setStringPrefs(LoginActivity.this, PASSWORD, password);

    }

    private void myRegisterReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(AUTO_LOGIN_STATE_ACTION);
        LocalBroadcastManager.getInstance(YWChannel.getApplication()).registerReceiver(mAutoLoginStateReceiver, filter);
    }

    private void myUnregisterReceiver() {
        LocalBroadcastManager.getInstance(YWChannel.getApplication()).unregisterReceiver(mAutoLoginStateReceiver);
    }

  /*  void LogdindOpenIME(int i) {
        //判断当前网络状态，若当前无网络则提示用户无网络
        if (YWChannel.getInstance().getNetWorkState().isNetWorkNull()) {
            Toast.makeText(LoginActivity.this, "网络已断开，请稍后再试哦~", Toast.LENGTH_SHORT).show();
            return;
        }

        //开始登录
        String userid = "testpro1";
        String password = "taobao1234";
        init(userid, MyApplication.APP_KEY);
        YWIMKit mIMKit = LoginSampleHelper.getInstance().getIMKit();
        IYWLoginService loginService = mIMKit.getLoginService();
        YWLoginParam loginParam = YWLoginParam.createLoginParam(userid, password);
        loginService.login(loginParam, new IWxCallback() {

            @Override
            public void onSuccess(Object... arg0) {
                DD.i("login 旺旺 登录!onSuccess");
            }

            @Override
            public void onProgress(int arg0) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onError(int errCode, String description) {
                //如果登录失败，errCode为错误码,description是错误的具体描述信息
                DD.i("login 旺旺 登录!onSuccess");
            }
        });

    }
*/
    void LogdindOpenIME() {
        //判断当前网络状态，若当前无网络则提示用户无网络
        if (YWChannel.getInstance().getNetWorkState().isNetWorkNull()) {
            Toast.makeText(LoginActivity.this, "网络已断开，请稍后再试哦~", Toast.LENGTH_SHORT).show();
            return;
        }
        bt_logoButton.setClickable(false);
        final Editable userId = usernameEditText.getText();
        final Editable password = passwordEditText.getText();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(usernameEditText.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(passwordEditText.getWindowToken(), 0);
        LoginSampleHelper.APP_KEY = MyApplication.APP_KEY;
        String wanName = MyApplication.getInstan().getUser().getData().getThinksId();

        init(wanName, MyApplication.APP_KEY);
        APPKEY = MyApplication.APP_KEY;
        DD.i("login 开始 旺旺 登录!账号:" + wanName);
        loginHelper.login_Sample(wanName, "123456", MyApplication.APP_KEY, new IWxCallback() {

            @Override
            public void onSuccess(Object... arg0) {
                saveIdPasswordToLocal(userId.toString(), password.toString());

                bt_logoButton.setClickable(true);
                // progressBar.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this, "登录成功",
                        Toast.LENGTH_SHORT).show();
                DD.i("login 旺旺 登录成功!");
               /* Intent intent = new Intent(LoginActivity.this, FragmentTabs.class);
                intent.putExtra(FragmentTabs.LOGIN_SUCCESS, "loginSuccess");
                LoginActivity.this.startActivity(intent);
                LoginActivity.this.finish();*/

                // MyApplication.getInstan().setUser(user);
                MyApplication.getInstan().setUserName(userId.toString());
                MyApplication.getInstan().setUserPwd(password.toString());
                closeWait();
                if (tag.equals("logding")) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
                finish();

//						YWIMKit mKit = LoginSampleHelper.getInstance().getIMKit();
//						EServiceContact contact = new EServiceContact("qn店铺测试账号001:找鱼");
//						LoginActivity.this.startActivity(mKit.getChattingActivityIntent(contact));
//                        mockConversationForDemo();
            }

            @Override
            public void onProgress(int arg0) {

            }

            @Override
            public void onError(int errorCode, String errorMessage) {
                //progressBar.setVisibility(View.GONE);
                if (errorCode == YWLoginCode.LOGON_FAIL_INVALIDUSER) { //若用户不存在，则提示使用游客方式登陆
                    showDialog(GUEST);
                    closeWait();
                    DD.i("login 旺旺 失败1：" + errorMessage + "===" + errorCode);
                } else {
                    DD.w("login 旺旺 失败2:" + errorMessage + "===" + errorCode);
                    bt_logoButton.setClickable(true);
                    YWLog.w(TAG, "登录失败，错误码：" + errorCode + "  错误信息：" + errorMessage);
                    //Notification.showToastMsg(LoginActivity.this, errorMessage);
                }
            }
        });
    }
}
