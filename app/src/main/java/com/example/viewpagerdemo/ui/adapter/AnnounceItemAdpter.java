package com.example.viewpagerdemo.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.viewpagerdemo.ui.activity.EatInfoActivity;
import com.example.viewpagerdemo.ui.bean.ShoppingListBanerBean;
import com.example.viewpagerdemo.ui.bean.ShoppingListBean;
import com.example.viewpagerdemo.ui.Contantor;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.CircleImageView;
import com.example.viewpagerdemo.ui.units.PixelsUtils;
import com.example.viewpagerdemo.ui.views.HomeBannerImageHolderView;
import com.example.viewpagerdemo.ui.views.banner.CBViewHolderCreator;
import com.example.viewpagerdemo.ui.views.banner.ConvenientBanner;
import com.example.viewpagerdemo.ui.views.banner.OnItemClickListener;
import com.squareup.picasso.Picasso;
import com.xingkesi.foodapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页列表适配器
 */
public class AnnounceItemAdpter extends RecyclerView.Adapter<AnnounceItemAdpter.ViewHolder> {

    Context context;
    List<ShoppingListBean> mDatas;
    private ArrayList<View> dots; // 图片标题正文的那些点;
    private List<ShoppingListBanerBean> bannerList;

    public AnnounceItemAdpter(Context c,List<ShoppingListBean> list) {
        this.context = c;
        mDatas = list;
        dots = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(context).inflate(R.layout.item_eatreclerviewadpter, parent, false);
        ViewHolder viewHolder = new ViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, final int position) {
        final ShoppingListBean data = mDatas.get(position);
        //轮播图
        bannerList=data.getBannerList();

        vh.convenientBanner.setPages(new CBViewHolderCreator<HomeBannerImageHolderView>() {
            @Override
            public HomeBannerImageHolderView createHolder() {
                return new HomeBannerImageHolderView();
            }
        }, bannerList);
        //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
        vh.convenientBanner.setPageIndicator(new int[]{R.drawable.icon_dot_green,
                R.drawable.icon_dot_white});
        //设置指示器的方向
        //      .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
        //设置翻页的效果，不需要翻页效果可用不设
        //.setPageTransformer(ConvenientBanner.Transformer.DefaultTransformer)
//              .setOnPageChangeListener(this);监听翻页事件
//               convenientBanner.setManualPageable(false);//设置不能手动影响
        vh.convenientBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent it =new Intent(context, EatInfoActivity.class);
                if(data.getIsBanner()==0){
                    it.putExtra("id",data.getId()+"");
                    it.putExtra("shopID",data.getShopId()+"");
                    it.putExtra("name",data.getName()+"");
                    context.startActivity(it);
                }
            }
        });



        if(data.getIsBanner()==0){
            vh.infoLayout.setVisibility(View.VISIBLE);
            vh.shopLayout.setVisibility(View.VISIBLE);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    PixelsUtils.getWidth(context)-PixelsUtils.getScale(context,20), PixelsUtils.getScale(context,160));
            vh.convenientBanner.setLayoutParams(params);

            //banner 不允许滑动
            vh.convenientBanner.stopTurning();
            vh.listTitle.setText(data.getName());//标题名称
            vh.listAddr.setText(data.getShopAddress());//地址
            vh.listMoney.setText(data.getPrice() + "");//价格
            vh.listName.setText(data.getShopName());//卖家名称
            //店铺头像
            Picasso.with(context).load(Contantor.Imagepost+data.getShop().getIcon()).
                    placeholder(R.drawable.logding)
                    .error(R.drawable.dialogpop_bg2).into(  vh.listImage);

            //商品详情
            vh.infoLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it =new Intent(context, EatInfoActivity.class);
                    it.putExtra("id",data.getId()+"");
                    it.putExtra("shopID",data.getShopId()+"");
                    it.putExtra("name",data.getName()+"");
                    context.startActivity(it);
                }
            });

        }else{
            vh.infoLayout.setVisibility(View.GONE);
            vh.shopLayout.setVisibility(View.GONE);
            vh.convenientBanner.startTurning(2000);
           LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, PixelsUtils.getScale(context,160));
            vh.convenientBanner.setLayoutParams(params);
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



    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView listTitle;
        TextView listAddr;
        TextView listMoney;
        CircleImageView listImage;
        TextView listName;
        ConvenientBanner convenientBanner;
        LinearLayout infoLayout;
        LinearLayout shopLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            listTitle= (TextView) itemView.findViewById(R.id.list_title);
            listAddr= (TextView) itemView.findViewById(R.id.list_addr);
            listMoney= (TextView) itemView.findViewById(R.id.list_money);
            listName= (TextView) itemView.findViewById(R.id.list_name);
            convenientBanner = (ConvenientBanner) itemView.findViewById(R.id.convenientBanner);
            infoLayout= (LinearLayout) itemView.findViewById(R.id.infoLayout);
            shopLayout = (LinearLayout) itemView.findViewById(R.id.shop_layout);
            listImage= (CircleImageView) itemView.findViewById(R.id.list_image);
        }
    }
}
