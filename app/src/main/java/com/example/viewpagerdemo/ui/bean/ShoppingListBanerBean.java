package com.example.viewpagerdemo.ui.bean;

/**
 * Created by Administrator on 2016/5/24.
 */
public class ShoppingListBanerBean {

    private Long id;
    private Integer type;
    private String intro;
    private String url;
    private String name;
    private Long info;//商品id

/*
    String id;
    String name;
    String url;
    String type;
    String intro;
    String info;*/



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
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

    public Long getInfo() {
        return info;
    }

    public void setInfo(Long info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
