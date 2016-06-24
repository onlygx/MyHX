package com.example.viewpagerdemo.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.viewpagerdemo.ui.MyApplication;
import com.example.viewpagerdemo.ui.adapter.EateInfoAotuAdapter;
import com.example.viewpagerdemo.ui.bean.Collection;
import com.example.viewpagerdemo.ui.bean.EatInfoOneBaen;
import com.example.viewpagerdemo.ui.bean.ShopInfoListBean;
import com.example.viewpagerdemo.ui.bean.ShoppingInfoBean;
import com.example.viewpagerdemo.ui.jlfragmenwork.Contantor;
import com.example.viewpagerdemo.ui.jlfragmenwork.actvity.LoginActivity;
import com.example.viewpagerdemo.ui.jlfragmenwork.baseactivitywork.JLBaseActivity;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.example.viewpagerdemo.ui.jlfragmenwork.share.SelectPicPopupWindowShare;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.xingkesi.foodapp.R;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 商品详情
 * Created by Administrator on 2016/5/22.
 */
public class EatInfoActivity extends JLBaseActivity implements View.OnClickListener {

    @Bind(R.id.iv_back)
    ImageView iv_back;
    @Bind(R.id.tv_sc)
    TextView sc;
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
    @Bind(R.id.eat_layout)
    RelativeLayout eat_layout;
    //------------
    @Bind(R.id.shoppingName)//标题
            TextView shoppingName;
    @Bind(R.id.san_sc)//标题
            TextView san_sc;
    @Bind(R.id.san_pf)//标题
            TextView san_pf;
    @Bind(R.id.san_gm)//标题
            TextView san_gm;


    @Bind(R.id.money)//价格
            TextView money;
    @Bind(R.id.webview)
    WebView webview;

    double Qpic;
    /*@Bind(R.id.iv_kd)
    ImageView iv_kd;*/
   /* @Bind(R.id.tv_kd)
    TextView tv_kd;*/
    /*@Bind(R.id.allcontent)//全部评价
            TextView allcontent;*/
    /*@Bind(R.id.mylist)
    ListView mylist;//评价列表
    ArrayList<ContentListBean> contentList;*/


    //---------------------------------------
    private ArrayList<View> dots; // 图片标题正文的那些点
    private int currentItem = 0; // 当前图片的索引号
    private ScheduledExecutorService scheduledExecutorService;
    EateInfoAotuAdapter eateAdapter;
    // 轮播banner的数据
    private List<EatInfoOneBaen> adList;
    //----------------------------------------------------


    String id, name, shopID;
    Intent intent = new Intent();

    ShoppingInfoBean info;


    int isColl = 0;//默认没收藏  1收藏了

