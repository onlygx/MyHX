package com.example.viewpagerdemo.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.viewpagerdemo.ui.activity.EatInfoActivity;
import com.example.viewpagerdemo.ui.bean.ShopInfoListBean;
import com.example.viewpagerdemo.ui.Contantor;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.squareup.picasso.Picasso;
import com.xingkesi.foodapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/28.
 * 店铺--->商品列表
 */
public class EatShopInfoListAdatper2 extends BaseAdapter {

    ArrayList<ShopInfoListBean> contentList;
    Context context;
    ImageView ball;// 小圆点
    StartBall balls;

    public EatShopInfoListAdatper2(Context context, ArrayList<ShopInfoListBean> contentList, StartBall balls) {
        this.context = context;
        this.contentList = contentList;
        this.balls = balls;
    }

    @Override
    public int getCount() {
        return contentList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder vh;
        final int pos = position;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.shopinfo2, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        if (position == 0) {
            vh.listImage.setVisibility(View.VISIBLE);
            vh.image_small.setVisibility(View.GONE);
            vh.listXs.setVisibility(View.VISIBLE);
            vh.postion0.setVisibility(View.VISIBLE);
            vh.ivKd.setVisibility(View.VISIBLE);
            // vh.listXs2.setVisibility(View.GONE);
            // vh.ivKd2.setVisibility(View.GONE);

        } else {
            vh.listImage.setVisibility(View.GONE);
            vh.image_small.setVisibility(View.VISIBLE);
            // vh.listXs2.setVisibility(View.VISIBLE);
            //vh.ivKd2.setVisibility(View.VISIBLE);
            vh.listXs.setVisibility(View.GONE);
            vh.ivKd.setVisibility(View.GONE);
            vh.postion0.setVisibility(View.GONE);
        }
        final ShopInfoListBean cb = contentList.get(position);
        int nums=cb.getCurrNum();
        if(nums>0){
           vh.jjNum.setVisibility(View.VISIBLE);
           vh.iv_num.setVisibility(View.VISIBLE);
            vh.iv_num.setText(nums+"");
        }
        DD.i("需要更新的位置================:"+nums);
        vh.listCongtent.setText(cb.getName());//商品名
        vh.listGongx.setText(cb.getContent());//介绍
        vh.money.setText(cb.getPrice() + "");//现价
        vh.listOldmoney.setText(cb.getPrice() + "");//原价
        vh.listOldmoney.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
        StringBuffer str = new StringBuffer();


        if (cb.getPeiSong() == 1) {
            str.append("配送");
        }
        if (cb.getPeiSong() == 1 && cb.getKuaiDi() == 1) {
            str.append("/快递");
        } else if (cb.getPeiSong() != 1 && cb.getKuaiDi() == 1) {
            str.append("快递");
        }
        if (cb.getPeiSong() == 1 && cb.getKuaiDi() == 1 && cb.getZiTi() == 1) {
            str.append("/自提");
        } else if (cb.getPeiSong() != 1 && cb.getKuaiDi() == 1 && cb.getZiTi() == 1) {
            str.append("/自提");
        } else if (cb.getPeiSong() != 1 && cb.getKuaiDi() != 1 && cb.getZiTi() == 1) {
            str.append("自提");
        }

        vh.tvKd.setText(str.toString());//原价
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String data = sdf.format(new Date(cb.getSetTime()));
        // vh.tvDate.setText(data);


        if (position == 0) {
            if (cb.getBannerList().size() > 0) {
                Picasso.with(context).load(Contantor.Imagepost + cb.getBannerList().get(0).getUrl()).
                        placeholder(R.drawable.logding)
                        .error(R.drawable.logding).into(vh.listImage);
            } else {
                Picasso.with(context).load(R.drawable.logding).
                        into(vh.image_small);
            }

            if (cb.getBaoYou() == 1) {
                vh.ivKd.setVisibility(View.VISIBLE);
            }
        } else {
            if (cb.getBannerList().size() > 0) {
                Picasso.with(context).load(Contantor.Imagepost + cb.getBannerList().get(0).getUrl()).
                        placeholder(R.drawable.logding)
                        .error(R.drawable.logding).into(vh.image_small);
            } else {
                Picasso.with(context).load(R.drawable.logding).
                        into(vh.image_small);
            }
            if (cb.getBaoYou() == 1) {
                vh.ivKd2.setVisibility(View.VISIBLE);
            }
        }

        vh.image_small.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it =new Intent(context, EatInfoActivity.class);
                it.putExtra("id",cb.getId()+"");
                it.putExtra("shopID",cb.getShopId()+"");
                it.putExtra("name",cb.getName()+"");
                context.startActivity(it);
            }
        });
        vh.listCongtent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it =new Intent(context, EatInfoActivity.class);
                it.putExtra("id",cb.getId()+"");
                it.putExtra("shopID",cb.getShopId()+"");
                it.putExtra("name",cb.getName()+"");
                context.startActivity(it);
            }
        });


        vh.addNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vh.jjNum.getVisibility() == View.INVISIBLE) {
                    vh.jjNum.setVisibility(View.VISIBLE);
                }
                if (vh.iv_num.getVisibility() == View.INVISIBLE) {
                    vh.iv_num.setVisibility(View.VISIBLE);
                }
                //添加
                int[] startLocation = new int[2];// 一个整型数组，用来存储按钮的在屏幕的X、Y坐标
                v.getLocationInWindow(startLocation);// 这是获取购买按钮的在屏幕的X、Y坐标（这也是动画开始的坐标）
                ball = new ImageView(context);// buyImg是动画的图片，我的是一个小球（R.drawable.sign）
                ball.setImageResource(R.drawable.sign);// 设置buyImg的图片
                cb.setCurrNum(cb.getCurrNum() + 1);
                cb.setCurrPostion(pos);
                balls.setAnim(pos, ball, startLocation);// 开始执行动画
                vh.iv_num.setText(cb.getCurrNum() + "");
                notifyDataSetChanged();

            }
        });

        vh.jjNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb.getCurrNum() == 0) {
                    vh.jjNum.setVisibility(View.INVISIBLE);
                    vh.iv_num.setVisibility(View.INVISIBLE);
                    cb.setCurrPostion(-1);
                    return;
                }
                int[] startLocation = new int[2];// 一个整型数组，用来存储按钮的在屏幕的X、Y坐标
                v.getLocationInWindow(startLocation);// 这是获取购买按钮的在屏幕的X、Y坐标（这也是动画开始的坐标）
                ball = new ImageView(context);// buyImg是动画的图片，我的是一个小球（R.drawable.sign）
                ball.setImageResource(R.drawable.sign);// 设置buyImg的图片

                cb.setCurrNum(cb.getCurrNum() - 1);
                balls.setJJAnim(pos, ball, startLocation);
                vh.iv_num.setText(cb.getCurrNum() + "");
                notifyDataSetChanged();

                if (cb.getCurrNum() == 0) {
                    cb.setCurrPostion(-1);
                    vh.jjNum.setVisibility(View.INVISIBLE);
                    vh.iv_num.setVisibility(View.INVISIBLE);
                }
            }
        });

        return convertView;
    }

    public void UpdataNum(int postion){

        DD.i("需要更新的位置:"+postion);
        ShopInfoListBean fb= contentList.get(postion);
        int num=fb.getCurrNum();
        int nums=num+1;
        DD.i("更新的位置的数量:"+num+"加1后:"+nums);
        fb.setCurrNum(nums);
        notifyDataSetChanged();
    }


    static class ViewHolder {
        @Bind(R.id.list_image)
        ImageView listImage;
        @Bind(R.id.image_small)
        ImageView image_small;
        @Bind(R.id.list_congtent)
        TextView listCongtent;
        @Bind(R.id.list_gongx)
        TextView listGongx;
        @Bind(R.id.list_xs)
        ImageView listXs;
        @Bind(R.id.iv_kd)
        ImageView ivKd;
        /* @Bind(R.id.list_xs2)
         ImageView listXs2;//*/
        @Bind(R.id.iv_kd2)
        ImageView ivKd2;
        @Bind(R.id.tv_kd)
        TextView tvKd;
        @Bind(R.id.money)
        TextView money;
        @Bind(R.id.list_oldmoney)
        TextView listOldmoney;
        @Bind(R.id.iv_num)
        TextView iv_num;//数量
        @Bind(R.id.jjNum)
        ImageView jjNum;//减
        @Bind(R.id.addNum)
        ImageView addNum;
        @Bind(R.id.postion0)
        LinearLayout postion0;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


    public interface StartBall {
        void setAnim(int sb, ImageView ball, int[] startLocation);

        void setJJAnim(int sb, ImageView ball, int[] startLocation);
    }
}
