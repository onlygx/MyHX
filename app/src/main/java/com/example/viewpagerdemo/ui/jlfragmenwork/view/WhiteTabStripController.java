package com.example.viewpagerdemo.ui.jlfragmenwork.view;

import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;

import com.xingkesi.foodapp.R;


/**
 * Created by liujing on 16/3/20.
 */
public class WhiteTabStripController {
    private Context context;
    private PagerSlidingTabStrip pagerSlidingTabStrip;
    private boolean bigTabStrip;

    public WhiteTabStripController(Context context, PagerSlidingTabStrip pagerSlidingTabStrip) {
        this.context = context;
        this.pagerSlidingTabStrip = pagerSlidingTabStrip;
    }

    public void resetSkin(){
        int slidBlockWidth;
        if(bigTabStrip){
            slidBlockWidth = (int) ((60 * context.getResources().getDisplayMetrics().density) + 0.5);
        }else{
            slidBlockWidth = (int) ((33 * context.getResources().getDisplayMetrics().density) + 0.5);
        }
        int slidBlockHeight = (int) ((2 * context.getResources().getDisplayMetrics().density) + 0.5);
        RectShape shape = new RectShape();
        shape.resize(slidBlockWidth, slidBlockHeight);
        ShapeDrawable drawable = new ShapeDrawable(shape);
        drawable.getPaint().setColor(context.getResources().getColor(R.color.logding_bg));
        drawable.setIntrinsicWidth(slidBlockWidth);
        drawable.setIntrinsicHeight(slidBlockHeight);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        pagerSlidingTabStrip.setBackgroundColor(context.getResources().getColor(R.color.white));
        pagerSlidingTabStrip.setSlidingBlockDrawable(drawable);
    }

    public void setBigTabStrip(boolean bigTabStrip) {
        this.bigTabStrip = bigTabStrip;
    }
}
