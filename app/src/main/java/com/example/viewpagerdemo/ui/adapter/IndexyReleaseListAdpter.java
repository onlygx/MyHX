package com.example.viewpagerdemo.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.viewpagerdemo.ui.activity.IndexMainActivity;
import com.example.viewpagerdemo.ui.bean.IndexRaleaseBean;
import com.example.viewpagerdemo.ui.bean.ShoppingListBanerBean;
import com.example.viewpagerdemo.ui.jlfragmenwork.Contantor;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.CircleImageView;
import com.example.viewpagerdemo.ui.views.HomeBannerImageHolderView;
import com.example.viewpagerdemo.ui.views.banner.CBViewHolderCreator;
import com.example.viewpagerdemo.ui.views.banner.ConvenientBanner;
import com.example.viewpagerdemo.ui.views.banner.OnItemClickListener;
import com.squareup.picasso.Picasso;
import com.xingkesi.foodapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 首页需求列表
 */
public class IndexyReleaseListAdpter extends RecyclerView.Adapter<IndexyReleaseListAdpter.ViewHolder> {

    Context c;
    delData del;
    private List<IndexRaleaseBean.ListBean> adList;
    private List<ShoppingListBanerBean> bannerList;
    FruitMainAotuAdapter eateAdapter;
    private ArrayList<View> dots; // 图片标题正文的那些点;
    int currentItem;
    public IndexyReleaseListAdpter(Context c) {
        this.c = c;
        adList = new ArrayList<>();
        dots = new ArrayList<>();
    }

    public List<IndexRaleaseBean.ListBean> getArrayLists() {
        return adList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(c).inflate(R.layout.item_eatreclerviewadpter, parent, false);
        ViewHolder viewHolder = new ViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, final int position) {



        final IndexRaleaseBean.ListBean data = adList.get(position);
        //ces
        bannerList=data.getBannerList();
        //  DD.d(position+"--banner:"+adList.size()+"---"+data.getName()+"=="+data.getId());
        vh.convenientBanner.setPages(new CBViewHolderCreator<HomeBannerImageHolderView>() {
            @Override
            public HomeBannerImageHolderView createHolder() {
                return new HomeBannerImageHolderView();
            }
        }, bannerList);
        vh.convenientBanner.setPageIndicator(new int[]{R.drawable.icon_dot_green,
                R.drawable.icon_dot_white});
        vh.convenientBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });


        vh.listTitle.setText(data.getTitle());
        String content =data.getContent();
        StringBuffer sb=new StringBuffer();
        sb.append(data.getProvince()+" "+data.getCity()+" "+data.getDistrict());
        vh.listAddr.setText(sb.toString());
        double Price =data.getPrice();
        vh.listMoney.setText(Price+"");
        long dates =data.getSetTime();
        SimpleDateFormat sdf =new SimpleDateFormat("yy-MM-dd HH:ss");
        String da=sdf.format(new Date(dates));

        vh.infoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent(c, IndexMainActivity.class);
                it.putExtra("tag",0);
                it.putExtra("id",data.getId()+"");
                c.startActivity(it);

            }
        });
        vh.listName.setText(data.getSendUser().getNickName());//卖家名称
        //卖家头像
        Picasso.with(c).load(Contantor.Imagepost + data.getSendUser().getHead()).placeholder(R.drawable.aliwx_default_photo_right)
                .error(R.drawable.aliwx_fail_photo_right).into(vh.listImage);
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
        TextView listTitle;
        TextView listAddr;
        TextView listMoney;
        CircleImageView listImage;
        TextView listName;
        LinearLayout infoLayout;
        ConvenientBanner convenientBanner;
        public ViewHolder(View itemView) {
            super(itemView);
            listTitle= (TextView) itemView.findViewById(R.id.list_title);
            listAddr= (TextView) itemView.findViewById(R.id.list_addr);
            listMoney= (TextView) itemView.findViewById(R.id.list_money);
            listImage= (CircleImageView) itemView.findViewById(R.id.list_image);
            listName= (TextView) itemView.findViewById(R.id.list_name);
            convenientBanner = (ConvenientBanner) itemView.findViewById(R.id.convenientBanner);
            infoLayout= (LinearLayout) itemView.findViewById(R.id.infoLayout);
        }
    }



}
