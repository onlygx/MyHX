package com.example.viewpagerdemo.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.viewpagerdemo.ui.Contantor;
import com.example.viewpagerdemo.ui.activity.EatInfoActivity;
import com.example.viewpagerdemo.ui.activity.IndexMainActivity;
import com.example.viewpagerdemo.ui.bean.ColltentBean;
import com.example.viewpagerdemo.ui.bean.SearchBean;
import com.squareup.picasso.Picasso;
import com.xingkesi.foodapp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 我的收藏列表适配器
 */
public class SearchItemAdpter extends RecyclerView.Adapter<SearchItemAdpter.ViewHolder> {


    Context c;
    private List<SearchBean> mDatas;
    private int type = 0;

    public SearchItemAdpter(Context c,List<SearchBean> mList) {
        this.c = c;
        mDatas = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(c).inflate(R.layout.item_newcolltent_ddlist, parent, false);
        ViewHolder viewHolder = new ViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, final int position) {
        //-------------------
        final SearchBean  data = mDatas.get(position);
        if(type==0){
            if (data.getCommodityBean().getBannerList().size() > 0) {
                String url = data.getCommodityBean().getBannerList().get(0).getUrl();
                Picasso.with(c).load(Contantor.Imagepost + url).placeholder(R.drawable.aliwx_default_photo_right)
                        .error(R.drawable.aliwx_fail_photo_right).into(vh.shopIocn);
            } else {
                Picasso.with(c).load(R.drawable.aliwx_default_photo_right).into(vh.shopIocn);
            }
            vh.findshopTitle.setText(data.getCommodityBean().getName());
            vh.tv_price.setText("￥"+data.getCommodityBean().getPrice());
            vh.rl_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(c, EatInfoActivity.class);
                    it.putExtra("id", data.getCommodityBean().getId() + "");
                    it.putExtra("shopID", data.getCommodityBean().getShopId() + "");
                    it.putExtra("name", data.getCommodityBean().getName() + "");
                    c.startActivity(it);
                }
            });
        }else{
            if (data.getNeedBean().getBannerList().size() > 0) {
                String url = data.getNeedBean().getBannerList().get(0).getUrl();
                Picasso.with(c).load(Contantor.Imagepost + url).placeholder(R.drawable.aliwx_default_photo_right)
                        .error(R.drawable.aliwx_fail_photo_right).into(vh.shopIocn);
            } else {
                Picasso.with(c).load(R.drawable.aliwx_default_photo_right).into(vh.shopIocn);
            }
            vh.findshopTitle.setText(data.getNeedBean().getTitle());
            vh.tv_price.setText("￥"+data.getNeedBean().getPrice());
            vh.rl_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it =new Intent(c, IndexMainActivity.class);
                    it.putExtra("tag",0);
                    it.putExtra("id",data.getCommodityBean().getId()+"");
                    c.startActivity(it);
                }
            });
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    public interface delData {
        void del(int postion);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.shop_iocn)
        ImageView shopIocn;
        @Bind(R.id.findshop_title)
        TextView findshopTitle;
        @Bind(R.id.tv_price)
        TextView tv_price;
        @Bind(R.id.rl_parent)
        RelativeLayout rl_parent;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public void setType(int type) {
        this.type = type;
    }
}
