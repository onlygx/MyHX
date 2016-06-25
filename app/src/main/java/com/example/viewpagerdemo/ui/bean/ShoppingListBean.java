package com.example.viewpagerdemo.ui.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/24.
 */
public class ShoppingListBean {


    private String ids;
    private String seCode;
    private int id;
    private String name;
    private String intro;
    private double price;
    private int peiSong;
    private int kuaiDi;
    private int ziTi;
    private int baoYou;
    private int youPrice;
    private int sort;
    private long shopId;
    private long setTime;
    private String content;
    private List<ShoppingListBanerBean> bannerList;
    private int bannerSelect;
    private String shopName;
    private String shopAddress;
    private ShoppingListInfoBean shop;

    private int isBanner = 0;//是否是banner

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPeiSong() {
        return peiSong;
    }

    public void setPeiSong(int peiSong) {
        this.peiSong = peiSong;
    }

    public int getKuaiDi() {
        return kuaiDi;
    }

    public void setKuaiDi(int kuaiDi) {
        this.kuaiDi = kuaiDi;
    }

    public int getZiTi() {
        return ziTi;
    }

    public void setZiTi(int ziTi) {
        this.ziTi = ziTi;
    }

    public int getBaoYou() {
        return baoYou;
    }

    public void setBaoYou(int baoYou) {
        this.baoYou = baoYou;
    }

    public int getYouPrice() {
        return youPrice;
    }

    public void setYouPrice(int youPrice) {
        this.youPrice = youPrice;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public long getSetTime() {
        return setTime;
    }

    public void setSetTime(long setTime) {
        this.setTime = setTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<ShoppingListBanerBean> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<ShoppingListBanerBean> bannerList) {
        this.bannerList = bannerList;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public ShoppingListInfoBean getShop() {
        return shop;
    }

    public void setShop(ShoppingListInfoBean shop) {
        this.shop = shop;
    }

    public int getBannerSelect() {
        return bannerSelect;
    }

    public void setBannerSelect(int bannerSelect) {
        this.bannerSelect = bannerSelect;
    }

    public int getIsBanner() {
        return isBanner;
    }

    public void setIsBanner(int isBanner) {
        this.isBanner = isBanner;
    }
}
