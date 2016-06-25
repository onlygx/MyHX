package com.example.viewpagerdemo.ui.views.banner;

/**
 * Created by dingwei on 16/2/21
 */

import android.content.Context;
import android.view.View;

public interface Holder<T>{
    View createView(Context context);
    void UpdateUI(Context context, int position, T data);
}