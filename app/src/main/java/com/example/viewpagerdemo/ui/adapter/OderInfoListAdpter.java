package com.example.viewpagerdemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.viewpagerdemo.ui.bean.OderInfoBean;
import com.xingkesi.foodapp.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * d订单详情中的商品列表
 */
public class OderInfoListAdpter extends RecyclerView.Adapter<OderInfoListAdpter.ViewHolder> {


    Context c;
    private List<OderInfoBean.GoodsesBean> adList;

    public OderInfoListAdpter(Context c, List<OderInfoBean.GoodsesBean> adList) {
        this.c = c;
        this.adList = adList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(c).inflate(R.layout.item_oderinfo_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, final int position) {
        //-------------------
        OderInfoBean.GoodsesBean data = adList.get(position);

        vh.oderListTitle.setText(data.getName());
        vh.oderListNum.setText(data.getNumber()+"");
        vh.oderListMoney.setText(data.getPrice()+"");



    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return adList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.oder_list_title)
        TextView oderListTitle;
        @Bind(R.id.oder_list_num)
        TextView oderListNum;
        @Bind(R.id.oder_list_money)
        TextView oderListMoney;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
