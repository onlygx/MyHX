package com.example.viewpagerdemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.viewpagerdemo.ui.bean.EateListBean;
import com.xingkesi.foodapp.R;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.Bind;

/**
 * Created by Administrator on 2016/5/20.
 */
public class EatReclerViewAdpter extends RecyclerView.Adapter<EatReclerViewAdpter.ViewHolder> {

    Context c;
    ArrayList<EateListBean> list;

    public EatReclerViewAdpter(Context c, ArrayList<EateListBean> list) {
        this.c = c;
        this.list = list;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(c).inflate(R.layout.item_eatreclerviewadpter, parent, false);
        ViewHolder viewHolder = new ViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        Log.d("LD","22postion:"+position);
        EateListBean data =list.get(position);
        vh.listTitle.setText(data.getTitle());
        vh.listScroe.setText(data.getScore());
        vh.listNum.setText(data.getMoney());
        vh.listAddr.setText(data.getAddr());
        vh.listMoney.setText(data.getMoney());
        vh.listName.setText(data.getName());
        //-------------

        vh.listImage.setImageResource(R.drawable.pic);

    }
    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'item_eatreclerviewadpter.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static  class ViewHolder extends RecyclerView.ViewHolder{
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

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
