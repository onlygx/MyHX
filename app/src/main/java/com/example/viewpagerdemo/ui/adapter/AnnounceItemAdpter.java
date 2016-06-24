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
import android.widget.Toast;

import com.example.viewpagerdemo.ui.activity.EatInfoActivity;
import com.example.viewpagerdemo.ui.bean.ShoppingListBanerBean;
import com.example.viewpagerdemo.ui.bean.ShoppingListBean;
import com.example.viewpagerdemo.ui.jlfragmenwork.Contantor;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.CircleImageView;
import com.squareup.picasso.Picasso;
import com.xingkesi.foodapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页列表适配器
 */
public class AnnounceItemAdpter extends RecyclerView.Adapter<AnnounceItemAdpter.ViewHolder> {

    Context c;
    ArrayList<ShoppingListBean> list;
    private ArrayList<View> dots; // 图片标题正文的那些点;
    private List<ShoppingListBanerBean> adList;
    EateAotuAdapter eateAdapter;

    public AnnounceItemAdpter(Context c) {
        this.c = c;
        list = new ArrayList<>();
        dots = new ArrayList<>();
    }
    public  ArrayList<ShoppingListBean> getArrayLists(){
        return list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(c).inflate(R.layout.item_eatreclerviewadpter, parent, false);
        ViewHolder viewHolder = new ViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, final int position) {
        //-------------------
        //ces
        final ShoppingListBean data = list.get(position);
        adList=data.getBannerList();
      //  DD.d(position+"--banner:"+adList.size()+"---"+data.getName()+"=="+data.getId());

        if(adList.size()>0) {
            dots.clear();
            if(vh.dot_layout.getChildCount()>0){
                vh.dot_layout.removeAllViews();
            }
            for (int i = 0; i < adList.size(); i++) {
                ImageView view = new ImageView(c);
                view.setBackgroundResource(R.drawable.dot_normal);
                LinearLayout.LayoutParams viewL = new LinearLayout.LayoutParams(10, 10);
                viewL.setMargins(5, 0, 5, 0);
                view.setLayoutParams(viewL);
                if(i==0){
                    view.setBackgroundResource(R.drawable.dot_focused);
                }
                dots.add(view);
                vh.dot_layout.addView(view);
            }
        }

        eateAdapter = new EateAotuAdapter(c, adList,data.getId()+"",data.getShopId()+"",data.getName());
        vh.eatVptwo.setAdapter(eateAdapter);// 设置填充ViewPager页面的适配器
        // 设置一个监听器，当ViewPager中的页面改变时调用
        vh.eatVptwo.setOnPageChangeListener(new MyPageChangeListener());
        /*eateAdapter.setmOnClickItemViewListener(new EateAotuAdapter.onClickItemViewListener() {
            @Override
            public void onClick() {
               *//* Toast.makeText(c,"点击轮播",Toast.LENGTH_LONG);
                Intent it =new Intent(c, EatInfoActivity.class);
                it.putExtra("id",data.getId()+"");
                it.putExtra("shopID",data.getShopId()+"");
                it.putExtra("name",data.getName()+"");
                c.startActivity(it);*//*
            }
        });*/
        vh.listTitle.setText(data.getName());//标题名称

        vh.listAddr.setText(data.getShopAddress());//地址
        vh.listMoney.setText("￥"+data.getPrice() + "");//价格
        vh.listName.setText(data.getShopName());//卖家名称
        //店铺头像
        Picasso.with(c).load(Contantor.Imagepost+data.getShop().getIcon()).
                placeholder(R.drawable.logding)
                .error(R.drawable.dialogpop_bg2).into(  vh.listImage);

        //商品详情
        vh.infoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent(c, EatInfoActivity.class);
                it.putExtra("id",data.getId()+"");
                it.putExtra("shopID",data.getShopId()+"");
                it.putExtra("name",data.getName()+"");
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
       // @Bind(R.id.list_title)
        TextView listTitle;
        /*@Bind(R.id.list_scroe)
        TextView listScroe;
        @Bind(R.id.list_num)
        TextView listNum;*/
       // @Bind(R.id.list_addr)
        TextView listAddr;
       // @Bind(R.id.list_money)
        TextView listMoney;
       // @Bind(R.id.list_image)
        CircleImageView listImage;
       // @Bind(R.id.list_name)
        TextView listName;
      //  @Bind(R.id.eat_vptwo)
        ViewPager eatVptwo;
       // @Bind(R.id.infoLayout)
        LinearLayout infoLayout;
        /*@Bind(R.id.righ)
        RelativeLayout righ;*/
       // @Bind(R.id.dotone2)
        LinearLayout dot_layout;
       /* @Bind(R.id.lunb)
        LinearLayout lunb;*/
       /* @Bind(R.id.rl_parent)
        RelativeLayout rl_parent;*/
        public ViewHolder(View itemView) {
            super(itemView);
            listTitle= (TextView) itemView.findViewById(R.id.list_title);
            listAddr= (TextView) itemView.findViewById(R.id.list_addr);
            listMoney= (TextView) itemView.findViewById(R.id.list_money);
            listName= (TextView) itemView.findViewById(R.id.list_name);
            eatVptwo= (ViewPager) itemView.findViewById(R.id.eat_vptwo);
            infoLayout= (LinearLayout) itemView.findViewById(R.id.infoLayout);
            dot_layout= (LinearLayout) itemView.findViewById(R.id.dotone2);
            listImage= (CircleImageView) itemView.findViewById(R.id.list_image);
            //ButterKnife.bind(this, itemView);
        }
    }

    class MyPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int position) {
            Toast.makeText(c,"position"+position,Toast.LENGTH_LONG);
            for(int i=0;i<dots.size();i++){
                if(position == i){
                    dots.get(position).setBackgroundResource(R.drawable.dot_focused);
                }else{
                    dots.get(i).setBackgroundResource(R.drawable.dot_normal);
                }
            }
        }
    }
}
