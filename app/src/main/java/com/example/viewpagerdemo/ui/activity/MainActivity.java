package com.example.viewpagerdemo.ui.activity;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.example.viewpagerdemo.ui.MyApplication;
import com.example.viewpagerdemo.ui.adapter.FragmentAdapter;
import com.example.viewpagerdemo.ui.fragment.AddressBookFragment;
import com.example.viewpagerdemo.ui.fragment.FindFragment;
import com.example.viewpagerdemo.ui.fragment.HomeFragment;
import com.example.viewpagerdemo.ui.fragment.WalletFragment;
import com.example.viewpagerdemo.ui.jlfragmenwork.baseactivitywork.JLBaseFragmentActivity;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.Tools;
import com.xingkesi.foodapp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends JLBaseFragmentActivity {

    @Bind(R.id.id_page_vp)
    ViewPager mPageVp;


    /**
     * Tab显示内容TextView
     */
    @Bind(R.id.id_friend_tv)
    TextView mTabFriendTv;
    @Bind(R.id.id_chat_tv)
    TextView mTabChatTv;
    @Bind(R.id.id_contacts_tv)
    TextView mTabContactsTv;
    @Bind(R.id.id_friend_tv2)
    TextView id_friend_tv2;

    @Bind(R.id.home_iv)
    ImageView home_iv;
    @Bind(R.id.money_iv)
    ImageView money_iv;
    @Bind(R.id.addr_iv)
    ImageView addr_iv;
    @Bind(R.id.find_iv)
    ImageView find_iv;


    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private FragmentAdapter mFragmentAdapter;


    /**
     * Tab的那个引导线
     */
    // private ImageView mTabLineIv;
    /**
     * Fragment
     */
    private HomeFragment homeFragment;//首页
    private WalletFragment walletFragment;
    private AddressBookFragment addressBookFragment;
    private FindFragment findFragment;
    /**
     * ViewPager的当前选中页
     */
    private int currentIndex;





    @Override
    public int setViewLayout() {
        Log.d("LD","Main--------setViewLayout---------");
        return R.layout.activity_main;
    }


    @Override
    public void initObject() {
        Log.d("LD","Main--------initObject---------");
        init();
        initTabLineWidth();
    }




    private void init() {

        SharedPreferences sf =this.getSharedPreferences("start",0);
        sf.edit().putInt("start",1).commit();


        walletFragment = new WalletFragment();
        addressBookFragment = new AddressBookFragment();
        findFragment = new FindFragment();
        homeFragment = new HomeFragment();
        mFragmentList.add(homeFragment);
        mFragmentList.add(walletFragment);
        mFragmentList.add(addressBookFragment);
        mFragmentList.add(findFragment);

        mFragmentAdapter = new FragmentAdapter(
                this.getSupportFragmentManager(), mFragmentList);
        mPageVp.setAdapter(mFragmentAdapter);
        mPageVp.setCurrentItem(0);
       // mPageVp.setOffscreenPageLimit(1);

        mPageVp.setOnPageChangeListener(new OnPageChangeListener() {

            /**
             * state滑动中的状态 有三种状态（0，1，2） 1：正在滑动 2：滑动完毕 0：什么都没做。
             */
            @Override
            public void onPageScrollStateChanged(int state) {

            }

            /**
             * position :当前页面，及你点击滑动的页面 offset:当前页面偏移的百分比
             * offsetPixels:当前页面偏移的像素位置
             */
            @Override
            public void onPageScrolled(int position, float offset,
                                       int offsetPixels) {
               /* LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv.getLayoutParams();
                Log.e("offset:", offset + "");
                *//**
                 * 利用currentIndex(当前所在页面)和position(下一个页面)以及offset来
                 * 设置mTabLineIv的左边距 滑动场景：
                 * 记3个页面,
                 * 从左到右分别为0,1,2
                 * 0->1; 1->2; 2->1; 1->0
                 *//*

                if (currentIndex == 0 && position == 0)// 0->1
                {
                    lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));

                } else if (currentIndex == 1 && position == 0) // 1->0
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));

                } else if (currentIndex == 1 && position == 1) // 1->2
                {
                    lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));
                } else if (currentIndex == 2 && position == 1) // 2->1
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));
                }
                mTabLineIv.setLayoutParams(lp);*/
            }

            @Override
            public void onPageSelected(int position) {
                resetTextView();
                switch (position) {
                    case 0:
                        mTabChatTv.setTextColor(getResources().getColor(R.color.logding_bg));
                        home_iv.setImageResource(R.drawable.home_p);

                        break;
                    case 1:
                        mTabFriendTv.setTextColor(getResources().getColor(R.color.logding_bg));
                        money_iv.setImageResource(R.drawable.money_p);

                        break;
                    case 2:
                        mTabContactsTv.setTextColor(getResources().getColor(R.color.logding_bg));
                        addr_iv.setImageResource(R.drawable.addr_p);

                        break;
                    case 3:
                        id_friend_tv2.setTextColor(getResources().getColor(R.color.logding_bg));
                        find_iv.setImageResource(R.drawable.find_p);
                        break;
                }
                currentIndex = position;
                MyApplication.postion=currentIndex;
            }
        });

    }

    /**
     * 设置滑动条的宽度为屏幕的1/3(根据Tab的个数而定)
     */
    private void initTabLineWidth() {
       /* DisplayMetrics dpMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay()
                .getMetrics(dpMetrics);
        screenWidth = dpMetrics.widthPixels;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
                .getLayoutParams();
        lp.width = screenWidth / 3;
        mTabLineIv.setLayoutParams(lp);*/

    }

    /**
     * 重置颜色
     */
    private void resetTextView() {
        home_iv.setImageResource(R.drawable.home_n);
        money_iv.setImageResource(R.drawable.money_n);
        addr_iv.setImageResource(R.drawable.addr_n);
        find_iv.setImageResource(R.drawable.find_n);
        mTabChatTv.setTextColor(getResources().getColor(R.color.main_text));
        mTabFriendTv.setTextColor(getResources().getColor(R.color.main_text));
        mTabContactsTv.setTextColor(getResources().getColor(R.color.main_text));
        id_friend_tv2.setTextColor(getResources().getColor(R.color.main_text));

    }


    @OnClick({R.id.id_tab_chat_ll, R.id.id_tab_friend_ll, R.id.id_tab_contacts_ll, R.id.id_tab_friend_ll2})
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.id_tab_chat_ll:
                if (currentIndex == 0) {
                    return;
                }
                mPageVp.setCurrentItem(0);
                break;

            case R.id.id_tab_friend_ll:
                if (currentIndex == 1) {
                    return;
                }
                mPageVp.setCurrentItem(1);
                break;

            case R.id.id_tab_contacts_ll:
                if (currentIndex == 2) {
                    return;
                }
                mPageVp.setCurrentItem(2);
                break;

            case R.id.id_tab_friend_ll2:
                if (currentIndex == 3) {
                    return;
                }
                mPageVp.setCurrentItem(3);
                break;
        }

    }




}
