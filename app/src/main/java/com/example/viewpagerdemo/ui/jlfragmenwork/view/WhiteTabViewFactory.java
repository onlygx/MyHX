package com.example.viewpagerdemo.ui.jlfragmenwork.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xingkesi.foodapp.R;


/**
 * Created by liujing on 16/3/20.
 */
public class WhiteTabViewFactory implements
        PagerSlidingTabStrip.TabViewFactory {
    private Context context;
    private String[] titles;

    public WhiteTabViewFactory(Context context, String[] titles) {
        this.context = context;
        this.titles = titles;
    }

    @Override
    public void addTabs(ViewGroup parent, int currentItemPosition) {
        // 先清除已有的Tab View
        parent.removeAllViews();

        ColorStateList colorStateList;
        int[][] states = new int[2][];
        int[] colors = new int[2];

        states[0] = new int[]{android.R.attr.state_selected};
        states[1] = new int[]{};

        colors[0] = Color.parseColor("#06C1AE");
        colors[1] = Color.parseColor("#ff696969");


        colorStateList = new ColorStateList(states, colors);
        for (int w = 0; w < titles.length; w++) {
            TextView titleTextView = (TextView) LayoutInflater
                    .from(context).inflate(R.layout.jl_tab_category, parent,
                            false);
            titleTextView.setTextColor(colorStateList);
            String title = titles[w];
            titleTextView.setText(title);
            if (w == currentItemPosition) {
                titleTextView.setSelected(true);
            } else {
                titleTextView.setSelected(false);
            }
            parent.addView(titleTextView);
        }
    }
}