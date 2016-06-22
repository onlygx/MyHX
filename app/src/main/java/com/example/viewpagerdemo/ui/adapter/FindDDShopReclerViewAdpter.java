package com.example.viewpagerdemo.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.viewpagerdemo.ui.activity.OrderInfoActivity;
import com.example.viewpagerdemo.ui.activity.PlayMainActivity;
import com.example.viewpagerdemo.ui.bean.FindDDListShopBean;
import com.example.viewpagerdemo.ui.bean.FindDDListShopGoodsBean;
import com.example.viewpagerdemo.ui.jlfragmenwork.Contantor;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.squareup.picasso.Picasso;
import com.xingkesi.foodapp.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 订单列表--》商铺做定
 * Created by Administrator on 2016/5/20.
 */
public class FindDDShopReclerViewAdpter extends RecyclerView.Adapter<FindDDShopReclerViewAdpter.ViewHolder> {

    Context c;
    ArrayList<FindDDListShopBean> list;


    public FindDDShopReclerViewAdpter(Context c) {
        this.c = c;
        this.list = new ArrayList<>();
        // DD.w("list:"+list.size());

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(c).inflate(R.layout.item_newfind_ddlist, parent, false);
        ViewHolder viewHolder = new ViewHolder(convertView);
        return viewHolder;
    }

    public ArrayList getArrayList() {
        return list;
    }

    @Override
    public void onBindViewHolder(final ViewHolder vh, int position) {
        final FindDDListShopBean data = list.get(position);
        //状态：1已提交  2已付款 3已发货 4已完成  5取消/失效
        final int pos = position;
        final int statue = data.getStatus();
        final long id = data.getId();
        final double money = data.getPrice();

        final String name =data.getShopName();
        vh.findshop_summongye.setText(money + "");//
        vh.findshopTitle.setText(name);//名
        long dates = data.getSetTime();
        SimpleDateFormat sf = new SimpleDateFormat("yy年MM月dd日 HH:mm");
        String fdate = sf.format(new Date(dates));
        vh.newDate.setText(fdate);//日期
        String url = Contantor.Imagepost + data.getShopIcon();
        DD.v("订单图片:" + url);
        Picasso.with(c).load(url).placeholder(R.drawable.aliwx_default_photo_right)
                .error(R.drawable.aliwx_fail_photo_right).into(vh.shop_iocn);

//        vh.findshopNum.setText(data.getPrice() + "");//配送  未提供 暂时用快递
        //--------状态-------
        if (statue == 1) {
            vh.fingshop_fk.setVisibility(View.VISIBLE);
            vh.fingshop_fk.setText("待付款");//付款
            vh.fingshopPl.setText("付款");
            vh.fingshopState.setText("已提交");
        } else if (statue == 4) {//完成
            vh.fingshopPl.setText("评价");
            vh.fingshop_fk.setVisibility(View.VISIBLE);
            vh.fingshop_fk.setText("待评价");//付款
            vh.fingshopState.setText("已完成");
        } else if (statue == 2) {//已付款
            vh.fingshopPl.setText("查看物流");
            vh.fingshop_fk.setVisibility(View.VISIBLE);
            vh.fingshop_fk.setText("已付款");//付款
            vh.fingshopState.setText("已付款");
        } else if (statue == 3) {//已发货
            vh.fingshopPl.setText("查看物流");
            vh.fingshop_fk.setVisibility(View.VISIBLE);
            vh.fingshop_fk.setText("已付款");//付款
            vh.fingshopState.setText("已付款");
        } else if (statue == 5) {//已失效
            vh.fingshopPl.setText("已失效");
            vh.fingshopPl.setEnabled(false);
            vh.fingshop_fk.setVisibility(View.VISIBLE);
            vh.fingshop_fk.setText("已失效");//付款
            vh.fingshopState.setText("已失效");
        } else if (statue == 0) {
            vh.fingshopPl.setVisibility(4);
            vh.fingshop_fk.setVisibility(4);
            vh.fingshopState.setVisibility(4);
        }


        vh.fingshopPl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* if (statue == 1) {//打开付款
                    Intent it = new Intent(c, PlayMainActivity.class);
                    it.putExtra("id", id + "");
                    it.putExtra("mo", money + "");
                    c.startActivity(it);
                } else if (statue == 5) {

                } else {*/
                    //查看详情
                    Intent it = new Intent(c, OrderInfoActivity.class);
                    it.putExtra("id", id + "");
                    it.putExtra("st", statue);
                    it.putExtra("name", name);
                    c.startActivity(it);

               // }
            }
        });


    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.shop_iocn)//图片
                ImageView shop_iocn;
        @Bind(R.id.newDate)//日期
                TextView newDate;
        @Bind(R.id.fingshop_state)//订单状态
                TextView fingshopState;
        @Bind(R.id.findshop_title)//商铺名
                TextView findshopTitle;
        @Bind(R.id.findshop_summongye)//总价
                TextView findshop_summongye;
        /* @Bind(R.id.findshop_num)//配送
         TextView findshopSummongye;*/
        @Bind(R.id.fingshop_pl) //评论按钮
                TextView fingshopPl;
        @Bind(R.id.fingshop_fk) //未付款
                TextView fingshop_fk;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
