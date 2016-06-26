package com.example.viewpagerdemo.ui.bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/24.
 */
public class ShopingBean {

    private String page;
    private String size;
    private String province;
    private String city;
    private String district;

    ArrayList<ShoppingListBean> list;


    public ArrayList<ShoppingListBean> getList() {
        return list;
    }

    public void setList(ArrayList<ShoppingListBean> list) {
        this.list = list;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
