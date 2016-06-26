package com.example.viewpagerdemo.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.example.viewpagerdemo.ui.adapter.EatShopInfoListAdatper2;
import com.example.viewpagerdemo.ui.adapter.EatShopInfoListAdatperCar;
import com.example.viewpagerdemo.ui.bean.ShopInfoBean;
import com.example.viewpagerdemo.ui.bean.ShopInfoListBean;
import com.example.viewpagerdemo.ui.Contantor;
import com.example.viewpagerdemo.ui.jlfragmenwork.baseactivitywork.JLBaseActivity;
import com.example.viewpagerdemo.ui.jlfragmenwork.util.DD;
import com.example.viewpagerdemo.ui.jlfragmenwork.view.BadgeView;
import com.example.viewpagerdemo.ui.units.StringUtils;
import com.xingkesi.foodapp.R;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.Bind;

/**
 * 店铺详情
 */
public class EatShopInfoActivity extends JLBaseActivity implements View.OnClickListener,
        EatShopInfoListAdatper2.StartBall ,EatShopInfoListAdatperCar.CarInterfaceClass{

    //-----------顶图--start-----------------------------
    //-----------左侧--------------
    @Bind(R.id.iv_back)
    ImageView iv_back;
    @Bind(R.id.tv_title)
    TextView tv_title;
    //------------右侧----------------------
    @Bind(R.id.iv_right_image)
    ImageView iv_right_image;
    @Bind(R.id.tv_right_text)
    TextView tv_right_text;
    //-----------顶图--end-----------------------------

    //-----------body--------------------------
    @Bind(R.id.shoppingName)//简介
            TextView shoppingName;
    @Bind(R.id.shopme)//下单
            TextView shopme;
   /* @Bind(R.id.addrson)//位置
            TextView addrson;*/
   /* @Bind(R.id.shop_name)//主打菜
            TextView shop_name;*/
    @Bind(R.id.address)//省区
            TextView address;
    @Bind(R.id.money_num)//
            TextView money_num;
    @Bind(R.id.carlist)//
            RecyclerView carlist;

   /* @Bind(R.id.master_iv)
    ImageView master_iv;//头像*/
    @Bind(R.id.mylist)
    ListView mylist;//评价列表
    @Bind(R.id.car_out)
    LinearLayout car_out;//评价列表
    @Bind(R.id.car_layou)
    LinearLayout car_layou;//评价列表


    ArrayList<ShopInfoListBean> goodsList;

    int height;
    String id, name;

    ViewGroup anim_mask_layout;//动画层
    @Bind(R.id.shopCart)//
            ImageView shopCart;//购物车
    int buyNum = 0;//购买数量
    BadgeView buyNumView;//购物车上的数量标签

    ArrayList<ShopInfoListBean> carLiat;

    boolean isCarShow = false;
    EatShopInfoListAdatper2 es;

    @Override
    public int setViewLayout() {
        return R.layout.eat_shop_info;
    }


    @Override
    public void initID() {
        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        DD.w("这里是店铺详情：" + id + "==" + name);
        goodsList = new ArrayList<>();
        carLiat = new ArrayList<>();
    }

    @Override
    public int setColor() {
        return super.setColor();
    }


    //店铺详情列表
    void getShopInfo() {
        AjaxParams map = new AjaxParams();
        map.put("id", id);
        String url = Contantor.shopInfo;
        new FinalHttp().post(url, map, new AjaxCallBack<String>() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                DD.w("商铺详情:" + s);
                ShopInfoBean sb = JSONObject.parseObject(s, ShopInfoBean.class);
                goodsList = sb.getGoodsList();
                //-----------------------------------------------------
                shoppingName.setText(sb.getIntro());//简介
                address.setText(sb.getAddress());//地址

                //---------------商品列表----------------------------------
                es = new EatShopInfoListAdatper2(EatShopInfoActivity.this, goodsList, EatShopInfoActivity.this);
                mylist.setAdapter(es);
                es.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }
        });

    }

    @Override
    public void initObject() {
        buyNumView = new BadgeView(this, shopCart);
        buyNumView.setTextColor(Color.WHITE);
        buyNumView.setBackgroundColor(Color.RED);
        buyNumView.setTextSize(12);
        iv_back.setVisibility(View.VISIBLE);
        iv_back.setImageResource(R.drawable.cp_fh);
        iv_right_image.setVisibility(View.VISIBLE);
        iv_right_image.setImageResource(R.drawable.cp_xx);
        Drawable drawable= getResources().getDrawable(R.drawable.fenxiang);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tv_right_text.setCompoundDrawables(drawable,null,null,null);

        tv_title.setText(name);

        WindowManager wm = EatShopInfoActivity.this.getWindowManager();
        height = wm.getDefaultDisplay().getHeight();
        //
        iv_right_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //评论页
                Intent it = new Intent(EatShopInfoActivity.this, CommentsActivity.class);
                it.putExtra("id", id);
                startActivity(it);
            }
        });

        //---------------商品列表数据----------------------------------
        goodsList = new ArrayList<>();
        es = new EatShopInfoListAdatper2(getApplicationContext(), goodsList, this);
        mylist.setAdapter(es);

        getShopInfo();


        //下单
        shopme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(carLiat==null || carLiat.size()<=0){
                    Toast.makeText(EatShopInfoActivity.this, "请选择需要购买的商品！", Toast.LENGTH_SHORT).show();
                    return;
                }
                DD.v("这是ID:"+id);
                Intent it =new Intent(EatShopInfoActivity.this, ShoppingDDCarActivity.class);
                it.putExtra("id",id);
                it.putExtra("car",(Serializable)carLiat);
                startActivity(it);
            }
        });


        car_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] location = new int[2];
                carlist.getLocationOnScreen(location);
                int y = location[1];
                TranslateAnimation tan = new TranslateAnimation(0, 0, y, height);
                tan.setDuration(800);
                carlist.startAnimation(tan);
                tan.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        car_out.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        carlist.setVisibility(View.GONE);
                        car_layou.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });
        //购物车
        shopCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                LinearLayoutManager man = new LinearLayoutManager(EatShopInfoActivity.this);
                man.setOrientation(LinearLayoutManager.VERTICAL);

                if (carLiat.size() > 0) {
                    if (!isCarShow) {//显示
                        isCarShow = true;
                        car_layou.setVisibility(View.VISIBLE);
                        car_layou.setAlpha(0.0f);
                        car_out.setVisibility(View.VISIBLE);
                        car_out.setAlpha(0.0f);
                        carlist.setVisibility(View.VISIBLE);
                        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.pop);
                        carlist.setLayoutManager(man);
                        carlist.startAnimation(animation);
                        EatShopInfoListAdatperCar ac = new EatShopInfoListAdatperCar(EatShopInfoActivity.this, carLiat,EatShopInfoActivity.this);
                        carlist.setAdapter(ac);

                        animation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {
                                car_layou.setAlpha(1.0f);
                                car_out.setAlpha(1.0f);
                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });

                    } else {
                        isCarShow = false;

                        int[] location = new int[2];
                        carlist.getLocationOnScreen(location);
                        int y = location[1];
                        TranslateAnimation tan = new TranslateAnimation(0, 0, y, height);
                        tan.setDuration(800);
                        carlist.startAnimation(tan);
                        tan.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {
                                car_out.setVisibility(View.GONE);
                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                carlist.setVisibility(View.GONE);
                                car_layou.setVisibility(View.GONE);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                    }
                }
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.allcontent:

                break;
        }
    }

    /**
     * @param
     * @return void
     * @throws
     * @Description: 创建动画层
     */
    private ViewGroup createAnimLayout() {
        ViewGroup rootView = (ViewGroup) this.getWindow().getDecorView();
        LinearLayout animLayout = new LinearLayout(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        animLayout.setLayoutParams(lp);
        animLayout.setId(Integer.MAX_VALUE);//2147483647
        animLayout.setBackgroundResource(android.R.color.transparent);
        rootView.addView(animLayout);
        return animLayout;
    }

    private View addViewToAnimLayout(final ViewGroup parent, final View view,
                                     int[] location) {
        int x = location[0];
        int y = location[1];
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = x;
        lp.topMargin = y;
        view.setLayoutParams(lp);
        return view;
    }

    private void setAnims(final View v, int[] startLocation) {
        anim_mask_layout = null;
        anim_mask_layout = createAnimLayout();
        anim_mask_layout.addView(v);//把动画小球添加到动画层
        final View view = addViewToAnimLayout(anim_mask_layout, v,
                startLocation);
        int[] endLocation = new int[2];// 存储动画结束位置的X、Y坐标
        shopCart.getLocationInWindow(endLocation);// shopCart是那个购物车

        // 计算位移
        int endX = 0 - startLocation[0] + 210;// 动画位移的X坐标
        int endY = endLocation[1] - startLocation[1];// 动画位移的y坐标
        TranslateAnimation translateAnimationX = new TranslateAnimation(0,
                endX, 0, 0);
        translateAnimationX.setInterpolator(new LinearInterpolator());
        translateAnimationX.setRepeatCount(0);// 动画重复执行的次数
        translateAnimationX.setFillAfter(true);

        TranslateAnimation translateAnimationY = new TranslateAnimation(0, 0,
                0, endY);
        translateAnimationY.setInterpolator(new AccelerateInterpolator());
        translateAnimationY.setRepeatCount(0);// 动画重复执行的次数
        translateAnimationX.setFillAfter(true);

        AnimationSet set = new AnimationSet(false);
        set.setFillAfter(false);
        set.addAnimation(translateAnimationY);
        set.addAnimation(translateAnimationX);
        set.setDuration(800);// 动画的执行时间
        view.startAnimation(set);
        // 动画监听事件
        set.setAnimationListener(new Animation.AnimationListener() {
            // 动画的开始
            @Override
            public void onAnimationStart(Animation animation) {
                v.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }

            // 动画的结束
            @Override
            public void onAnimationEnd(Animation animation) {
                v.setVisibility(View.GONE);
                buyNum++;//让购买数量加1
                buyNumView.setText(buyNum + "");//
                buyNumView.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
                buyNumView.show();
            }
        });

    }


    @Override
    public void setAnim(int sb, ImageView ball, int[] startLocation) {
        ShopInfoListBean sif = goodsList.get(sb);
        if (carLiat.size() == 0) {
            carLiat.add(sif);
        } else {
            int tag=0;
            for (int i = 0; i < carLiat.size(); i++) {
                ShopInfoListBean si = carLiat.get(i);
                DD.i(sif.getId()+"==="+si.getId());
                if (sif.getId() == si.getId()) {
                    tag=1;
                    break;
                }
            }
            if(tag==0){
                carLiat.add(sif);
            }
        }
        double money = sif.getPrice();
        setAnims(ball, startLocation);
        String str = money_num.getText().toString();
        double txtMoney;
        if (str == null || str.equals("")) {
            txtMoney = Double.parseDouble("0");
        } else {
            txtMoney = Double.parseDouble(str);
        }
        double num = txtMoney + money;
        money_num.setText(StringUtils.toTwoDouble(num) + "");
    }

    @Override
    public void setJJAnim(int sb, ImageView ball, int[] startLocation) {
        buyNum--;//让购买数量加1
        buyNumView.setText(buyNum + "");//
        ShopInfoListBean sif = goodsList.get(sb);

       // DD.v(carLiat.size() + "循环jian移除前甲烷：" + sif.getCurrNum());

        if (carLiat.size() > 0 &&  sif.getCurrNum()==0) {
                for (int i = 0; i < carLiat.size(); i++) {
                    ShopInfoListBean si = carLiat.get(i);
                    if (sif.getId() == si.getId()) {
                        carLiat.remove(i);
                    }
                }
        }

        double money = sif.getPrice();
        String str = money_num.getText().toString();
        double txtMoney;
        if (str == null || str.equals("")) {
            txtMoney = Double.parseDouble("0");
        } else {
            txtMoney = Double.parseDouble(str);
        }
        double num = txtMoney - money;

        money_num.setText(num + "");
      //  DD.v(carLiat.size() + "循环jian移除--后甲烷：" + sif.getCurrNum());

        money_num.setText(StringUtils.toTwoDouble(num) + "");

    }

    @Override
    public void AddAnim(int pos) {
        //+++++更改商铺下商品数量 并更改金额
        int postion=carLiat.get(pos).getCurrPostion();
        ShopInfoListBean sif =goodsList.get(postion);
        es.notifyDataSetChanged();

        double money = sif.getPrice();
        String str = money_num.getText().toString();
        double txtMoney;
        if (str == null || str.equals("")) {
            txtMoney = Double.parseDouble("0");
        } else {
            txtMoney = Double.parseDouble(str);
        }
        double num = txtMoney + money;
        money_num.setText(StringUtils.toTwoDouble(num) + "");

       // v.setVisibility(View.GONE);
        buyNum++;//让购买数量加1
        buyNumView.setText(buyNum + "");//
        buyNumView.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
        buyNumView.show();

    }

    @Override
    public void JJAnim(int pos) {
        //-----更改商铺下商品数量 并更改金额
        int postion=carLiat.get(pos).getCurrPostion();
        ShopInfoListBean sif =goodsList.get(postion);
        es.notifyDataSetChanged();

        double money = sif.getPrice();
        String str = money_num.getText().toString();
        double txtMoney;
        if (str == null || str.equals("")) {
            txtMoney = Double.parseDouble("0");
        } else {
            txtMoney = Double.parseDouble(str);
        }
        double num = txtMoney - money;
        money_num.setText(StringUtils.toTwoDouble(num) + "");

        //v.setVisibility(View.GONE);
        buyNum--;//让购买数量加1
        buyNumView.setText(buyNum + "");//
        buyNumView.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
        buyNumView.show();
    }
}
