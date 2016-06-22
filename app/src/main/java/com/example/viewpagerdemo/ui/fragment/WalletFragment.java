package com.example.viewpagerdemo.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.example.viewpagerdemo.ui.MyApplication;
import com.example.viewpagerdemo.ui.activity.ErWMain2Activity;
import com.example.viewpagerdemo.ui.activity.WakketZhuangZActivity;
import com.example.viewpagerdemo.ui.bean.QbaoBean;
import com.example.viewpagerdemo.ui.jlfragmenwork.Contantor;
import com.example.viewpagerdemo.ui.jlfragmenwork.actvity.LoginActivity;
import com.example.viewpagerdemo.ui.jlfragmenwork.adpter.FragmentArrayPageAdapter;
import com.example.viewpagerdemo.ui.jlfragmenwork.basefregmetwork.JLBaseFragment;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.SelectFKPopupWindow;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.SelectPicPopupWindow2;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.Tools;
import com.example.viewpagerdemo.ui.jlfragmenwork.view.PagerSlidingTabStrip;
import com.example.viewpagerdemo.ui.jlfragmenwork.view.WhiteTabStripController;
import com.example.viewpagerdemo.ui.jlfragmenwork.view.WhiteTabViewFactory;
import com.xingkesi.foodapp.R;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.io.File;

import butterknife.Bind;


/**
 * 钱包
 */
public class WalletFragment extends JLBaseFragment {

