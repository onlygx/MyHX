package com.example.viewpagerdemo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.viewpagerdemo.ui.bean.ShopInfoListBean;
import com.xingkesi.foodapp.R;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.Bind;

/**
 * Created by Administrator on 2016/5/28.
 * 店铺下商品列表
 */
public class EatShopInfoListAdatper extends BaseAdapter {

    ArrayList<ShopInfoListBean> contentList;
    Context context;

    public EatShopInfoListAdatper(Context context, ArrayList<ShopInfoListBean> contentList) {
        this.context = context;
        this.contentList = contentList;
    }

    @Override
    public int getCount() {
        return 7;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.shopinfo, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }



       /* ShopInfoListBean cb = contentList.get(position);
        vh.list_congtent.setText(cb.getIntro());
        vh.list_gongx.setText(cb.getContent());*/

       /* vh.tvContent.setText(cb.getContent());
        vh.tvName.setText(cb.getUserName());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String data = sdf.format(new Date(cb.getSetTime()));
        vh.tvDate.setText(data);
        Picasso.with(context).load(Contantor.Imagepost + cb.get()).
                placeholder(R.drawable.logding)
                .error(R.drawable.logding).into(vh.ivContent);*/


        return convertView;
    }


    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'eatcontentlayout.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @Bind(R.id.list_congtent)
        TextView list_congtent;//简介
        @Bind(R.id.list_image)
        ImageView list_image;//banner大图
        @Bind(R.id.iv_kd)
        ImageView iv_kd;//包邮
        @Bind(R.id.addNum)
        ImageView addNum;//加号
        @Bind(R.id.list_xs)
        ImageView list_xs;//大图_显示优惠
        @Bind(R.id.list_gongx)
        TextView list_gongx;//功效;
        @Bind(R.id.money)
        TextView money;
        @Bind(R.id.tv_kd)
        TextView tv_kd;//邮费
        @Bind(R.id.list_oldmoney)
        TextView list_oldmoney;//原价
        //@Bind(R.id.list_peis)
       // TextView list_peis;//配送

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
