package com.example.viewpagerdemo.ui.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/6/19.
 */
public class IndexOrderBean {

    /**
     * address : 2222
     * id : 3425971996178075475
     * content : 222
     * status : 1
     * title : 85发布2
     * setTime : 1466330946000
     * price : 222.0
     * sendUserId : 8988110878234332042
     * bannerList : [{"id":4948569997648903813,"type":3,"info":3425971996178075475,"url":"/images/demand/5308755112306102100.jpg"}]
     * sendUser : {"name":"","id":8988110878234332042,"head":"/images/account/defaultHead.jpg","qq":"","nickName":"13306400285","phone":"13306400285","wechat":"","thinksId":"13306400285"}
     * province : 山东省
     * district : 历下区
     * city : 济南市
     */

    private String address;
    private long id;
    private String content;
    private int status;
    private String title;
    private long setTime;
    private double price;
    private long sendUserId;
    /**
     * name :
     * id : 8988110878234332042
     * head : /images/account/defaultHead.jpg
     * qq :
     * nickName : 13306400285
     * phone : 13306400285
     * wechat :
     * thinksId : 13306400285
     */

    private SendUserBean sendUser;
    private String province;
    private String district;
    private String city;
    /**
     * id : 4948569997648903813
     * type : 3
     * info : 3425971996178075475
     * url : /images/demand/5308755112306102100.jpg
     */

    private List<BannerListBean> bannerList;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getSetTime() {
        return setTime;
    }

    public void setSetTime(long setTime) {
        this.setTime = setTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(long sendUserId) {
        this.sendUserId = sendUserId;
    }

    public SendUserBean getSendUser() {
        return sendUser;
    }

    public void setSendUser(SendUserBean sendUser) {
        this.sendUser = sendUser;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<BannerListBean> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<BannerListBean> bannerList) {
        this.bannerList = bannerList;
    }

    public static class SendUserBean {
        private String name;
        private long id;
        private String head;
        private String qq;
        private String nickName;
        private String phone;
        private String wechat;
        private String thinksId;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getWechat() {
            return wechat;
        }

        public void setWechat(String wechat) {
            this.wechat = wechat;
        }

        public String getThinksId() {
            return thinksId;
        }

        public void setThinksId(String thinksId) {
            this.thinksId = thinksId;
        }
    }

    public static class BannerListBean {
        private long id;
        private int type;
        private long info;
        private String url;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public long getInfo() {
            return info;
        }

        public void setInfo(long info) {
            this.info = info;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
