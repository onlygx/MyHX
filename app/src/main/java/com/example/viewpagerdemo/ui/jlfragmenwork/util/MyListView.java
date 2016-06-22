package com.example.viewpagerdemo.ui.jlfragmenwork.util;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 解决Scroll嵌套后的显示不全  MyGradView一样
 * Created by Administrator on 2016/5/22.
 */
public class MyListView extends ListView {

    public MyListView(Context context) {
        super(context);
    }
    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public MyListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
