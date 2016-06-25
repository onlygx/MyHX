package com.example.viewpagerdemo.ui.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.viewpagerdemo.ui.bean.EatOneBaen;
import com.example.viewpagerdemo.ui.bean.ShoppingListBean;
import com.example.viewpagerdemo.ui.views.HomeBannerImageHolderView;
import com.example.viewpagerdemo.ui.views.banner.CBViewHolderCreator;
import com.example.viewpagerdemo.ui.views.banner.ConvenientBanner;
import com.example.viewpagerdemo.ui.views.banner.OnItemClickListener;
import com.xingkesi.foodapp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Bind;

/**
 * Created by Administrator on 2016/5/20.
 */
public class EatListViewAdpter extends BaseAdapter {

    Context c;
    ArrayList<ShoppingListBean> list;
    int currentItem;
    private ArrayList<View> dots; // 图片标题正文的那些点;
    private List<EatOneBaen> adList;
    EateAotuAdapter eateAdapter;

    public EatListViewAdpter(Context c) {
        this.c = c;
        list=new ArrayList<>();
        dots=new ArrayList<>();

    }

    public  ArrayList<ShoppingListBean> getArrayLists(){
        return list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if(convertView==null) {
            convertView = LayoutInflater.from(c).inflate(R.layout.item_eatreclerviewadpter, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        }else{
            vh= (ViewHolder) convertView.getTag();
        }
        //ces
        adList = testgetBannerAd();

        vh.convenientBanner.setPages(new CBViewHolderCreator<HomeBannerImageHolderView>() {
            @Override
            public HomeBannerImageHolderView createHolder() {
                return new HomeBannerImageHolderView();
            }
        }, adList);
        vh.convenientBanner.setPageIndicator(new int[]{R.drawable.icon_dot_green,
                R.drawable.icon_dot_white});
        vh.convenientBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });


        ShoppingListBean data =list.get(position);


        vh.listTitle.setText(data.getName());
        vh.listScroe.setText(data.getSort()+"");//评分
        vh.listNum.setText("暂无");//多少人吃过
        vh.listAddr.setText(data.getShopAddress());//地址
        vh.listMoney.setText(data.getPrice()+"");//价格
        vh.listName.setText(data.getName());//卖家名称
        //-------------

        vh.listImage.setImageResource(R.drawable.pic);//卖家头像
        return convertView;
    }




    /**
     */
    static  class ViewHolder {
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
        @Bind(R.id.convenientBanner)
        ConvenientBanner convenientBanner;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


    List<EatOneBaen> testgetBannerAd() {
        List<EatOneBaen> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            EatOneBaen eb = new EatOneBaen();
            eb.setId(i + "");
            eb.setName(i + "");
            eb.setUrl("http://pica.nipic.com/2007-12-21/200712219313548_2.jpg");
            list.add(eb);
        }
        return list;
    }
}
