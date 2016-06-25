package com.example.viewpagerdemo.ui.adapter;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.viewpagerdemo.ui.bean.EatInfoOneBaen;
import com.example.viewpagerdemo.ui.Contantor;
import com.squareup.picasso.Picasso;
import com.xingkesi.foodapp.R;

import java.util.List;

/**
 * Created by Administrator on 2016/5/20.
 */
public class EateInfoAotuAdapter extends PagerAdapter {

    Context context;
    List<EatInfoOneBaen> list;

    public EateInfoAotuAdapter(Context context, List<EatInfoOneBaen> list) {
        this.context = context;
        this.list = list;

    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iv = new ImageView(context);
        ((ViewPager) container).addView(iv);
        EatInfoOneBaen one = list.get(position);
        Picasso.with(context).load(Contantor.Imagepost + one.getUrl()).
                placeholder(R.drawable.logding)
                .error(R.drawable.logding).into(iv);
       // iv.setImageResource(R.drawable.eatone);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        //final AdDomain adDomain = adList.get(position);
        // 在这个方法里面设置图片的点击事件
        /*iv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 处理跳转逻辑
                TS.shortTime("单机了图片");
            }
        });*/
        return iv;
    }

    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView((View) arg2);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {

    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void startUpdate(View arg0) {

    }

    @Override
    public void finishUpdate(View arg0) {

    }
}