    @Override
    public int setViewLayout() {
        return R.layout.eat_info;
    }


    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            eatVpone.setCurrentItem(currentItem);
        }
    };

    @Override
    public void initID() {
        id = getIntent().getStringExtra("id");
        shopID = getIntent().getStringExtra("shopID");
        name = getIntent().getStringExtra("name");
        DD.d("这里是详情：" + id + "==" + name);
        dots = new ArrayList<>();
        // 广告数据
        adList = new ArrayList<>();
//        allcontent.setOnClickListener(this);

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
 *
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
   /* void getContent() {
        AjaxParams map = new AjaxParams();
        map.put("goodsId", id + "");
        map.put("page", "1");
        map.put("size", "10");
        String url = Contantor.shopInfoPL;
        new FinalHttp().post(url, map, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                DD.d("===:" + s);
                ContentBean bean = JSON.parseObject(s, ContentBean.class);
                contentList = bean.getList();
                if (contentList.size() > 0) {
                    DD.d("有评论：" + contentList.size());
                    EatInfoContentAdatper ec = new EatInfoContentAdatper(EatInfoActivity.this, contentList);
                    mylist.setAdapter(ec);
                    ec.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }
        });
    }*/


    //详情列表
    void getShoppingInfo() {
        AjaxParams map = new AjaxParams();
        map.put("id", id);
        String uid;
        if (MyApplication.getInstan().getUser() != null && MyApplication.getInstan().getUser().getData().getId() != 0) {
            uid = MyApplication.getInstan().getUser().getData().getId() + "";
        } else {
            uid = "";
        }
        map.put("userId", uid);
        String url = Contantor.shoppingInfo;
        DD.d("详情：" + url + "?" + map.toString());
        new FinalHttp().post(url, map, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                DD.d("详情s：" + s);
                info = JSON.parseObject(s, ShoppingInfoBean.class);
                shoppingName.setText(info.getName());
                Qpic = info.getPrice();
                money.setText(info.getPrice() + "");
                san_sc.setText(info.getCollectionCount() + "");
                san_gm.setText(info.getPayCount() + "");

                if (shopID==null || shopID.equals("")) {
                    shopID = info.getShopId() + "";
                }

//                http://119.188.182.131:8080/app/goods/findById?userId=7227619999906416263&id=6403945117280157140
               /* if (info.getBaoYou() != 1) {
                    iv_kd.setVisibility(View.GONE);
                    tv_kd.setVisibility(View.VISIBLE);
                    tv_kd.setText("快递费:" + info.getYouPrice());
                }*/

                if (info.getCollection() != null && MyApplication.getInstan().getUser() != null) {
                    Collection cl = info.getCollection();
                    if (cl.getUserId().equals(MyApplication.getInstan().getUser().getData().getId() + "")) {
                        isColl = 1;
                        Drawable drawable = getApplicationContext().getResources().getDrawable(R.drawable.scl);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());//必须设置图片大小，否则不显示
                        sc.setCompoundDrawables(null, drawable, null, null);
                    }else{
                        isColl=0;
                    }
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
                        ImageView view = new ImageView(EatInfoActivity.this);
                        view.setBackgroundResource(R.drawable.dot_normal);
                        LinearLayout.LayoutParams viewL = new LinearLayout.LayoutParams(10, 10);
                        viewL.setMargins(5, 0, 5, 0);
                        view.setLayoutParams(viewL);
                        dots.add(view);
                        dot_layout.addView(view);
                    }
                    eateAdapter = new EateInfoAotuAdapter(EatInfoActivity.this, adList);
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
                Intent it = new Intent(EatInfoActivity.this, CommentsActivity.class);
                it.putExtra("id", id);
                startActivity(it);


            }
        });


        //getContent();


        eateAdapter = new EateInfoAotuAdapter(this, adList);
        eatVpone.setAdapter(eateAdapter);// 设置填充ViewPager页面的适配器
        // 设置一个监听器，当ViewPager中的页面改变时调用
        eatVpone.setOnPageChangeListener(new MyPageChangeListener());


        //分享
       tv_right_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectPicPopupWindowShare menuWindow = new SelectPicPopupWindowShare(EatInfoActivity.this, umShareListener);
                //显示窗口
                menuWindow.showAtLocation(eat_layout, Gravity.BOTTOM |
                        Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置

            }
        });
    }
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(EatInfoActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(EatInfoActivity.this, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(EatInfoActivity.this, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };
    @Override
    protected void onResume() {
        super.onResume();

        getShoppingInfo();
        startAd();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_sl, R.id.tv_sc, R.id.shopgg, R.id.shopme, R.id.pllb})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pllb://全部评论vbreak;
                intent.setClass(EatInfoActivity.this, AllContentActivity.class);
                intent.putExtra("id", id + "");
                startActivity(intent);


            case R.id.tv_sl://私聊
                if (MyApplication.getInstan().getUser() == null || MyApplication.getInstan().getUser().getData().getId() == 0) {
                    intent.putExtra("tag", "finsh");
                    intent.setClass(EatInfoActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {

                }
                break;
            case R.id.tv_sc://收藏
                if (MyApplication.getInstan().getUser() == null || MyApplication.getInstan().getUser().getData().getId() == 0) {
                    intent.putExtra("tag", "finsh");
                    intent.setClass(EatInfoActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    if (isColl==0) {
                        Schouchang();
                    } else {
                        ChanSchouchang();
                    }
                }
                break;

            case R.id.shopgg: //店铺
                if (MyApplication.getInstan().getUser() == null || MyApplication.getInstan().getUser().getData().getId() == 0) {
                    intent.putExtra("tag", "finsh");
                    intent.setClass(EatInfoActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    intent.setClass(EatInfoActivity.this, EatShopInfoActivity.class);
                    intent.putExtra("id", shopID);
                    intent.putExtra("name", name);
                    startActivity(intent);
                }

                break;

            case R.id.shopme://订单
                if (MyApplication.getInstan().getUser() == null || MyApplication.getInstan().getUser().getData().getId() == 0) {
                    intent.putExtra("tag", "finsh");
                    intent.setClass(EatInfoActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    ArrayList<ShopInfoListBean> carLiat = new ArrayList<>();
                    ShopInfoListBean cb = new ShopInfoListBean();
                    cb.setShopId(Long.valueOf(shopID));
                    cb.setName(shoppingName.getText().toString());
                    cb.setCurrNum(1);
                    cb.setPrice(Qpic);
                    carLiat.add(cb);
                    Intent it = new Intent(EatInfoActivity.this, ShoppingDDCarActivity.class);
                    it.putExtra("id", shopID);
                    it.putExtra("car", (Serializable) carLiat);
                    startActivity(it);


                    /*intent.setClass(EatInfoActivity.this, ShoppingDDActivity.class);
                    intent.putExtra("id", id);
                    intent.putExtra("info",info);
                    startActivity(intent);*/
                  /*  intent.setClass(EatInfoActivity.this, EatShopInfoActivity.class);
                    intent.putExtra("id", shopID);
                    intent.putExtra("name", name);
                    startActivity(intent);*/
                }

                break;

        }
    }

    private void Schouchang() {
        AjaxParams map = new AjaxParams();
        map.put("goodsId", id);
        map.put("userId", MyApplication.getInstan().getUser().getData().getId() + "");
        DD.v("收藏商品:" + Contantor.main_ShppingSave+"?"+map.toString());
        new FinalHttp().post(Contantor.main_ShppingSave, map, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                try {
                    JSONObject js = new JSONObject(s);
                    DD.v("收藏商品:" + s);
                    if (js.getBoolean("success")) {
                        //  TS.shortTime("收藏成功");

                        isColl=1;
                        Drawable drawable = getApplicationContext().getResources().getDrawable(R.drawable.scl);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());//必须设置图片大小，否则不显示
                        sc.setCompoundDrawables(null, drawable, null, null);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }
        });
    }private void ChanSchouchang() {
        AjaxParams map = new AjaxParams();
        map.put("Id", id);
        DD.v("shan收藏商品:" + Contantor.delete+"?"+map.toString());
       // map.put("userId", MyApplication.getInstan().getUser().getData().getId() + "");
        new FinalHttp().post(Contantor.delete, map, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                try {
                    JSONObject js = new JSONObject(s);
                    DD.v("取消收藏商品:" + s);
                    if (js.getBoolean("success")) {
                        //  TS.shortTime("收藏成功");
                        isColl=0;
                        Drawable drawable = getApplicationContext().getResources().getDrawable(R.drawable.shoucangs);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());//必须设置图片大小，否则不显示
                        sc.setCompoundDrawables(null, drawable, null, null);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }
        });
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
