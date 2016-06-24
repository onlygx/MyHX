package com.example.viewpagerdemo.ui.jlfragmenwork.share;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xingkesi.foodapp.R;


/**
 * Created by Administrator on 2015/12/28.
 */
public class MeShareAdapter extends BaseAdapter {
    Context c;
    String[] name;
    int[] icon;
    public MeShareAdapter(Context c, String[] name, int[] icon) {
        this.c = c;
        this.name=name;
        this.icon=icon;
    }

    @Override
    public int getCount() {
        return name.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHoder vh;
        if(convertView==null){
            vh =new viewHoder();
            convertView = LayoutInflater.from(c).inflate(R.layout.itme_share,null);
            vh.iv= (ImageView) convertView.findViewById(R.id.share_image);
            vh.tv= (TextView) convertView.findViewById(R.id.share_txt);
            convertView.setTag(vh);
        }else{
            vh = (viewHoder) convertView.getTag();
        }

        vh.iv.setImageResource(icon[position]);
        vh.tv.setText(name[position]);
        return convertView;
    }


    class viewHoder {
        ImageView iv;
        TextView tv;
    }


}
