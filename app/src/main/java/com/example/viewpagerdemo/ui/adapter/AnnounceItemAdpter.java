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

import com.example.viewpagerdemo.ui.bean.ShoppingListBanerBean;
import com.example.viewpagerdemo.ui.bean.ShoppingListBean;
import com.example.viewpagerdemo.ui.activity.EatInfoActivity;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.TS;
import com.xingkesi.foodapp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Bind;


/**
 * 首页列表适配器
 */
public class AnnounceItemAdpter extends RecyclerView.Adapter<AnnounceItemAdpter.ViewHolder> {


    Context c;
    ArrayList<ShoppingListBean> list;
    int currentItem;
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
                dots.add(view);
                vh.dot_layout.addView(view);
            }
        }

       /* Picasso.with(c).load(Contantor.Imagepost+url).
                placeholder(R.drawable.logding)
                .error(R.drawable.dialogpop_bg2).into(view);*/
        //--------------------------------------
        final long shopID=data.getShopId();
        final int id=data.getId();
        final String name =data.getName();
        eateAdapter = new EateAotuAdapter(c, adList,id+"",name);
        vh.eatVptwo.setAdapter(eateAdapter);// 设置填充ViewPager页面的适配器
        // 设置一个监听器，当ViewPager中的页面改变时调用
        vh.eatVptwo.setOnPageChangeListener(new MyPageChangeListener());

        vh.listTitle.setText(data.getName());
        vh.listScroe.setText(data.getSort() + "");//评分
        vh.listNum.setText("暂无");//多少人吃过
        vh.listAddr.setText(data.getShopAddress());//地址
        vh.listMoney.setText(data.getPrice() + "");//价格
        vh.listName.setText(data.getShopName());//卖家名称
        //-------------
        vh.listImage.setImageResource(R.drawable.pic);//卖家头像

        //商品详情
        vh.infoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DD.v("单机了："+name+"=="+id+"=="+shopID);
                Intent it =new Intent(c, EatInfoActivity.class);
                it.putExtra("id",id+"");
                it.putExtra("shopID",shopID+"");
                it.putExtra("name",name+"");
                c.startActivity(it);
            }
        });

        //商家信息
        vh.righ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TS.shortTime("商家信息");

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

    public interface delData {
        void del(int tag, int postion, String state);

        void startInfo(String id);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.list_title)
        TextView listTitle;
        @Bind(R.id.list_scroe)
        TextView listScroe;
        @Bind(R.id.list_num)
        TextView listNum;
        @Bind(R.id.list_addr)
        TextView listAddr;
        @Bind(R.id.list_money)
        TextView listMoney;
        @Bind(R.id.list_image)
        ImageView listImage;
        @Bind(R.id.list_name)
        TextView listName;
        @Bind(R.id.eat_vptwo)
        ViewPager eatVptwo;
        @Bind(R.id.infoLayout)
        LinearLayout infoLayout;
        @Bind(R.id.righ)
        LinearLayout righ;
        @Bind(R.id.dotone2)
        LinearLayout dot_layout;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class MyPageChangeListener implements ViewPager.OnPageChangeListener {

        private int oldPosition2 = 0;

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int position) {
            currentItem = position;
            dots.get(oldPosition2).setBackgroundResource(R.drawable.dot_normal);
            dots.get(position).setBackgroundResource(R.drawable.dot_focused);
            oldPosition2 = position;
        }
    }



}
