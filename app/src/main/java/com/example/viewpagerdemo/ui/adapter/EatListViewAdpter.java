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
        //

        for (int i = 0; i < adList.size(); i++) {
            ImageView view = new ImageView(c);
            view.setBackgroundResource(R.drawable.dot_normal);
            LinearLayout.LayoutParams viewL = new LinearLayout.LayoutParams(10, 10);
            viewL.setMargins(5, 0, 5, 0);
            view.setLayoutParams(viewL);
            dots.add(view);
            vh.dot_layout.addView(view);
        }
       // eateAdapter = new EateAotuAdapter(c, adList);
        vh.eatVptwo.setAdapter(eateAdapter);// 设置填充ViewPager页面的适配器
        // 设置一个监听器，当ViewPager中的页面改变时调用
        vh.eatVptwo.setOnPageChangeListener(new MyPageChangeListener());


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
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_eatreclerviewadpter.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
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
        @Bind(R.id.eat_vptwo)
        ViewPager eatVptwo;
        @Bind(R.id.dotone2)
        LinearLayout dot_layout;

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
