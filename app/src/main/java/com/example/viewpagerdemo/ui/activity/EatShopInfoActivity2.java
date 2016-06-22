package com.example.viewpagerdemo.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.viewpagerdemo.ui.bean.ContentBean;
import com.example.viewpagerdemo.ui.bean.ContentListBean;
import com.example.viewpagerdemo.ui.bean.EatInfoOneBaen;
import com.example.viewpagerdemo.ui.bean.ShoppingInfoBean;
import com.example.viewpagerdemo.ui.adapter.EatInfoContentAdatper;
import com.example.viewpagerdemo.ui.adapter.EateInfoAotuAdapter;
import com.example.viewpagerdemo.ui.jlfragmenwork.Contantor;
import com.example.viewpagerdemo.ui.jlfragmenwork.baseactivitywork.JLBaseActivity;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.xingkesi.foodapp.R;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/5/22.
 */
public class EatShopInfoActivity2 extends JLBaseActivity implements View.OnClickListener {

    @Bind(R.id.iv_back)
    ImageView iv_back;
    @Bind(R.id.tv_title)
    TextView tv_title;
    //----------------------------------
    @Bind(R.id.iv_right_image)
    ImageView iv_right_image;
    @Bind(R.id.tv_right_text)
    TextView tv_right_text;
    //--------------轮班---------------
    @Bind(R.id.eat_vpone)
    ViewPager eatVpone;
    @Bind(R.id.dotone)
    LinearLayout dot_layout;
    //------------
    @Bind(R.id.shoppingName)//标题
            TextView shoppingName;
    @Bind(R.id.shopgg)//进店逛逛
            TextView shopgg;
    @Bind(R.id.shopme)//我要买
            TextView shopme;
    @Bind(R.id.peisong)//配送
            TextView peiSong;
    @Bind(R.id.money)//价格
            TextView money;
    @Bind(R.id.webview)
    WebView webview;
    @Bind(R.id.iv_kd)
    ImageView iv_kd;
    @Bind(R.id.tv_kd)
    TextView tv_kd;
    @Bind(R.id.allcontent)//全部评价
            TextView allcontent;
    @Bind(R.id.mylist)
    ListView mylist;//评价列表
    ArrayList<ContentListBean> contentList;


    //---------------------------------------
    private ArrayList<View> dots; // 图片标题正文的那些点
    private int currentItem = 0; // 当前图片的索引号
    private ScheduledExecutorService scheduledExecutorService;
    EateInfoAotuAdapter eateAdapter;
    // 轮播banner的数据
    private List<EatInfoOneBaen> adList;
    //----------------------------------------------------


    String id, name;

    @Override
    public int setViewLayout() {
        return R.layout.eat_shop_info;
    }


    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            eatVpone.setCurrentItem(currentItem);
        }
    };

    @Override
    public void initID() {
        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        DD.d("这里是详情：" + id + "==" + name);
        dots = new ArrayList<>();
        // 广告数据
        adList = new ArrayList<>();
        allcontent.setOnClickListener(this);

        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setUseWideViewPort(true);//关键点

        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        webSettings.setDisplayZoomControls(false);
        webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本
        webSettings.setAllowFileAccess(true); // 允许访问文件
        webSettings.setBuiltInZoomControls(true); // 设置显示缩放按钮
        webSettings.setSupportZoom(true); // 支持缩放


        webSettings.setLoadWithOverviewMode(true);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int mDensity = metrics.densityDpi;
        Log.d("maomao", "densityDpi = " + mDensity);
        if (mDensity == 240) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        } else if (mDensity == 160) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        } else if (mDensity == 120) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.CLOSE);
        } else if (mDensity == DisplayMetrics.DENSITY_XHIGH) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        } else if (mDensity == DisplayMetrics.DENSITY_TV) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        } else {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        }


