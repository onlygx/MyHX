package com.example.viewpagerdemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.viewpagerdemo.ui.bean.MyReleaseBean;
import com.example.viewpagerdemo.ui.Contantor;
import com.squareup.picasso.Picasso;
import com.xingkesi.foodapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 我的需求列表
 */
public class MyReleaseListAdpter extends RecyclerView.Adapter<MyReleaseListAdpter.ViewHolder> {


    Context c;
    delData del;
    private List<MyReleaseBean.ListBean> adList;

    public MyReleaseListAdpter(Context c, delData del) {
        this.c = c;
        this.del = del;
        adList = new ArrayList<>();
    }

    public List<MyReleaseBean.ListBean> getArrayLists() {
        return adList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(c).inflate(R.layout.item_myrelease_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, final int position) {
        //-------------------
        MyReleaseBean.ListBean data = adList.get(position);
        String name =data.getTitle();
        vh.findshopTitle.setText(name);
        String content =data.getContent();
        StringBuffer sb=new StringBuffer();
        sb.append(data.getProvince()+" "+data.getCity()+" "+data.getDistrict());
        vh.findshopNum.setText(sb.toString());
        double Price =data.getPrice();
        vh.findshopSummongye.setText(Price+"");
        long dates =data.getSetTime();
        SimpleDateFormat sdf =new SimpleDateFormat("yy-MM-dd HH:ss");
        String da=sdf.format(new Date(dates));
        vh.newDate.setText(da);
        String url=data.getBannerList().get(0).getUrl();
        Picasso.with(c).load(Contantor.Imagepost+url).placeholder(R.drawable.aliwx_default_photo_right)
                .error(R.drawable.aliwx_fail_photo_right).into(vh.shopIocn);
        vh.findshopDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                del.del(position);
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return adList.size();
    }



    public interface delData {
        void del(int postion);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.shop_iocn)
        ImageView shopIocn;

        @Bind(R.id.findshop_title)
        TextView findshopTitle;
        @Bind(R.id.findshop_summongye)
        TextView findshopSummongye;
        @Bind(R.id.findshop_num)
        TextView findshopNum;

        @Bind(R.id.newDate)
        TextView newDate;
        @Bind(R.id.findshop_del)
        Button findshopDel;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
