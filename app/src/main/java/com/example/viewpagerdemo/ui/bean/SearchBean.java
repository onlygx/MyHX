package com.example.viewpagerdemo.ui.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/5/24.
 */
public class SearchBean {

    private int type;
    private ShoppingListBean commodityBean;

    private IndexRaleaseBean.ListBean needBean;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ShoppingListBean getCommodityBean() {
        return commodityBean;
    }

    public void setCommodityBean(ShoppingListBean commodityBean) {
        this.commodityBean = commodityBean;
    }

    public IndexRaleaseBean.ListBean getNeedBean() {
        return needBean;
    }

    public void setNeedBean(IndexRaleaseBean.ListBean needBean) {
        this.needBean = needBean;
    }
}
