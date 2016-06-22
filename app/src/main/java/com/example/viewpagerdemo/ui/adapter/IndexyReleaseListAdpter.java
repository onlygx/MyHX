package com.example.viewpagerdemo.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.viewpagerdemo.ui.activity.IndexMainActivity;
import com.example.viewpagerdemo.ui.bean.IndexRaleaseBean;
import com.example.viewpagerdemo.ui.bean.MyReleaseBean;
import com.example.viewpagerdemo.ui.jlfragmenwork.Contantor;
import com.squareup.picasso.Picasso;
import com.xingkesi.foodapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 首页需求列表
 */
public class IndexyReleaseListAdpter extends RecyclerView.Adapter<IndexyReleaseListAdpter.ViewHolder> {


    Context c;
    delData del;
    private List<IndexRaleaseBean.ListBean> adList;

    public IndexyReleaseListAdpter(Context c) {
        this.c = c;
        adList = new ArrayList<>();
    }

    public List<IndexRaleaseBean.ListBean> getArrayLists() {
        return adList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(c).inflate(R.layout.item_indexrelease_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, final int position) {
        //-------------------
        final IndexRaleaseBean.ListBean data = adList.get(position);
        String name =data.getTitle();
        vh.findshopDel.setVisibility(View.INVISIBLE);
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
        if(data.getBannerList().size()>0) {
            String url = data.getBannerList().get(0).getUrl();
            Picasso.with(c).load(Contantor.Imagepost + url).placeholder(R.drawable.aliwx_default_photo_right)
                    .error(R.drawable.aliwx_fail_photo_right).into(vh.shopIocn);
        }
        vh.index_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it =new Intent(c, IndexMainActivity.class);
                it.putExtra("tag",0);
                it.putExtra("id",data.getId()+"");
                c.startActivity(it);

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
        @Bind(R.id.index_ll)
        LinearLayout index_ll;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
