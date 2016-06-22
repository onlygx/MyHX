package com.example.viewpagerdemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.viewpagerdemo.ui.bean.ContentListBean;
import com.example.viewpagerdemo.ui.jlfragmenwork.Contantor;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.squareup.picasso.Picasso;
import com.xingkesi.foodapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.Bind;

/**
 * Created by Administrator on 2016/5/28.
 */
public class EatInfoContentReclerAdatper extends RecyclerView.Adapter<EatInfoContentReclerAdatper.ViewHolder> {

    ArrayList<ContentListBean> contentList;
    Context context;

    public EatInfoContentReclerAdatper(Context context) {
        this.context = context;
        this.contentList = new ArrayList<>();
    }
    public  ArrayList<ContentListBean> getArrayLists(){
        return contentList;
    }
    @Override
    public EatInfoContentReclerAdatper.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(context).inflate(R.layout.eatcontentlayout, parent, false);
        ViewHolder viewHolder = new ViewHolder(convertView);
        return viewHolder;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public void onBindViewHolder(EatInfoContentReclerAdatper.ViewHolder vh, int position) {

        ContentListBean cb = contentList.get(position);
        DD.i("绑定:"+cb.getUserName());

        vh.tvContent.setText(cb.getContent());
        vh.tvName.setText(cb.getUserName());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String data = sdf.format(new Date(cb.getSetTime()));
        vh.tvDate.setText(data);
        Picasso.with(context).load(Contantor.Imagepost + cb.getUserHead()).
                placeholder(R.drawable.logding)
                .error(R.drawable.logding).
                into(vh.ivContent);

    }

    @Override
    public int getItemCount() {
        return contentList.size();
    }


    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'eatcontentlayout.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_content)
        TextView tvContent;
        @Bind(R.id.iv_content)
        ImageView ivContent;
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_date)
        TextView tvDate;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
