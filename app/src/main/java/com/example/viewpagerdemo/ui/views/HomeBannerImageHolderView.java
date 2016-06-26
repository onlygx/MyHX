package com.example.viewpagerdemo.ui.views;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.example.viewpagerdemo.ui.Contantor;
import com.example.viewpagerdemo.ui.bean.ShoppingListBanerBean;
import com.example.viewpagerdemo.ui.views.banner.Holder;
import com.squareup.picasso.Picasso;
import com.xingkesi.foodapp.R;


/**
 * 网络图片加载例子
 * Created by dingwei on 2015/12/2
 */
public class HomeBannerImageHolderView implements Holder<ShoppingListBanerBean> {
    private ImageView imageView;
    @Override
    public View createView(Context context) {
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context,int position, ShoppingListBanerBean data) {
        Picasso.with(context).load(Contantor.Imagepost+data.getUrl()).
                placeholder(R.drawable.logding)
                .error(R.drawable.dialogpop_bg2).into(imageView);
    }
}
