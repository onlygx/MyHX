package com.example.viewpagerdemo.ui.jlfragmenwork.baseactivitywork;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.viewpagerdemo.ui.jlfragmenwork.actvity.LoadingDialog;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.TS;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.Tools;
import com.xingkesi.foodapp.R;

import butterknife.ButterKnife;


public class JLBaseActivity extends Activity {

    private InputMethodManager manager;
    protected static int windowWidth;// 屏幕宽度
    protected static int windowHeight;// 屏幕高度
    LoadingDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean title[] = TitleAndScreen();
        if (title[0]) {
            getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }
        if (title[1]) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

       /*   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Tools.setTranslucentStatus(this,true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(setColor());//通知栏所需颜色
        }*/
        setContentView(setViewLayout());
        ButterKnife.bind(this);
        initID();
        initObject();
    }

    public boolean[] TitleAndScreen() {
        return new boolean[]{true, false};
    }

    public int setColor() {
        return R.color.waiter;
    }

    public void initID() {
    }

    public void initObject() {
       /* builder= new BoilingDialog.Builder(this);
        dialog = builder.build();*/
        manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        windowWidth = Tools.decodeDisplayMetrics(this)[0];
        windowHeight = Tools.decodeDisplayMetrics(this)[1];

        if (!Tools.isNetwork(getApplicationContext())) {
            TS.longTime("请连接网络");
            return;
        }
    }


    public void ReustData() {
    }

    public int setViewLayout() {
        return 0;
    }


    /**
     * 标题栏返回按钮
     */
    public void goBack(View view) {
        finish();
    }


    /**
     * 点击非editText时，隐藏键盘
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }


    /**
     * 隐藏软键盘
     */
    protected void hideKeyboard() {
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getCurrentFocus() != null)
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 判断是否需要隐藏input
     */
    public static boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    protected void showWait() {
        if (dialog == null) {
            dialog = new LoadingDialog(this, R.style.LoadingDialog_style);
        }
        dialog.show();
    }

    protected void closeWait() {
        if (dialog != null)
            dialog.dismiss();
    }
}
