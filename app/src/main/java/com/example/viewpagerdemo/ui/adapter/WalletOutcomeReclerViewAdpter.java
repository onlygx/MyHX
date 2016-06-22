package com.example.viewpagerdemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.viewpagerdemo.ui.bean.Wallet_IncomeBean;
import com.xingkesi.foodapp.R;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.Bind;

/**
 * Created by Administrator on 2016/5/20.
 */
public class WalletOutcomeReclerViewAdpter extends RecyclerView.Adapter<WalletOutcomeReclerViewAdpter.ViewHolder> {

    Context c;
    ArrayList<Wallet_IncomeBean> list;

    public WalletOutcomeReclerViewAdpter(Context c, ArrayList<Wallet_IncomeBean> list) {
        this.c = c;
        this.list = list;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(c).inflate(R.layout.item_wallet_income, parent, false);
        ViewHolder viewHolder = new ViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        Log.d("LD","555postion:"+position);
        Wallet_IncomeBean data =list.get(position);
        vh.walletIn_name.setText(data.getComeName());
        vh.walletIn_money.setText(data.getComeMoney());
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
        @Bind(R.id.walletIn_name)
        TextView walletIn_name;
        @Bind(R.id.walletIn_money)
        TextView walletIn_money;


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
