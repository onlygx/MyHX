package com.example.viewpagerdemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.viewpagerdemo.ui.Contantor;
import com.example.viewpagerdemo.ui.bean.FindDDListShopBean;
import com.example.viewpagerdemo.ui.bean.TalkingBean;
import com.squareup.picasso.Picasso;
import com.xingkesi.foodapp.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 与我相关
 * Created by Administrator on 2016/5/20.
 */
public class TalkViewAdpter2 extends RecyclerView.Adapter<TalkViewAdpter2.ViewHolder> {

    Context c;
    ArrayList<TalkingBean> list;


    public TalkViewAdpter2(Context c) {
        this.c = c;
        this.list = new ArrayList<>();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(c).inflate(R.layout.item_talking2, parent, false);
        ViewHolder viewHolder = new ViewHolder(convertView);
        return viewHolder;
    }

    public ArrayList getArrayList() {
        return list;
    }

    @Override
    public void onBindViewHolder(final ViewHolder vh, int position) {
        TalkingBean tal = list.get(position);
        int type = tal.getType();

        vh.talkinName.setText(tal.getSendUser().getNickName());
        Picasso.with(c).load(Contantor.Imagepost+tal.getSendUser().getHead()).into(vh.talk_icon);
        if (type == 2) {
            vh.talkinContent.setText(tal.getContent());
            vh.talkinHuifu.setText(tal.getRecord().getContent());

        } else if (type == 1) {
            vh.talkinContent.setText(tal.getTalk().getContent());
            vh.talkinHuifu.setText(tal.getContent());

        } else if (type == 0) {
            vh.talkin_zan.setVisibility(View.VISIBLE);
            vh.talkinContent.setText(tal.getTalk().getContent());
        }


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
        @Bind(R.id.talkin_name)
        TextView talkinName;
        @Bind(R.id.conlayout)
        LinearLayout conlayout;
        @Bind(R.id.talkin_content)
        TextView talkinContent;
        @Bind(R.id.talkin_huifu)
        TextView talkinHuifu;
        @Bind(R.id.talkin_zan)
        ImageView talkin_zan;
        @Bind(R.id.talk_icon)
        ImageView talk_icon;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
