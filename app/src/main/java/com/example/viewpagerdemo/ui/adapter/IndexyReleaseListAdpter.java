package com.example.viewpagerdemo.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.viewpagerdemo.ui.activity.EatInfoActivity;
import com.example.viewpagerdemo.ui.activity.IndexMainActivity;
import com.example.viewpagerdemo.ui.bean.IndexRaleaseBean;
import com.example.viewpagerdemo.ui.bean.ShoppingListBanerBean;
import com.example.viewpagerdemo.ui.jlfragmenwork.Contantor;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.CircleImageView;
import com.squareup.picasso.Picasso;
import com.xingkesi.foodapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


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

        vh.dot_layout.setTag(data.getId());
        eateAdapter = new FruitMainAotuAdapter(c, bannerList,data.getId()+"");
        vh.eatVptwo.setAdapter(eateAdapter);// 设置填充ViewPager页面的适配器
        // 设置一个监听器，当ViewPager中的页面改变时调用
        vh.eatVptwo.setOnPageChangeListener(new MyPageChangeListener());

        vh.listTitle.setText(data.getTitle());
        String content =data.getContent();
        StringBuffer sb=new StringBuffer();
        sb.append(data.getProvince()+" "+data.getCity()+" "+data.getDistrict());
        vh.listAddr.setText(sb.toString());
        double Price =data.getPrice();
        vh.listMoney.setText("￥"+Price+"");
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
        @Bind(R.id.list_title)
        TextView listTitle;
        /*@Bind(R.id.list_scroe)
        TextView listScroe;
        @Bind(R.id.list_num)
        TextView listNum;*/
        @Bind(R.id.list_addr)
        TextView listAddr;
        @Bind(R.id.list_money)
        TextView listMoney;
        @Bind(R.id.list_image)
        CircleImageView listImage;
        @Bind(R.id.list_name)
        TextView listName;
        @Bind(R.id.eat_vptwo)
        ViewPager eatVptwo;
        @Bind(R.id.infoLayout)
        LinearLayout infoLayout;
        @Bind(R.id.righ)
        RelativeLayout righ;
        @Bind(R.id.dotone2)
        LinearLayout dot_layout;
        @Bind(R.id.lunb)
        LinearLayout lunb;

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
