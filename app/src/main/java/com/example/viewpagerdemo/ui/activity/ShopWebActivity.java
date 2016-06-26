package com.example.viewpagerdemo.ui.activity;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.viewpagerdemo.ui.MyApplication;
import com.example.viewpagerdemo.ui.jlfragmenwork.baseactivitywork.JLBaseActivity;
import com.xingkesi.foodapp.R;
import com.yiw.circledemo2.bean.ToolsHost;

import butterknife.Bind;

public class ShopWebActivity extends JLBaseActivity {

    @Bind(R.id.iv_back)
    ImageView iv_back;
    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.wev)
    WebView webView;

    @Override
    public int setViewLayout() {
        return R.layout.activity_about_web;
    }

    @Override
    public void initID() {
        super.initID();
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setImageResource(R.drawable.cp_fh);
        tv_title.setText("我要开店");
    }

    @Override
    public void initObject() {
        super.initObject();

        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });


       // DD.v("gyuanyu:" + ToolsHost.HEDEUT + "/app/system/about");
        webView.loadUrl(ToolsHost.HEDEUT + "/app/system/registerShop?userId="+ MyApplication.getInstan().getUser().getData().getId());
        /*new FinalHttp().get(ToolsHost.HEDEUT + "/app/system/about", new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                DD.v("333收货地址:" + s);

            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }
        });*/
    }
}