/**
 * 用WebView显示图片，可使用这个参数 设置网页布局类型： 1、LayoutAlgorithm.NARROW_COLUMNS ：
 * 适应内容大小 2、LayoutAlgorithm.SINGLE_COLUMN:适应屏幕，内容将自动缩放
 */
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });

    }

    @Override
    public int setColor() {
        return super.setColor();
    }


    //评论列表
    void getContent() {
        AjaxParams map = new AjaxParams();
        map.put("goodsId", id + "");
        map.put("page", "1");
        map.put("size", "10");
        String url = Contantor.shopInfoPL;
        new FinalHttp().post(url, map, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                DD.d("===:"+s);
                ContentBean bean = JSON.parseObject(s, ContentBean.class);
                contentList = bean.getList();
                if (contentList.size() > 0) {
                    DD.d("有评论：" + contentList.size());
                    EatInfoContentAdatper ec = new EatInfoContentAdatper(EatShopInfoActivity2.this, contentList);
                    mylist.setAdapter(ec);
                    ec.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }
        });
    }


    //详情列表
    void getShoppingInfo() {
        AjaxParams map = new AjaxParams();
        map.put("id", id);
        String url = Contantor.shoppingInfo;
        new FinalHttp().post(url, map, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                ShoppingInfoBean info = JSON.parseObject(s, ShoppingInfoBean.class);
                shoppingName.setText(info.getName());
                money.setText(info.getPrice() + "");
                StringBuffer sb = new StringBuffer();
                if (info.getPeiSong() == 1) {
                    sb.append("配送/");
                }
                if (info.getKuaiDi() == 1) {
                    sb.append("全国快递/");
                }
                if (info.getZiTi() == 1) {
                    sb.append("自提");
                }

                if (info.getBaoYou() != 1) {
                    iv_kd.setVisibility(View.GONE);
                    tv_kd.setVisibility(View.VISIBLE);
                    tv_kd.setText("快递费:" + info.getYouPrice());
                }


                String url = info.getContent();
                //DD.d("有网页:"+Contantor.Imagepost+url);
                if (url.contains("http://www.")) {
                    DD.d("网页:" + Contantor.Imagepost + url);
                    webview.loadUrl(url);
                } else {
                    DD.d("有:" + Contantor.Imagepost + url);
                    webview.loadDataWithBaseURL(null, "<html><hera><meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8 \" />" +
                            "</head><body>" + url + "</body></html>", "text/html", "utf-8", null);
                    //webview.loadDataWithBaseURL(null,url,"text/html","utf-8",null);
                }
                //轮播图数据
                if (info.getBannerList().size() > 0) {
                    adList = info.getBannerList();
                    int size = adList.size();
                    //DD.d("详情中的轮播图:" + size + "===" + name);
                    for (int i = 0; i < size; i++) {
                        ImageView view = new ImageView(EatShopInfoActivity2.this);
                        view.setBackgroundResource(R.drawable.dot_normal);
                        LinearLayout.LayoutParams viewL = new LinearLayout.LayoutParams(10, 10);
                        viewL.setMargins(5, 0, 5, 0);
                        view.setLayoutParams(viewL);
                        dots.add(view);
                        dot_layout.addView(view);
                    }
                    eateAdapter = new EateInfoAotuAdapter(EatShopInfoActivity2.this, adList);
                    eatVpone.setAdapter(eateAdapter);// 设置填充ViewPager页面的适配器
                    eateAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }
        });

    }

    @Override
    public void initObject() {
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setImageResource(R.drawable.cp_fh);
        iv_right_image.setVisibility(View.VISIBLE);
        tv_right_text.setVisibility(View.VISIBLE);
        iv_right_image.setImageResource(R.drawable.cp_xx);
        tv_right_text.setBackgroundResource(R.drawable.icon_share_blue);
        tv_title.setText(name);
        //
        iv_right_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //评论页
                Log.d("LD", "============11:" + id);
                DD.d("到的：" + id);
                Intent it = new Intent(EatShopInfoActivity2.this, CommentsActivity.class);
                it.putExtra("id", id);
                startActivity(it);


            }
        });

        getShoppingInfo();
        getContent();
        startAd();


        shopme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EatShopInfoActivity2.this, ShoppingDDActivity.class));
            }
        });


       /* for (int i = 0; i < adList.size(); i++) {
            ImageView view = new ImageView(this);
            view.setBackgroundResource(R.drawable.dot_normal);
            LinearLayout.LayoutParams viewL = new LinearLayout.LayoutParams(10, 10);
            viewL.setMargins(5, 0, 5, 0);
            view.setLayoutParams(viewL);
            dots.add(view);
            dot_layout.addView(view);
        }*/
        eateAdapter = new EateInfoAotuAdapter(this, adList);
        eatVpone.setAdapter(eateAdapter);// 设置填充ViewPager页面的适配器
        // 设置一个监听器，当ViewPager中的页面改变时调用
        eatVpone.setOnPageChangeListener(new MyPageChangeListener());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.allcontent:
                //
                Intent it = new Intent(EatShopInfoActivity2.this, AllContentActivity.class);
                it.putExtra("id", id + "");
                startActivity(it);

                break;
        }
    }


    class MyPageChangeListener implements ViewPager.OnPageChangeListener {

        private int oldPosition = 0;

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int position) {
            currentItem = position;
            dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
            dots.get(position).setBackgroundResource(R.drawable.dot_focused);
            oldPosition = position;
        }
    }

    private class ScrollTask implements Runnable {

        @Override
        public void run() {
            synchronized (eatVpone) {
                currentItem = (currentItem + 1) % adList.size();
                handler.obtainMessage().sendToTarget();
            }
        }
    }

    private void startAd() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // 当Activity显示出来后，每两秒切换一次图片显示
        scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 2,
                TimeUnit.SECONDS);
    }


}
