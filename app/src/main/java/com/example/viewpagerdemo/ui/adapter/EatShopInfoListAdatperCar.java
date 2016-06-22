package com.example.viewpagerdemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.viewpagerdemo.ui.bean.ShopInfoListBean;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.xingkesi.foodapp.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/28.
 * 店铺-->购物车--->商品列表
 */
public class EatShopInfoListAdatperCar extends RecyclerView.Adapter<EatShopInfoListAdatperCar.ViewHolder> {

    ArrayList<ShopInfoListBean> contentList;
    Context context;
    CarInterfaceClass carInter;
    public EatShopInfoListAdatperCar(Context context, ArrayList<ShopInfoListBean> contentList,CarInterfaceClass carInter) {
        this.context = context;
        this.contentList = contentList;
        this.carInter = carInter;
       // DD.d("购物车:"+contentList.size());
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(context).inflate(R.layout.shopinfocar, parent, false);
        ViewHolder viewHolder = new ViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh,  int position) {
        final int pos=position;
        final ShopInfoListBean sif= contentList.get(position);
        int nums=sif.getCurrNum();
        DD.d("onBindViewHolder购物车:"+position+"==="+nums);
        vh.listCongtent.setText(sif.getName());
        vh.listOldmoney.setText(sif.getPrice()+"");
        vh.num.setText(nums+"");

        vh.addNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //加 更改购物车下的数量
                int num =sif.getCurrNum();
                sif.setCurrNum(num+1);
                notifyDataSetChanged();
                carInter.AddAnim(pos);

            }
        });

        vh.jianNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //减 更改购物车下的数量
                int num =sif.getCurrNum();
                if(num!=0) {
                    sif.setCurrNum(num - 1);
                    notifyDataSetChanged();
                    carInter.JJAnim(pos);
                }

            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return contentList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.list_congtent)
        TextView listCongtent;
        @Bind(R.id.num)
        TextView num;
        @Bind(R.id.list_oldmoney)
        TextView listOldmoney;
        @Bind(R.id.jianNum)
        ImageView jianNum;
        @Bind(R.id.addNum)
        ImageView addNum;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    public interface CarInterfaceClass{
        void AddAnim(int pos);
        void JJAnim(int pos);
    }
}
