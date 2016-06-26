package com.example.viewpagerdemo.ui.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/6/19.
 */
public class MyOderListBean {

        private String address;
        private long id;
        private String content;
        private int status;//订单状态，0、已取消

        private String title;
        private long setTime;
        private double price;
        /**
         * name :
         * id : 8984249178456606085
         * head : /images/account/defaultHead.jpg
         * phone : 13306400282
         * nickName : 13306400282
         * qq :
         * thinksId : 13306400282
         * wechat :
         */

        private ReceiveUserBean receiveUser;
        private long receiveUserId;
        private long receiveTime;
        private String city;
        /**
         * name :
         * id : 8988110878234332042
         * head : /images/account/defaultHead.jpg
         * phone : 13306400285
         * nickName : 13306400285
         * qq :
         * thinksId : 13306400285
         * wechat :
         */

        private SendUserBean sendUser;
        private long sendUserId;
        private String district;
        private String province;
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

        public ReceiveUserBean getReceiveUser() {
            return receiveUser;
        }

        public void setReceiveUser(ReceiveUserBean receiveUser) {
            this.receiveUser = receiveUser;
        }

        public long getReceiveUserId() {
            return receiveUserId;
        }

        public void setReceiveUserId(long receiveUserId) {
            this.receiveUserId = receiveUserId;
        }

        public long getReceiveTime() {
            return receiveTime;
        }

        public void setReceiveTime(long receiveTime) {
            this.receiveTime = receiveTime;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public SendUserBean getSendUser() {
            return sendUser;
        }

        public void setSendUser(SendUserBean sendUser) {
            this.sendUser = sendUser;
        }

        public long getSendUserId() {
            return sendUserId;
        }

        public void setSendUserId(long sendUserId) {
            this.sendUserId = sendUserId;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public List<BannerListBean> getBannerList() {
            return bannerList;
        }

        public void setBannerList(List<BannerListBean> bannerList) {
            this.bannerList = bannerList;
        }

        public static class ReceiveUserBean {
            private String name;
            private long id;
            private String head;
            private String phone;
            private String nickName;
            private String qq;
            private String thinksId;
            private String wechat;

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

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getQq() {
                return qq;
            }

            public void setQq(String qq) {
                this.qq = qq;
            }

            public String getThinksId() {
                return thinksId;
            }

            public void setThinksId(String thinksId) {
                this.thinksId = thinksId;
            }

            public String getWechat() {
                return wechat;
            }

            public void setWechat(String wechat) {
                this.wechat = wechat;
            }
        }

        public static class SendUserBean {
            private String name;
            private long id;
            private String head;
            private String phone;
            private String nickName;
            private String qq;
            private String thinksId;
            private String wechat;

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

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getQq() {
                return qq;
            }

            public void setQq(String qq) {
                this.qq = qq;
            }

            public String getThinksId() {
                return thinksId;
            }

            public void setThinksId(String thinksId) {
                this.thinksId = thinksId;
            }

            public String getWechat() {
                return wechat;
            }

            public void setWechat(String wechat) {
                this.wechat = wechat;
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
