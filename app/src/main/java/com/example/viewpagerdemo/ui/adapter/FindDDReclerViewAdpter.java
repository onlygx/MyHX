package com.example.viewpagerdemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.viewpagerdemo.ui.bean.DDListBean;
import com.example.viewpagerdemo.ui.bean.FindDDListBean;
import com.example.viewpagerdemo.ui.bean.FindDDListShopGoodsBean;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.xingkesi.foodapp.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 订单列表-->商铺下商品做定
 * Created by Administrator on 2016/5/20.
 */
public class FindDDReclerViewAdpter extends RecyclerView.Adapter<FindDDReclerViewAdpter.ViewHolder> {

    Context c;
    ArrayList<FindDDListShopGoodsBean> list;


    public FindDDReclerViewAdpter(Context c) {
        this.c = c;
        this.list = new ArrayList<>();
       // DD.w("list:"+list.size());

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(c).inflate(R.layout.item_find_ddlist, parent, false);
        ViewHolder viewHolder = new ViewHolder(convertView);
        return viewHolder;
    }

    public ArrayList getArrayList(){
        return  list;
    }

    @Override
    public void onBindViewHolder(final ViewHolder vh, int position) {
        final FindDDListShopGoodsBean data = list.get(position);
        //data.get
        final int pos = position;

        vh.fingshopName.setText(data.getName());
        vh.findshopTitle.setText(data.getName());
       // vh.shopMoneny.setText(data.getPrice());
        vh.findshopNum.setText(data.getNumber()+"");
        vh.findshopSummongye.setText(data.getPrice()+"");
        //--------测试状态-------
        if(position%2==0){
            vh.fingshopPl.setText("已评价");
            vh.fingshopState.setText("已完成");
        }else if(position%3==0){
            vh.fingshopPl.setText("待评价");
            vh.fingshopState.setText("已完成");
        }


    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }




    static class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.fingshop_name)
        TextView fingshopName;
        @Bind(R.id.fingshop_state)
        TextView fingshopState;
        @Bind(R.id.findshop_title)
        TextView findshopTitle;
        @Bind(R.id.shop_moneny)
        TextView shopMoneny;
        @Bind(R.id.findshop_num)
        TextView findshopNum;
        @Bind(R.id.findshop_summongye)
        TextView findshopSummongye;
        @Bind(R.id.fingshop_pl)
        TextView fingshopPl;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
