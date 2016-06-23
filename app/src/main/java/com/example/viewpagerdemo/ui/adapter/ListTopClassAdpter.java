package com.example.viewpagerdemo.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.viewpagerdemo.ui.activity.OhterListMainActivity;
import com.example.viewpagerdemo.ui.bean.ListTopBean;
import com.example.viewpagerdemo.ui.jlfragmenwork.Contantor;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.CircleImageView;
import com.squareup.picasso.Picasso;
import com.xingkesi.foodapp.R;
import java.util.ArrayList;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 首页列表适配器
 */
public class ListTopClassAdpter extends RecyclerView.Adapter<ListTopClassAdpter.ViewHolder> {

    Context context;
    ArrayList<ListTopBean> list;

    public ListTopClassAdpter(Context c) {
        this.context = c;
        list = new ArrayList<>();
    }
    public  ArrayList<ListTopBean> getArrayLists(){
        return list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(context).inflate(R.layout.adapter_listtop_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, final int position) {

        WindowManager wm = (WindowManager)context
                .getSystemService(Context.WINDOW_SERVICE);

        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();

        final ListTopBean data = list.get(position);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width/4,width/4);
        vh.img_class.setLayoutParams(params);
        //店铺头像
        Picasso.with(context).load(Contantor.Imagepost+data.getIcon()).
                placeholder(R.drawable.logding)
                .error(R.drawable.dialogpop_bg2).into(  vh.img_class);
        vh.tv_name.setText(data.getName());

        vh.img_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, OhterListMainActivity.class);
                Toast.makeText(context,data.getType(),Toast.LENGTH_LONG);
                intent.putExtra("Type_Id",data.getId()+"");
                context.startActivity(intent);
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
        @Bind(R.id.img_class)
        CircleImageView img_class;
        @Bind(R.id.tv_name)
        TextView tv_name;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
