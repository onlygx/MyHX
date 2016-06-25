package com.example.viewpagerdemo.ui.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.example.viewpagerdemo.ui.MyApplication;
import com.example.viewpagerdemo.ui.activity.ListTopClassActivity;
import com.example.viewpagerdemo.ui.activity.OhterListMainActivity;
import com.example.viewpagerdemo.ui.jlfragmenwork.adpter.FragmentArrayPageAdapter;
import com.example.viewpagerdemo.ui.jlfragmenwork.basefregmetwork.JLBaseFragment;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.MyDialog;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.TS;
import com.example.viewpagerdemo.ui.jlfragmenwork.view.PagerSlidingTabStrip;
import com.example.viewpagerdemo.ui.jlfragmenwork.view.WhiteTabStripController;
import com.example.viewpagerdemo.ui.jlfragmenwork.view.WhiteTabViewFactory;
import com.example.viewpagerdemo.ui.searchfrend.CityListActivity;
import com.mining.app.ScanMainActivity;
import com.xingkesi.foodapp.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeFragment extends JLBaseFragment {

    int CurPage = 0;

    @Bind(R.id.tabStrip_viewPagerFragment_tabs)
    PagerSlidingTabStrip pagerSlidingTabStrip;
    @Bind(R.id.pager_viewPagerFragment_content)
    ViewPager viewPager;
    @Bind(R.id.tv_left_text)
    TextView tv_left_text;

    @Bind(R.id.showOthoer)
    TextView showOthoer;
    @Bind(R.id.iv_right_image)
    ImageView iv_right_image;


    private FragmentArrayPageAdapter pageAdapter;
    WhiteTabViewFactory tabViewFactory;
    private WhiteTabStripController tabStripController;
    OrderChildPage defaultChildPage = OrderChildPage.NOT_FINISH;

    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();

    @Override
    public int setViewLayout() {
        Log.d("LD", "HomeFragment---------------");
        return R.layout.activity_tab_chat;
    }


    @Override
    public void InitObject() {
        ButterKnife.bind(getActivity());

        mLocationClient = new LocationClient(getActivity());     //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);    //注册监听函数
        initLocation();
        if (mLocationClient != null) {
            mLocationClient.start();
        }


        //首页放大镜
        iv_right_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(getActivity(), CityListActivity.class));
            }
        });

        OrderChildPage[] softwareChildPages = OrderChildPage.values();
        String[] tabNames = new String[softwareChildPages.length];
        Fragment[] fragments = new Fragment[softwareChildPages.length];
        int w = 0;
        for (OrderChildPage softwareChildPage : softwareChildPages) {
            tabNames[w] = softwareChildPage.getTabName();
            fragments[w] = softwareChildPage.buildFragment();
            w++;
        }
        tabViewFactory = new WhiteTabViewFactory(getActivity(), tabNames);
        pageAdapter = new FragmentArrayPageAdapter(getChildFragmentManager(), fragments);
        tabStripController = new WhiteTabStripController(getActivity(), pagerSlidingTabStrip);
        pagerSlidingTabStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }
            @Override
            public void onPageScrollStateChanged(int state) {}
            @Override
            public void onPageSelected(int position) {
                defaultChildPage = OrderChildPage.values()[position];
            }
        });
        viewPager.setAdapter(pageAdapter);
        viewPager.setCurrentItem(defaultChildPage.getPageIndex());
        viewPager.setOffscreenPageLimit(viewPager.getAdapter().getCount());
        tabStripController.resetSkin();
        pagerSlidingTabStrip.setTabViewFactory(tabViewFactory);
        pagerSlidingTabStrip.setViewPager(viewPager);


        showOthoer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ListTopClassActivity.class));
            }
        });
    }

    public enum OrderChildPage {
        NOT_FINISH(0, "商品") {
            @Override
            public Fragment buildFragment() {
                return new HomeeateListFragment();
            }
        },

        FINISHED(1, "需求") {
            @Override
            public Fragment buildFragment() {
                return new HomeFruitListFragment();
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


    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            //Receive Location
            String city = null;
            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());
            sb.append("\nradius : ");
            sb.append(location.getRadius());
            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                /*sb.append("\nspeed : ");
                sb.append(location.getSpeed());// 单位：公里每小时
                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());
                sb.append("\nheight : ");
                sb.append(location.getAltitude());// 单位：米
                sb.append("\ndirection : ");
                sb.append(location.getDirection());// 单位度
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                sb.append("\ndescribe : ");
                sb.append("gps定位成功");*/
                city = location.getCity();
                MyApplication.getInstan().setSheng(location.getProvince());
                MyApplication.getInstan().setCity(location.getCity());
                MyApplication.getInstan().setQu(location.getDistrict());
                // DD.d("定位了："+location.getAddress().address);
                MyApplication.getInstan().setAddress(location.getAddress().address);

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                /*sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                //运营商信息
                sb.append("\noperationers : ");
                sb.append(location.getOperators());
                sb.append("\ndescribe : ");
                sb.append("网络定位成功");*/
                city = location.getCity();
                MyApplication.getInstan().setSheng(location.getProvince());
                MyApplication.getInstan().setCity(location.getCity());
                MyApplication.getInstan().setQu(location.getDistrict());
                MyApplication.getInstan().setAddress(location.getAddress().address);
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
               /* sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");*/
                city = location.getCity();
                MyApplication.getInstan().setSheng(location.getProvince());
                MyApplication.getInstan().setCity(location.getCity());
                MyApplication.getInstan().setQu(location.getDistrict());
                MyApplication.getInstan().setAddress(location.getAddress().address);
            } else if (location.getLocType() == BDLocation.TypeServerError) {

               /* sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");*/
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
               /* sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");*/
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
              /*  sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");*/
            }
            sb.append("\nlocationdescribe : ");
            sb.append(location.getLocationDescribe());// 位置语义化信息
            List<Poi> list = location.getPoiList();// POI数据
            if (list != null) {
                sb.append("\npoilist size = : ");
                sb.append(list.size());
                for (Poi p : list) {
                    sb.append("\npoi= : ");
                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                }
            }
           // DD.i("定位" + city);
            tv_left_text.setText(city);


        }
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 5 * 1000 * 60;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }


}
