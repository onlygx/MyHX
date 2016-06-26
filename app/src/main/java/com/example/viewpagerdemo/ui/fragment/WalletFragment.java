package com.example.viewpagerdemo.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.example.viewpagerdemo.ui.adapter.WalletIncomeReclerViewAdpter;
import com.example.viewpagerdemo.ui.bean.QbaoBean;
import com.example.viewpagerdemo.ui.Contantor;
import com.example.viewpagerdemo.ui.bean.WallBean;
import com.example.viewpagerdemo.ui.bean.WallDailtBean;
import com.example.viewpagerdemo.ui.bean.Wallet_IncomeBean;
import com.example.viewpagerdemo.ui.jlfragmenwork.actvity.LoginActivity;
import com.example.viewpagerdemo.ui.jlfragmenwork.basefregmetwork.JLBaseFragment;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.SelectFKPopupWindow;
import com.karics.library.zxing.android.CaptureActivity;
import com.xingkesi.foodapp.R;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Random;

import butterknife.Bind;


/**
 * 钱包
 */
public class WalletFragment extends JLBaseFragment {

    /* @Bind(R.id.tabStrip_viewPagerFragment_tabs)
     PagerSlidingTabStrip pagerSlidingTabStrip;
     @Bind(R.id.pager_viewPagerFragment_content)
     ViewPager viewPager;*/
    private static final int REQUEST_CODE_SCAN = 0x0000;

    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";
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
    RelativeLayout layouts;
    @Bind(R.id.sk)
    LinearLayout sk;

    SelectFKPopupWindow menuWindow;

    @Bind(R.id.iv_back)
    ImageView iv_back;

    @Bind(R.id.wallet_incomerv)
    RecyclerView wallet_incomerv;

    ArrayList<WallBean> list;
    WalletIncomeReclerViewAdpter adpter;



    @Override
    public int setViewLayout() {
        return R.layout.activity_tab_friend;
    }

    //----------测试数据-----------
    ArrayList<Wallet_IncomeBean> TestData() {
        ArrayList<Wallet_IncomeBean> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Wallet_IncomeBean wb = new Wallet_IncomeBean();
            wb.setComeId(i + "");
            wb.setComeName("新疆苹果");
            Random tan = new Random();
            int ran = tan.nextInt(15);
            int money = ran + 50 + i;
            wb.setComeMoney(money + "");
            list.add(wb);
        }

        return list;


    }

    @Override
    public void InitObject() {
        iv_back.setVisibility(View.GONE);
        iv_right_image.setVisibility(View.GONE);
        tv_title.setText("钱包");

        list = new ArrayList<>();
        wallet_incomerv.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        wallet_incomerv.setLayoutManager(manager);
        adpter = new WalletIncomeReclerViewAdpter(getActivity(), list);
        wallet_incomerv.setAdapter(adpter);


        //登录状态
        if (MyApplication.getInstan().getUser() != null && MyApplication.getInstan().getUser().getData().getThinksId() != null) {
            noLoa.setVisibility(View.GONE);
        } else {
            noLoa.setVisibility(View.VISIBLE);
        }

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
                if (MyApplication.getInstan().getUser() == null || MyApplication.getInstan().getUser().getData().getId() == 0) {
                    Intent intent = new Intent();
                    intent.putExtra("tag", "finsh");
                    intent.setClass(getActivity(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    startActivity(new Intent(getActivity(), ErWMain2Activity.class));
                }
            }
        });


        //tabStripController.resetSkin();
        /*pagerSlidingTabStrip.setTabViewFactory(tabViewFactory);
        pagerSlidingTabStrip.setViewPager(viewPager);*/


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SCAN && resultCode == getActivity().RESULT_OK) {
            if (data != null) {

                String content = data.getStringExtra(DECODED_CONTENT_KEY);
                // Bitmap bitmap = data.getParcelableExtra(DECODED_BITMAP_KEY);

                Intent it = new Intent(getActivity(), WakketZhuangZActivity.class);

                it.putExtra("tag", "no");
                it.putExtra("mo", content);
                startActivity(it);

            }
        }
    }

    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {

        public void onClick(View v) {
            menuWindow.dismiss();
            switch (v.getId()) {
                //二维码
                case R.id.erweima:
                    Intent intent = new Intent(getActivity(),
                            CaptureActivity.class);
                    startActivityForResult(intent, REQUEST_CODE_SCAN);
                    break;
                //账号
                case R.id.zhanghao:
                    Intent it = new Intent(getActivity(), WakketZhuangZActivity.class);
                    it.putExtra("tag", "yes");
                    startActivity(it);
                    break;
                default:
                    break;
            }
        }

    };

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //可见时加载数据相当于Fragment的onResume
            if (MyApplication.getInstan().getUser() != null && MyApplication.getInstan().getUser().getData().getThinksId() != null) {
                DD.e("钱============见");
                getQBInfo();
            }
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

                try {
                    org.json.JSONObject js=new org.json.JSONObject(s);
                    org.json.JSONObject data=js.getJSONObject("data");
                    JSONArray ld=data.getJSONArray("detail");
                    for(int i=0;i<ld.length();i++){
                        org.json.JSONObject jb=ld.getJSONObject(i);
                        String lai=jb.getString("来源");
                        WallBean wb =new WallBean();
                        wb.set来源(lai);
                        wb.set备注(jb.getString("备注"));
                        wb.set时间(jb.getLong("时间"));
                        wb.set类型(jb.getString("类型"));
                        wb.set编号(jb.getString("编号"));
                        wb.set金额(jb.getString("金额"));
                        list.add(wb);
                    }

                    balancetv.setText(data.getString("balance"));
                    adpter = new WalletIncomeReclerViewAdpter(getActivity(), list);
                    wallet_incomerv.setAdapter(adpter);
                    adpter.notifyDataSetChanged();
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

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("x", "--------------------Onresume");
        //登录状态
        if (MyApplication.getInstan().getUser() != null && MyApplication.getInstan().getUser().getData().getThinksId() != null) {
            noLoa.setVisibility(View.GONE);
        } else {
            noLoa.setVisibility(View.VISIBLE);
        }
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
