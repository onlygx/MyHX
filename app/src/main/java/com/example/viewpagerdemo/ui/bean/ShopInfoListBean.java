package com.example.viewpagerdemo.ui.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/30.
 * 店铺下的列表
 */
public class ShopInfoListBean implements Serializable{

    /**
     * ids : null
     * seCode : null
     * id : 363228211
     * name : 婴儿奶嘴
     * intro : 
     * price : 23.5
     * peiSong : 1
     * kuaiDi : 1
     * ziTi : 1
     * baoYou : 1
     * youPrice : 0
     * sort : 1
     * shopId : 3632282114678831000
     * setTime : 1463899276000
     * content : 这里是网页简介
     * bannerList : null
     * shopName : null
     * shopAddress : null
     */

    int currNum;
    int currPostion;
    private String ids;
    private String title;
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
    private String shopName;
    private String shopAddress;
    int fnum;
    double fmoney;
    ArrayList<ShopInfoListBannerBean> bannerList;


    public int getFnum() {
        return fnum;
    }

    public void setFnum(int fnum) {
        this.fnum = fnum;
    }

    public double getFmoney() {
        return fmoney;
    }

    public void setFmoney(double fmoney) {
        this.fmoney = fmoney;
    }

    public int getCurrPostion() {
        return currPostion;
    }

    public void setCurrPostion(int currPostion) {
        this.currPostion = currPostion;
    }

    public int getCurrNum() {
        return currNum;
    }

    public void setCurrNum(int currNum) {
        this.currNum = currNum;
    }

    public ArrayList<ShopInfoListBannerBean> getBannerList() {
        return bannerList;
    }

    public void setBannerList(ArrayList<ShopInfoListBannerBean> bannerList) {
        this.bannerList = bannerList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}
