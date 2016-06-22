package com.example.viewpagerdemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.viewpagerdemo.ui.bean.AddressListBean;
import com.example.viewpagerdemo.ui.bean.FindDDListBean;
import com.example.viewpagerdemo.ui.bean.FindDDListShopGoodsBean;
import com.xingkesi.foodapp.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我--订单列表
 * Created by Administrator on 2016/5/20.
 */
public class FindDDShopNameReclerViewAdpter extends RecyclerView.Adapter<FindDDShopNameReclerViewAdpter.ViewHolder> {

    Context c;
    ArrayList<FindDDListBean> list;

    public FindDDShopNameReclerViewAdpter(Context c, ArrayList<FindDDListBean> list) {
        this.c = c;
        this.list = list;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(c).inflate(R.layout.item_findshopname, parent, false);
        ViewHolder viewHolder = new ViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        FindDDListBean data = list.get(position);
        String name =data.getList().get(position).getShopName();
        vh.name.setText(name);
        ArrayList<FindDDListShopGoodsBean> getGoodses=data.getList().get(position).getGoodses();
        /*FindDDReclerViewAdpter fv =new FindDDReclerViewAdpter(c,getGoodses);
        LinearLayoutManager  man = new LinearLayoutManager(c);
        man.setOrientation(LinearLayoutManager.VERTICAL);
        vh.find_rec.setLayoutManager(man);
        vh.find_rec.setAdapter(fv);*/

    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @OnClick(R.id.edit)
    public void onClick() {


    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.name)
        TextView name;
        @Bind(R.id.find_rec)
        RecyclerView find_rec;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
