package com.example.viewpagerdemo.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.viewpagerdemo.ui.activity.EatInfoActivity;
import com.example.viewpagerdemo.ui.bean.ShoppingListBanerBean;
import com.example.viewpagerdemo.ui.bean.ShoppingListBean;
import com.example.viewpagerdemo.ui.jlfragmenwork.Contantor;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.squareup.picasso.Picasso;
import com.xingkesi.foodapp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 首页列表适配器
 */
public class OthorShopListItemAdpter extends RecyclerView.Adapter<OthorShopListItemAdpter.ViewHolder> {


    Context c;
    ArrayList<ShoppingListBean> list;
    private List<ShoppingListBanerBean> adList;

    public OthorShopListItemAdpter(Context c) {
        this.c = c;
        list = new ArrayList<>();
    }

    public ArrayList<ShoppingListBean> getArrayLists() {
        return list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(c).inflate(R.layout.item_other_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, final int position) {
        //-------------------
        final ShoppingListBean data = list.get(position);
        adList = data.getBannerList();

        final int id=data.getId();
       final long shopID= data.getShopId();

        if (adList.size() > 0) {
            String url = data.getBannerList().get(0).getUrl();
            Picasso.with(c).load(Contantor.Imagepost + url).
                    placeholder(R.drawable.logding)
                    .error(R.drawable.dialogpop_bg2).into(vh.otherItemIcon);
        } else {
            Picasso.with(c).load(R.drawable.logding)
                    .error(R.drawable.dialogpop_bg2).into(vh.otherItemIcon);
        }

        final String title = data.getName();
        double price = data.getPrice();
        vh.otherItemName.setText(title);
        vh.otherItemPic.setText(price + "");

        //--------------------------------------


        //商品详情
        vh.otherLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DD.v("单机了：" + title + "==" + id + "==" + shopID);
                Intent it = new Intent(c, EatInfoActivity.class);
                it.putExtra("id", id + "");
                it.putExtra("shopID", shopID + "");
                it.putExtra("name", title + "");
                c.startActivity(it);
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
        @Bind(R.id.other_item_icon)
        ImageView otherItemIcon;
        @Bind(R.id.other_item_name)
        TextView otherItemName;
        @Bind(R.id.other_item_pic)
        TextView otherItemPic;
        @Bind(R.id.other_item_layout)
        LinearLayout otherItemLayout;
        @Bind(R.id.other_layout)
        RelativeLayout otherLayout;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
