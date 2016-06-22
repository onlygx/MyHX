package com.example.viewpagerdemo.ui.bean;

/**
 * Created by Administrator on 2016/6/5.
 */
public class FindDDListShopGoodsBean {

    /**
     * id : 3667334363264891400
     * name : 婴儿奶嘴
     * type : 1
     * infoId : 363228211
     * number : 2
     * price : 47
     * orderFromId : 6511914774145353000
     */

    private long id;
    private String name;
    private int type;
    private int infoId;
    private int number;
    private int price;
    private long orderFromId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getInfoId() {
        return infoId;
    }

    public void setInfoId(int infoId) {
        this.infoId = infoId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public long getOrderFromId() {
        return orderFromId;
    }

    public void setOrderFromId(long orderFromId) {
        this.orderFromId = orderFromId;
    }
}
