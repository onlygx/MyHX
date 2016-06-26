package com.example.viewpagerdemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.viewpagerdemo.ui.bean.FindDDListShopBean;
import com.example.viewpagerdemo.ui.bean.TalkingBean;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.xingkesi.foodapp.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 与我相关
 * Created by Administrator on 2016/5/20.
 */
public class TalkViewAdpter extends RecyclerView.Adapter<TalkViewAdpter.ViewHolder> {

    Context c;
    ArrayList<TalkingBean> list;


    public TalkViewAdpter(Context c) {
        this.c = c;
        this.list = new ArrayList<>();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(c).inflate(R.layout.item_talking, parent, false);
        ViewHolder viewHolder = new ViewHolder(convertView);
        return viewHolder;
    }

    public ArrayList getArrayList() {
        return list;
    }

    @Override
    public void onBindViewHolder(final ViewHolder vh, int position) {
        DD.v("位置position:"+position);
        final TalkingBean data = list.get(position);
        // 一个是说说，
        TalkingBean.TalkBean tb = data.getTalk();
        // 一个是评论
        TalkingBean.RecordBean trb = data.getRecord();

        vh.talkName.setText(data.getSendUser().getNickName());
        //
        if (data.getType() == 1) {
            vh.talkContent.setText(tb.getContent());
            vh.talkContentHF.setText(data.getContent());
        } else {
            vh.talkContent.setText(data.getContent());
            vh.talkContentHF.setText(trb.getContent());
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
        @Bind(R.id.shop_iocn)
        ImageView shopIocn;
        @Bind(R.id.talk_name)
        TextView talkName;
        @Bind(R.id.talk_contentHF)
        TextView talkContentHF;
        @Bind(R.id.talk_content)
        TextView talkContent;


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
