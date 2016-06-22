package com.example.viewpagerdemo.ui.bean;

/**
 * Created by Administrator on 2016/5/30.
 */
public class ShopInfoLunBean {

    /**
     * id : 1
     * type : 1
     * intro : null
     * url : /images/shop/gg1.jpg
     * info : 3632282114678831000
     */

    private int id;
    private int type;
    private String intro;
    private String url;
    private long info;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getInfo() {
        return info;
    }

    public void setInfo(long info) {
        this.info = info;
    }
}