    /* @Bind(R.id.tabStrip_viewPagerFragment_tabs)
     PagerSlidingTabStrip pagerSlidingTabStrip;
     @Bind(R.id.pager_viewPagerFragment_content)
     ViewPager viewPager;*/
    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.balancetv)
    TextView balancetv;
    @Bind(R.id.tv_right_text)
    TextView tv_right_text;
    @Bind(R.id.iv_right_image)
    ImageView iv_right_image;

    @Bind(R.id._add_log)
    Button _add_log;
    @Bind(R.id.noLoa)
    LinearLayout noLoa;
    @Bind(R.id.qbfk)
    LinearLayout qbfk;
    @Bind(R.id.layouts)
    LinearLayout layouts;
    @Bind(R.id.sk)
    LinearLayout sk;
    @Bind(R.id.ll_base_title)
    RelativeLayout ll_base_title;

    SelectFKPopupWindow menuWindow;

    @Bind(R.id.iv_back)
    ImageView iv_back;


    @Override
    public int setViewLayout() {
        return R.layout.activity_tab_friend;
    }


    @Override
    public void InitObject() {
        iv_back.setVisibility(View.GONE);
        iv_right_image.setVisibility(View.GONE);
        tv_title.setText("钱包");

        if (MyApplication.getInstan().getUser() != null && MyApplication.getInstan().getUser().getData().getThinksId() != null) {
            tv_title.setTextColor(getResources().getColor(R.color.waiter));
            ll_base_title.setBackgroundColor(getResources().getColor(R.color.logding_bg));
            noLoa.setVisibility(View.GONE);
        } else {
            noLoa.setVisibility(View.INVISIBLE);
            tv_title.setTextColor(getResources().getColor(R.color.logding_bg));
            ll_base_title.setBackgroundColor(getResources().getColor(R.color.waiter));
        }


       /* OrderChildPage[] softwareChildPages = OrderChildPage.values();
        String[] tabNames = new String[softwareChildPages.length];
        Fragment[] fragments = new Fragment[softwareChildPages.length];
        int w = 0;
        for (OrderChildPage softwareChildPage : softwareChildPages) {
            tabNames[w] = softwareChildPage.getTabName();
            fragments[w] = softwareChildPage.buildFragment();
            w++;
        }*/
        //  tabViewFactory = new WhiteTabViewFactory(getActivity(), tabNames);
        //  pageAdapter = new FragmentArrayPageAdapter(getChildFragmentManager(), fragments);
//        tabStripController = new WhiteTabStripController(getActivity(), pagerSlidingTabStrip);
//        pagerSlidingTabStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                defaultChildPage = OrderChildPage.values()[position];
//            }
//        });

        /*viewPager.setAdapter(pageAdapter);
        viewPager.setCurrentItem(defaultChildPage.getPageIndex());
        viewPager.setOffscreenPageLimit(viewPager.getAdapter().getCount());*/
        _add_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("tag", "finsh");
                intent.setClass(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        qbfk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyApplication.getInstan().getUser() == null || MyApplication.getInstan().getUser().getData().getId() == 0) {
                    Intent intent = new Intent();
                    intent.putExtra("tag", "finsh");
                    intent.setClass(getActivity(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    menuWindow = new SelectFKPopupWindow(getActivity(), itemsOnClick, "zhutu");
                    //显示窗口
                    menuWindow.showAtLocation(layouts, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
                }
            }
        });


        //付款
        sk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ErWMain2Activity.class));
            }
        });


        //tabStripController.resetSkin();
        /*pagerSlidingTabStrip.setTabViewFactory(tabViewFactory);
        pagerSlidingTabStrip.setViewPager(viewPager);*/


    }

    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {

        public void onClick(View v) {
            menuWindow.dismiss();
            switch (v.getId()) {
                //二维码
                case R.id.erweima:

                    break;
                //账号
                case R.id.zhanghao:
                    startActivity(new Intent(getActivity(), WakketZhuangZActivity.class));
                    break;
                default:
                    break;
            }
        }

    };

    @Override
    public void onResume() {
        super.onResume();
        DD.v("179---------------------------");
        if (MyApplication.getInstan().getUser() != null && MyApplication.getInstan().getUser().getData().getThinksId() != null) {
            tv_title.setTextColor(getResources().getColor(R.color.waiter));
            ll_base_title.setBackgroundColor(getResources().getColor(R.color.logding_bg));
            getQBInfo();
            noLoa.setVisibility(View.GONE);
        } else {
            noLoa.setVisibility(View.INVISIBLE);
            tv_title.setTextColor(getResources().getColor(R.color.logding_bg));
            ll_base_title.setBackgroundColor(getResources().getColor(R.color.waiter));
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
        //可见时加载数据相当于Fragment的onResume
            DD.e("setUserVisibleHint============看得见");
            if (MyApplication.getInstan().getUser() != null && MyApplication.getInstan().getUser().getData().getThinksId() != null) {
               // tv_title.setTextColor(getResources().getColor(R.color.waiter));
               // ll_base_title.setBackgroundColor(getResources().getColor(R.color.logding_bg));
                getQBInfo();
            }
        } else {
            //不可见是不加载数据
            DD.e("setUserVisibleHint============看不见");
        }
    }

    void getQBInfo() {
        String url = Contantor.qianbao;
        AjaxParams ap = new AjaxParams();
        ap.put("thinksId", MyApplication.getInstan().getUser().getData().getThinksId());
        DD.d("钱包申请:" + url + "?" + ap.toString());
        new FinalHttp().post(url, ap, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                DD.d("钱包申请s:" + s);
                if (s == null || s.equals("") || s.equals(null)) {
                    return;
                }
                QbaoBean qb = JSONObject.parseObject(s, QbaoBean.class);

                if (qb != null && qb.getState().equals("true")) {
                    String balance = qb.getData().getBalance();
                    balancetv.setText(balance);
                }
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        DD.v("219---------------------------");

    }

   /* public enum OrderChildPage {
        NOT_FINISH(0, "收款") {
            @Override
            public Fragment buildFragment() {
                return new Wallet_IncomeFragment();
            }
        },

        FINISHED(1, "付款") {
            @Override
            public Fragment buildFragment() {
                return new Wallet_OutcomeFragment();
            }
        };

        private int pageIndex;
        private String tabName;

        OrderChildPage(int pageIndex, String tabName) {
            this.pageIndex = pageIndex;
            this.tabName = tabName;
        }

        public int getPageIndex() {
            return pageIndex;
        }

        public String getTabName() {
            return tabName;
        }

        public abstract Fragment buildFragment();
    }*/
}
