package com.example.viewpagerdemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.viewpagerdemo.ui.bean.DDListBean;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.xingkesi.foodapp.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 订单列表
 * Created by Administrator on 2016/5/20.
 */
public class DDReclerViewAdpter extends RecyclerView.Adapter<DDReclerViewAdpter.ViewHolder> {

    Context c;
    ArrayList<DDListBean> list;
    Shope_jian_add shopIn;

    //int fnum,fmoney;


    public DDReclerViewAdpter(Context c, ArrayList<DDListBean> list, Shope_jian_add shopIn) {
        this.c = c;
        this.list = list;
        this.shopIn = shopIn;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(c).inflate(R.layout.item_dd_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder vh, int position) {
        final DDListBean data = list.get(position);
        final int pos = position;
        vh.shopName.setText(data.getShop_name());
        vh.shopTitle.setText(data.getShop_title());

        final int num = data.getShop_num();//数量
        final int money = data.getShop_money();//单价
        final int fnum = list.get(list.size()-1).getFnum();
        final int fmoney = list.get(list.size()-1).getFmoney();
       /* fnum=fnum+num;
        nmoneys=num*money;
        fmoney=fmoney+nmoneys;*/
        DD.d("数量：" + fnum + "==" + fmoney);

        vh.shopMoneny.setText(money + "");
        if (data.isshiYh()) {
            vh.shopIsyh.setVisibility(View.VISIBLE);
        } else {
            vh.shopIsyh.setVisibility(View.INVISIBLE);
        }

        vh.shopNum.setText(num + "");
        vh.shopZnum.setText(num + "");

        vh.shopYmoney.setText(data.getShop_ymoney() + "");
        vh.shop_zmongy.setText(data.getShop_zmoney() + "");

        if (num == 0) {
            vh.shop_jec.setVisibility(View.INVISIBLE);
        } else {
            if (vh.shop_jec.getVisibility() == View.INVISIBLE) {
                vh.shop_jec.setVisibility(View.VISIBLE);
            }
        }


        //减
        vh.shop_jec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shopIn.ject(pos, money, num, fnum, fmoney);


            }
        });
        //加
        vh.shop_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shopIn.adds(pos, money, num, fnum, fmoney);
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

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.shop_name)
        TextView shopName;
        @Bind(R.id.shop_title)
        TextView shopTitle;
        @Bind(R.id.shop_moneny)
        TextView shopMoneny;
        @Bind(R.id.shop_isyh)
        ImageView shopIsyh;
        @Bind(R.id.shop_num)
        TextView shopNum;
        @Bind(R.id.shop_znum)
        TextView shopZnum;
        @Bind(R.id.shop_ymoney)
        TextView shopYmoney;
        @Bind(R.id.shop_zmongy)
        TextView shop_zmongy;
        @Bind(R.id.shop_jec)
        ImageView shop_jec;
        @Bind(R.id.shop_add)
        ImageView shop_add;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    public interface Shope_jian_add {

        /**
         * @param money  单价
         * @param num    数量
         * @param fnum   总数
         * @param fmoney 总价
         */
        void ject(int position, int money, int num, int fnum, int fmoney);

        void adds(int position, int money, int num, int fnum, int fmoney);

    }
}
