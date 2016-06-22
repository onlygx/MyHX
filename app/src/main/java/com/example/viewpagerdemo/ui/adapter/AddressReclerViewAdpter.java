package com.example.viewpagerdemo.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.viewpagerdemo.ui.activity.AddressActivity;
import com.example.viewpagerdemo.ui.activity.FindAddreesActivity;
import com.example.viewpagerdemo.ui.bean.AddressListBean;
import com.xingkesi.foodapp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 收货地址
 * Created by Administrator on 2016/5/20.
 */
public class AddressReclerViewAdpter extends RecyclerView.Adapter<AddressReclerViewAdpter.ViewHolder> {

    Context c;
    List<AddressListBean> list;
    boolean check=false;
    CheckClassReq  req;

    public AddressReclerViewAdpter(Context c, List<AddressListBean> list,boolean check,CheckClassReq  req) {
        this.c = c;
        this.list = list;
        this.check = check;
        this.req = req;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(c).inflate(R.layout.item_addrees_reclerviewadpter, parent, false);
        ViewHolder viewHolder = new ViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, final int position) {
        AddressListBean data = list.get(position);
        final String id =data.getId();
        final String name =data.getName();
        final String pro =data.getProvince();
        final String dro =data.getDistrict();
        final String city =data.getCity();
        final String tel=data.getPhone();
        final String addres=data.getAddress();
        final String add=pro + " " + city + dro + addres;
        vh.name.setText(name);
        vh.tel.setText(tel);
        vh.add.setText(add);


        //编辑
        vh.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                、、pro, city, dro
                Intent it =new Intent(c, AddressActivity.class);
                it.putExtra("tag",false);
                it.putExtra("id",id);
                it.putExtra("name",name);
                it.putExtra("tel",tel);

                it.putExtra("pro",pro);
                it.putExtra("city",city);
                it.putExtra("dro",dro);
                it.putExtra("addres",addres);

                c.startActivity(it);
            }
        });


        //选择
        vh.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check){
                    req.CheckCall(position);
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
        return list.size();
    }




    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.name)
        TextView name;
        @Bind(R.id.tel)
        TextView tel;
        @Bind(R.id.add)
        TextView add;
        @Bind(R.id.edit)
        ImageView edit;
        @Bind(R.id.ll)
        LinearLayout ll;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


  public   interface   CheckClassReq{

         void CheckCall(int pos);

    }
}
