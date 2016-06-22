package com.example.viewpagerdemo.ui.jlfragmenwork.baseactivitywork;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.viewpagerdemo.ui.jlfragmenwork.util.SystemBarTintManager;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.Tools;
import com.example.viewpagerdemo.ui.jlfragmenwork.view.BoilingDialog;
import com.xingkesi.foodapp.R;

import butterknife.ButterKnife;

public class JLBaseFragmentActivity extends FragmentActivity {

    private InputMethodManager manager;
    BoilingDialog.Builder builder;
    BoilingDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Tools.setTranslucentStatus(JLBaseFragmentActivity.this,true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.waiter);//通知栏所需颜色
        }*/

        //全屏取消状态栏
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(setViewLayout());
        ButterKnife.bind(this);
        //无标题栏：
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view=findViewById(R.id.mainLayout);
        setImmerseLayout(view);
        initID();
        initObject();
    }

    protected void setImmerseLayout(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
                /*window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);*/
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            int statusBarHeight = getStatusBarHeight(this.getBaseContext());
            view.setPadding(0, statusBarHeight, 0, 0);
        }
    }

    public void initID() {
    }

    public void initObject() {
        builder = new BoilingDialog.Builder(this);
        dialog = builder.build();
        manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    public int setViewLayout(){
        return 0;
    }



    /**
     * 标题栏返回按钮
     *
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
     *
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
    /**
     * 用于获取状态栏的高度。 使用Resource对象获取（推荐这种方式）
     *
     * @return 返回状态栏高度的像素值。
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    protected void showWait(){
        if(builder==null){
        builder = new BoilingDialog.Builder(this);
        }
        if(dialog==null){
        dialog = builder.build();
        }
        dialog.show();
    }

    protected void closeWait(){
        if(builder==null){
            builder = new BoilingDialog.Builder(this);
        }
        if(dialog==null){
            dialog = builder.build();
        }
        dialog.dismiss();
    }


    /*void editLoging(){
        final MyDialog md = new MyDialog(IdentityAuthActivity.this, R.style.WinDialog);
        md.setContentView(R.layout.dialog);
        TextView tv_dialogtext = (TextView) md.getWindow().findViewById(R.id.tv_dialogtext);
        tv_dialogtext.setText("确定退出登录？");
        Button cancel = (Button) md.getWindow().findViewById(R.id.cancelDialog);
        Button determine = (Button) md.getWindow().findViewById(R.id.OkDialog);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                md.dismiss();
            }
        });
        determine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                md.dismiss();
                finish();
            }
        });
        md.show();
    }*/

    private void colorChange(View view) {
       /* // 用来提取颜色的Bitmap
        Bitmap bitmap = Tools.convertViewToBitmap(view,100,100);
        // Palette的部分
        Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
            *//**
             * 提取完之后的回调方法
             *//*
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrant = palette.getVibrantSwatch();
                *//* 界面颜色UI统一性处理,看起来更Material一些 *//*
                // mPagerSlidingTabStrip.setBackgroundColor(vibrant.getRgb());
                //mPagerSlidingTabStrip.setTextColor(vibrant.getTitleTextColor());
                // 其中状态栏、游标、底部导航栏的颜色需要加深一下，也可以不加，具体情况在代码之后说明
                //mPagerSlidingTabStrip.setIndicatorColor(colorBurn(vibrant.getRgb()));
                //tintManager.setStatusBarTintResource(Tools.colorBurn(vibrant.getRgb()));//通知栏所需颜色
                //mToolbar.setBackgroundColor(vibrant.getRgb());
               *//* if (android.os.Build.VERSION.SDK_INT >= 21) {
                    Window window = context.getWindow();
                    // 很明显，这两货是新API才有的。
                    window.setStatusBarColor(colorBurn(vibrant.getRgb()));
                    window.setNavigationBarColor(colorBurn(vibrant.getRgb()));
                }*//*
            }
        });*/
    }
}
