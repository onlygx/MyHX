package com.example.viewpagerdemo.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.viewpagerdemo.ui.activity.EatInfoActivity;
import com.example.viewpagerdemo.ui.bean.EatOneBaen;
import com.example.viewpagerdemo.ui.Contantor;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.squareup.picasso.Picasso;
import com.xingkesi.foodapp.R;

import java.util.List;

/**
 * Created by Administrator on 2016/5/20.
 */
public class EateMainAotuAdapter extends PagerAdapter {

    Context context;
    List<EatOneBaen> list;

    public EateMainAotuAdapter(Context context, List<EatOneBaen> list) {
        this.context = context;
        this.list = list;

    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        EatOneBaen eob = list.get(position);
        final String id = eob.getInfo();
        ImageView iv = new ImageView(context);
        ((ViewPager) container).addView(iv);
        // iv.setImageResource(R.drawable.eatone);
        String url = Contantor.Imagepost + eob.getUrl();
        // DD.v("Banner图片显示:"+url);
        Picasso.with(context).load(url).placeholder(R.drawable.aliwx_default_photo_right)
                .error(R.drawable.aliwx_fail_photo_right).into(iv);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        //final AdDomain adDomain = adList.get(position);
        // 在这个方法里面设置图片的点击事件
        iv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 处理跳转逻辑
                // TS.shortTime("单机了图片");
                //商品详情

                DD.v("单机了："  +id);
                Intent it = new Intent(context, EatInfoActivity.class);
                it.putExtra("id", id + "");
                it.putExtra("shopID",  "");
                it.putExtra("name",  "商品详情");
                context.startActivity(it);
            }
        });
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
