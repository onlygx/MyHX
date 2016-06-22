package com.example.viewpagerdemo.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.viewpagerdemo.ui.activity.EatInfoActivity;
import com.example.viewpagerdemo.ui.bean.ColltentBean;
import com.example.viewpagerdemo.ui.jlfragmenwork.Contantor;
import com.squareup.picasso.Picasso;
import com.xingkesi.foodapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 我的收藏列表适配器
 */
public class ColltentItemAdpter extends RecyclerView.Adapter<ColltentItemAdpter.ViewHolder> {


    Context c;
    delData del;
    private List<ColltentBean.ListBean> adList;

    public ColltentItemAdpter(Context c, delData del) {
        this.c = c;
        this.del = del;
        adList = new ArrayList<>();
    }

    public List<ColltentBean.ListBean> getArrayLists() {
        return adList;
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
        final ColltentBean.ListBean data = adList.get(position);
        final String name = data.getGoods().getName();
        vh.findshopTitle.setText(name);
        String content = data.getGoods().getContent();
        int by = data.getGoods().getBaoYou();
        int peiSong = data.getGoods().getPeiSong();
        int kuaiDi = data.getGoods().getKuaiDi();
        int ZiTi = data.getGoods().getZiTi();
        StringBuffer sb = new StringBuffer();
        if (peiSong == 1) {
            sb.append("配送");
        }
        if (peiSong == 1 && kuaiDi == 1) {
            sb.append("/快递");
        } else if (peiSong != 1 && kuaiDi == 1) {
            sb.append("快递");
        }
        if ((peiSong == 1 && kuaiDi == 1 && ZiTi == 1) || (kuaiDi == 1 && ZiTi == 1)) {
            sb.append("/自提");
        } else {
            sb.append("自提");
        }
        vh.findshopNum.setText(sb.toString());
        if (by == 1) {
            vh.baoyou.setVisibility(View.VISIBLE);
        }
        double Price = data.getGoods().getPrice();
        vh.findshopSummongye.setText(Price + "");
        long dates = data.getGoods().getSetTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:ss");
        String da = sdf.format(new Date(dates));
        vh.newDate.setText(da);

        if (data.getGoods().getBannerList().size() > 0) {
            String url = data.getGoods().getBannerList().get(0).getUrl();
            Picasso.with(c).load(Contantor.Imagepost + url).placeholder(R.drawable.aliwx_default_photo_right)
                    .error(R.drawable.aliwx_fail_photo_right).into(vh.shopIocn);
        } else {
            Picasso.with(c).load(R.drawable.aliwx_default_photo_right).into(vh.shopIocn);
        }


        vh.findshopDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                del.del(position);
            }
        });
        vh.shopIocn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(c, EatInfoActivity.class);
                it.putExtra("id", data.getGoods().getId() + "");
                it.putExtra("shopID", data.getGoods().getShopId() + "");
                it.putExtra("name", name + "");
                c.startActivity(it);

            }
        });
        vh.ta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(c, EatInfoActivity.class);
                it.putExtra("id", data.getGoods().getId() + "");
                it.putExtra("shopID", data.getGoods().getShopId() + "");
                it.putExtra("name", name + "");
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
        return adList.size();
    }


    public interface delData {
        void del(int postion);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.shop_iocn)
        ImageView shopIocn;
        @Bind(R.id.baoyou)
        ImageView baoyou;

        @Bind(R.id.findshop_title)
        TextView findshopTitle;
        @Bind(R.id.findshop_summongye)
        TextView findshopSummongye;
        @Bind(R.id.findshop_num)
        TextView findshopNum;

        @Bind(R.id.newDate)
        TextView newDate;
        @Bind(R.id.findshop_del)
        Button findshopDel;
        @Bind(R.id.ta)
        LinearLayout ta;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
