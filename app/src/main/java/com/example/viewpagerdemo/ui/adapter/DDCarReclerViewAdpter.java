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
import com.squareup.picasso.Picasso;
import com.xingkesi.foodapp.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 订单列表
 * Created by Administrator on 2016/5/20.
 */
public class DDCarReclerViewAdpter extends RecyclerView.Adapter<DDCarReclerViewAdpter.ViewHolder> {

    Context c;
    ArrayList<ShopInfoListBean> list;
    Shope_jian_add shopIn;

    //int fnum,fmoney;


    public DDCarReclerViewAdpter(Context c, ArrayList<ShopInfoListBean> list, Shope_jian_add shopIn) {
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
        final ShopInfoListBean data = list.get(position);
        final int pos = position;
        vh.shopName.setText(data.getShopName());
        vh.shopTitle.setText(data.getName());

        final int num = data.getCurrNum();//数量
        final double money = data.getPrice();//单价
        final int fnum = list.get(list.size()-1).getFnum();
        final double fmoney = list.get(list.size()-1).getFmoney();

        if(data.getBannerList()!=null && data.getBannerList().size()>0) {
            String url = data.getBannerList().get(0).getUrl();
            Picasso.with(c).load(url).placeholder(R.drawable.aliwx_default_photo_right)
                    .error(R.drawable.aliwx_fail_photo_right).into(vh.shop_icon);
        }else{
            Picasso.with(c).load(R.drawable.aliwx_default_photo_right).into(vh.shop_icon);
        }

       /* fnum=fnum+num;
        nmoneys=num*money;
        fmoney=fmoney+nmoneys;*/
        DD.d("数量：" + fnum + "==" + fmoney);

        vh.shopMoneny.setText(money + "");
        if (data.getYouPrice()>0) {
            vh.shopIsyh.setVisibility(View.VISIBLE);
            vh.shopYmoney.setText(data.getYouPrice() + "");
        } else {
            vh.shopIsyh.setVisibility(View.INVISIBLE);
        }

        vh.shopNum.setText(num + "");
        vh.shopZnum.setText(num + "");
        vh.shop_zmongy.setText(money + "");

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

                DD.d("jian:"+num);
                if(num>0) {
                    shopIn.ject(pos, money, num, fnum, fmoney);
                }


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
      //  @Bind(R.id.shop_name)
        TextView shopName;
       // @Bind(R.id.shop_title)
        TextView shopTitle;
       // @Bind(R.id.shop_moneny)
        TextView shopMoneny;
       // @Bind(R.id.shop_isyh)
        ImageView shopIsyh;
       // @Bind(R.id.shop_icon)
        ImageView shop_icon;
       // @Bind(R.id.shop_num)
        TextView shopNum;
      //  @Bind(R.id.shop_znum)
        TextView shopZnum;
       // @Bind(R.id.shop_ymoney)
        TextView shopYmoney;
        //@Bind(R.id.shop_zmongy)
        TextView shop_zmongy;
       // @Bind(R.id.shop_jec)
        ImageView shop_jec;
       // @Bind(R.id.shop_add)
        ImageView shop_add;


        ViewHolder(View view) {
            super(view);
            shopName= (TextView) view.findViewById(R.id.shop_name);
            shopTitle= (TextView) view.findViewById(R.id.shop_title);
            shopMoneny= (TextView) view.findViewById(R.id.shop_moneny);
            shopIsyh= (ImageView) view.findViewById(R.id.shop_isyh);
            shop_icon= (ImageView) view.findViewById(R.id.shop_icon);
            shopNum= (TextView) view.findViewById(R.id.shop_num);
            shopZnum= (TextView) view.findViewById(R.id.shop_znum);
            shopYmoney= (TextView) view.findViewById(R.id.shop_ymoney);
            shop_zmongy= (TextView) view.findViewById(R.id.shop_zmongy);
            shop_jec= (ImageView) view.findViewById(R.id.shop_jec);
            shop_add= (ImageView) view.findViewById(R.id.shop_add);
            //ButterKnife.bind(this, view);
        }
    }


    public interface Shope_jian_add {

        /**
         * @param money  单价
         * @param num    数量
         * @param fnum   总数
         * @param fmoney 总价
         */
        void ject(int position, double money, int num, int fnum, double fmoney);

        void adds(int position, double money, int num, int fnum, double fmoney);

    }
}
