package com.example.viewpagerdemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.viewpagerdemo.ui.bean.ShenQBean;
import com.example.viewpagerdemo.ui.bean.ShoppingListBanerBean;
import com.example.viewpagerdemo.ui.jlfragmenwork.Contantor;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.CircleImageView;
import com.squareup.picasso.Picasso;
import com.xingkesi.foodapp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 首页列表适配器
 */
public class ShenQAdpter extends RecyclerView.Adapter<ShenQAdpter.ViewHolder> {


    Context c;
    ArrayList<ShenQBean> list;
    private List<ShoppingListBanerBean> adList;
    ShenqC sc;
    public ShenQAdpter(Context c,ShenqC sc) {
        this.c = c;
        this.sc = sc;
        list = new ArrayList<>();
    }

    public ArrayList<ShenQBean> getArrayLists() {
        return list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(c).inflate(R.layout.item_shenqing_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, final int position) {
        //-------------------
        ShenQBean data = list.get(position);
        final String id=data.getId();
        ShenQBean.FriendUserBean fu=data.getFriendUser();
        vh.shenqName.setText(fu.getNickName());
        String url = Contantor.Imagepost+fu.getHead();
        if(!url.equals("")){
            Picasso.with(c).load(Contantor.Imagepost + url).placeholder(R.drawable.aliwx_default_photo_right)
                    .error(R.drawable.aliwx_fail_photo_right).into(vh.shenqIcon);
        }




        vh.shenqOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sc.request(position,id,"1");
            }
        });
        vh.shenqNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sc.request(position,id,"0");

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
        @Bind(R.id.shenq_icon)
        CircleImageView shenqIcon;
        @Bind(R.id.shenq_name)
        TextView shenqName;
        @Bind(R.id.shenq_ok)
        TextView shenqOk;
        @Bind(R.id.shenq_no)
        TextView shenqNo;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    public interface   ShenqC{
        void request(int pos,String id,String state);
    }
}
