package com.example.viewpagerdemo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.viewpagerdemo.ui.bean.ContentListBean;
import com.example.viewpagerdemo.ui.jlfragmenwork.Contantor;
import com.squareup.picasso.Picasso;
import com.xingkesi.foodapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.Bind;

/**
 * Created by Administrator on 2016/5/28.
 */
public class EatInfoContentAdatper extends BaseAdapter {

    ArrayList<ContentListBean> contentList;
    Context context;

    public EatInfoContentAdatper(Context context, ArrayList<ContentListBean> contentList) {
        this.context = context;
        this.contentList = contentList;
    }

    @Override
    public int getCount() {
        return contentList.size();
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

        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.eatcontentlayout, null);
            vh =new ViewHolder(convertView);
            convertView.setTag(vh);
        }else{
            vh= (ViewHolder) convertView.getTag();
        }

        ContentListBean cb=contentList.get(position);

        vh.tvContent.setText(cb.getContent());
        vh.tvName.setText(cb.getUserName());
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
        String data=sdf.format(new Date(cb.getSetTime()));
        vh.tvDate.setText(data);
        Picasso.with(context).load(Contantor.Imagepost+cb.getUserHead()).
                placeholder(R.drawable.logding)
                .error(R.drawable.logding).into(vh.ivContent);




        return convertView;
    }



    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'eatcontentlayout.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @Bind(R.id.tv_content)
        TextView tvContent;
        @Bind(R.id.iv_content)
        ImageView ivContent;
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_date)
        TextView tvDate;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
