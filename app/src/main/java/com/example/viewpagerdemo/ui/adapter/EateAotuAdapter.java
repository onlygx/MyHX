package com.example.viewpagerdemo.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.viewpagerdemo.ui.bean.ShoppingListBanerBean;
import com.example.viewpagerdemo.ui.activity.EatInfoActivity;
import com.example.viewpagerdemo.ui.jlfragmenwork.Contantor;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.TS;
import com.squareup.picasso.Picasso;
import com.xingkesi.foodapp.R;

import java.util.List;

/**
 * 商品轮播图
 */
public class EateAotuAdapter extends PagerAdapter {

    Context context;
    List<ShoppingListBanerBean> list;
    String idlist,name;
    public EateAotuAdapter(Context context, List<ShoppingListBanerBean> list, String idlist,String name){
        this.context=context;
        this.list=list;
        this.idlist=idlist;
        this.name=name;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iv = new ImageView(context);
        ((ViewPager) container).addView(iv);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        //final AdDomain adDomain = adList.get(position);
        String url =list.get(position).getUrl();
         Picasso.with(context).load(Contantor.Imagepost+url).
                placeholder(R.drawable.logding)
                .error(R.drawable.dialogpop_bg2).into(iv);
        // 在这个方法里面设置图片的点击事件
        iv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 处理跳转逻辑
                TS.shortTime("单机了图片:"+idlist+"=="+name);
                Intent it =new Intent(context, EatInfoActivity.class);
                it.putExtra("id",idlist+"");
                it.putExtra("name",name+"");
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
