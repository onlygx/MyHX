package com.example.viewpagerdemo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.squareup.picasso.Picasso;
import com.xingkesi.foodapp.R;

import java.io.File;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/6/19.
 */
public class ImageAdapter extends BaseAdapter {
    Context c;
    ArrayList<String> list;

    public ImageAdapter(Context c, ArrayList<String> list) {
        this.c = c;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        String url = list.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(c).inflate(R.layout.item_gridview, null);
            vh = new ViewHolder();
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.icon= (ImageView) convertView.findViewById(R.id.icon);
        vh.delIcon= (ImageView) convertView.findViewById(R.id.del_icon);

        DD.d("图片鸡杂："+url);
        Picasso.with(c).load(new File(url)).placeholder(R.drawable.aliwx_default_photo_right)
                .error(R.drawable.aliwx_fail_photo_right).into(vh.icon);


        vh.delIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                list.remove(position);
                notifyDataSetChanged();
            }
        });


        return convertView;
    }


    static class ViewHolder {
        ImageView icon;
        ImageView delIcon;

    }
}
