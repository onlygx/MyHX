package com.example.viewpagerdemo.ui.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/30.
 */
public class ShopInfoListBannerBean  implements Serializable {

    String id;
    String type;
    String intro;
    String url;
    String info;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
