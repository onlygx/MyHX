package com.example.viewpagerdemo.ui.bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/5.
 */
public class FindDDListShopBean {

    /**
     * id : 6511914774145353000
     * userId : 123
     * shopId : 3632282114678831000
     * setTime : 1464575871000
     * type : 1
     * payType : 星客思支付
     * payTime : 1464576429000
     * payInfo : 123456
     * sendType : 顺丰快递
     * sendTime : 1464576604000
     * sendInfo : 123456
     * receiptAddressId : 123
     * status : 3
     * shopName : 生活卖场
     */

    private long id;
    private long userId;
    private long shopId;
    private long setTime;
    private int type;
    private String payType;
    private long payTime;
    private String payInfo;
    private String sendType;
    private long sendTime;
    private String sendInfo;
    private long receiptAddressId;
    private int status;
    private String shopName;
    private String intro;
    private String shopIcon;
    double price;


    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getShopIcon() {
        return shopIcon;
    }

    public void setShopIcon(String shopIcon) {
        this.shopIcon = shopIcon;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    ArrayList<FindDDListShopGoodsBean> goodses;


    public ArrayList<FindDDListShopGoodsBean> getGoodses() {
        return goodses;
    }

    public void setGoodses(ArrayList<FindDDListShopGoodsBean> goodses) {
        this.goodses = goodses;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public long getPayTime() {
        return payTime;
    }

    public void setPayTime(long payTime) {
        this.payTime = payTime;
    }

    public String getPayInfo() {
        return payInfo;
    }

    public void setPayInfo(String payInfo) {
        this.payInfo = payInfo;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendInfo() {
        return sendInfo;
    }

    public void setSendInfo(String sendInfo) {
        this.sendInfo = sendInfo;
    }

    public long getReceiptAddressId() {
        return receiptAddressId;
    }

    public void setReceiptAddressId(long receiptAddressId) {
        this.receiptAddressId = receiptAddressId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
