package com.example.viewpagerdemo.ui.jlfragmenwork.adpter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by liujing on 16/3/20.
 */
public class FragmentArrayPageAdapter extends
        FragmentPagerAdapter {
    private Fragment[] fragments;

    public FragmentArrayPageAdapter(FragmentManager fm,
                                    Fragment... fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int arg0) {
        return fragments[arg0];
    }

    @Override
    public int getCount() {
        return fragments != null ? fragments.length : 0;
    }

    public Fragment[] getFragments() {
        return fragments;
    }
}
