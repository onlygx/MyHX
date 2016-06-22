package com.example.viewpagerdemo.ui.jlfragmenwork.actvity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.viewpagerdemo.ui.jlfragmenwork.baseactivitywork.JLBaseActivity;
import com.xingkesi.foodapp.R;

/**
 * 引导页
 */
public class NavigationActivity extends JLBaseActivity {
    private static final int[] imgs = new int[]{R.drawable.startup, R.drawable.startup};
    private ViewPager viewPager ;
    private TextView goTextView;



    @Override
    public int setViewLayout() {
        return R.layout.activity_navigation;
    }

    @Override
    public void initObject() {
        super.initObject();
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        viewPager.setAdapter(new MyAdapter());
    }

    @Override
    public void initID() {
        super.initID();
        goTextView = (TextView)findViewById(R.id.go);


    }

    public class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imgs.length;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            FrameLayout frameLayout = new FrameLayout(NavigationActivity.this);
            ImageView photoView = new ImageView(NavigationActivity.this);
            try{
                photoView.setImageResource(imgs[position]);
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
                photoView.setLayoutParams(params);
                photoView.setScaleType(ImageView.ScaleType.FIT_XY);
                frameLayout.addView(photoView);

                if(position == imgs.length-1){
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(windowWidth, windowHeight);
//                    layoutParams.setMargins(0,0,0, DenisyUtil.dip2px(60));
                    layoutParams.gravity = Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM;
                    TextView textView = new TextView(NavigationActivity.this);
                    textView.setGravity(Gravity.CENTER);
//                    textView.setBackgroundResource(R.mipmap.rectangle);
//                    textView.setText("立即体验");
//                    textView.setTextColor(getResourcesColor(R.color.main_color));
                    textView.setClickable(true);
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            goMain();
                        }
                    });
                    textView.setLayoutParams(layoutParams);
                    frameLayout.addView(textView);
                }

                container.addView(frameLayout, ViewPager.LayoutParams.MATCH_PARENT,
                        ViewPager.LayoutParams.MATCH_PARENT);
            }catch(Exception e){}

            return frameLayout;
        }
    }

    public void goMain(){
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("tag","logding");
        startActivity(intent);
        finish();
    }

}
