package com.example.viewpagerdemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.viewpagerdemo.ui.bean.WallBean;
import com.example.viewpagerdemo.ui.bean.Wallet_IncomeBean;
import com.xingkesi.foodapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/20.
 */
public class WalletIncomeReclerViewAdpter extends RecyclerView.Adapter<WalletIncomeReclerViewAdpter.ViewHolder> {

    Context c;
    ArrayList<WallBean> list;

    public WalletIncomeReclerViewAdpter(Context c, ArrayList<WallBean> list) {
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
        WallBean data = list.get(position);

        vh.walletInBhao.setText(data.get编号());
        vh.walletInLaiyuan.setText(data.get来源());
        vh.walletInType.setText(data.get类型());
        vh.walletInMoney.setText(data.get金额());
        vh.walletInNote.setText(data.get备注());
        SimpleDateFormat sdf =new SimpleDateFormat("MM-dd");
        String date=sdf.format(new Date(data.get时间()));
        vh.walletInDate.setText(date);


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
        @Bind(R.id.walletIn_bhao)
        TextView walletInBhao;
        @Bind(R.id.walletIn_type)
        TextView walletInType;
        @Bind(R.id.walletIn_laiyuan)
        TextView walletInLaiyuan;
        @Bind(R.id.walletIn_money)
        TextView walletInMoney;
        @Bind(R.id.walletIn_date)
        TextView walletInDate;
        @Bind(R.id.walletIn_note)
        TextView walletInNote;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
