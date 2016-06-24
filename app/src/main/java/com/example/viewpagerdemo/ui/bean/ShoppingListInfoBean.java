package com.example.viewpagerdemo.ui.bean;

import java.util.Date;

/**
 * Created by Administrator on 2016/5/24.
 */
public class ShoppingListInfoBean {
    private Long id;

    private String name;

    private String intro;

    /**
     * 省份 中文
     */
    private String province;

    /**
     * 城市 中文
     */
    private String city;

    /**
     * 区县 中文
     */
    private String district;

    /**
     * 地址 中文
     */
    private String address;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 添加时间
     */
    private Date setTime;

    /**
     * 店铺图标
     */
    private String icon;

    /**
     * vip等级
     */
    private String vip;

    /**
     * 状态 1，正常，0，未审核
     */
    private Integer status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getSetTime() {
        return setTime;
    }

    public void setSetTime(Date setTime) {
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
