package com.example.viewpagerdemo.ui.bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/30.
 * 店铺
 */
public class ShopInfoBean {

    /**
     * ids : null
     * seCode : null
     * id : 3632282114678831000
     * name : 小宝贝卖场
     * intro : 婴儿用品
     * province : 山东省
     * city : 济南市
     * district : 天桥区
     * address : 三孔桥
     * userId : 125
     * setTime : 1470585600000
     * icon : /images/account/default_head.jpg
     * vip : 1
     * status : 1
     */

    private String ids;
    private String seCode;
    private String id;
    private String name;
    private String intro;
    private String province;
    private String city;
    private String district;
    private String address;
    private String userId;
    private long setTime;
    private String icon;
    private String vip;
    private int status;

    ArrayList<ShopInfoListBean> goodsList;
    ArrayList<ShopInfoLunBean> bannerList;


    public ArrayList<ShopInfoLunBean> getBannerList() {
        return bannerList;
    }

    public void setBannerList(ArrayList<ShopInfoLunBean> bannerList) {
        this.bannerList = bannerList;
    }

    public ArrayList<ShopInfoListBean> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(ArrayList<ShopInfoListBean> goodsList) {
        this.goodsList = goodsList;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getSeCode() {
        return seCode;
    }

    public void setSeCode(String seCode) {
        this.seCode = seCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getSetTime() {
        return setTime;
    }

    public void setSetTime(long setTime) {
        this.setTime = setTime;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
