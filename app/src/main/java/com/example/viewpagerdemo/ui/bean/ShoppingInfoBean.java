package com.example.viewpagerdemo.ui.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/25.
 */
public class ShoppingInfoBean implements Serializable {


    private String ids;
    private String seCode;
    private int id;
    private int payCount;
    private int collectionCount;
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
    private String score;
    private String shopName;
    private String shopAddress;


    Collection collection;

    ArrayList<EventList> eventList;


    public ArrayList<EventList> getEventList() {
        return eventList;
    }

    public void setEventList(ArrayList<EventList> eventList) {
        this.eventList = eventList;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getPayCount() {
        return payCount;
    }

    public void setPayCount(int payCount) {
        this.payCount = payCount;
    }

    public int getCollectionCount() {
        return collectionCount;
    }

    public void setCollectionCount(int collectionCount) {
        this.collectionCount = collectionCount;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    private ArrayList<EatInfoOneBaen> bannerList;

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

    public ArrayList<EatInfoOneBaen> getBannerList() {
        return bannerList;
    }

    public void setBannerList(ArrayList<EatInfoOneBaen> bannerList) {
        this.bannerList = bannerList;
    }


}
