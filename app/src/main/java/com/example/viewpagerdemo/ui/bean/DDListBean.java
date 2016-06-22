package com.example.viewpagerdemo.ui.bean;

/**
 * Created by Administrator on 2016/6/3.
 * 订单列表
 */
public class DDListBean {

    int shop_id;//商家id
    int id;//商品id
    String shop_name;//商家名
    String shop_title;//商品名
    int shop_money;//单价
    int shop_ymoney;//运费
    int shop_znum;//合计数量
    int shop_zmoney;//合计费用
    int shop_num;//数量
    boolean isshiYh;//是否优惠
    //
    int fnum;
    int fmoney;


    public int getFnum() {
        return fnum;
    }

    public void setFnum(int fnum) {
        this.fnum = fnum;
    }

    public int getFmoney() {
        return fmoney;
    }

    public void setFmoney(int fmoney) {
        this.fmoney = fmoney;
    }

    public int getShop_num() {
        return shop_num;
    }

    public void setShop_num(int shop_num) {
        this.shop_num = shop_num;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_title() {
        return shop_title;
    }

    public void setShop_title(String shop_title) {
        this.shop_title = shop_title;
    }

    public int getShop_money() {
        return shop_money;
    }

    public void setShop_money(int shop_money) {
        this.shop_money = shop_money;
    }

    public int getShop_ymoney() {
        return shop_ymoney;
    }

    public void setShop_ymoney(int shop_ymoney) {
        this.shop_ymoney = shop_ymoney;
    }

    public int getShop_znum() {
        return shop_znum;
    }

    public void setShop_znum(int shop_ynum) {
        this.shop_znum = shop_ynum;
    }

    public int getShop_zmoney() {
        return shop_zmoney;
    }

    public void setShop_zmoney(int shop_zmoney) {
        this.shop_zmoney = shop_zmoney;
    }

    public boolean isshiYh() {
        return isshiYh;
    }

    public void setIsshiYh(boolean isshiYh) {
        this.isshiYh = isshiYh;
    }
}
