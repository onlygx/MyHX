package com.example.viewpagerdemo.ui.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/6/19.
 */
public class OderInfoBean {


    /**
     * address : 曹阳小区11号
     * name : 大海啊大海
     * id : 5570554702071182621
     * province : 安徽省
     * phone : 13306400282
     * city : 安庆市
     * district : 枞阳县
     * title : 11
     * userId : 8984249178456606085
     */

    private AddressBean address;
    /**
     * address : {"address":"曹阳小区11号","name":"大海啊大海","id":5570554702071182621,"province":"安徽省","phone":"13306400282","city":"安庆市","district":"枞阳县","title":"11","userId":8984249178456606085}
     * id : 5872883120288057404
     * type : 1
     * status : 4
     * shopId : 3632282114678830878
     * shopName : 小宝贝卖场
     * goodses : [{"number":1,"name":"婴儿奶粉","id":5381249302621799593,"type":1,"infoId":363228213,"orderFromId":5872883120288057404,"price":98}]
     * sendType : 0
     * userId : 8984249178456606085
     * setTime : 1466357676000
     * price : 98.0
     * intro :
     * receiptAddressId : 5570554702071182621
     */

    private long id;
    private int type;
    private int status;
    private long shopId;
    private String shopName;
    private String sendType;
    private long userId;
    private long setTime;
    private double price;
    private String intro;
    private long receiptAddressId;
    /**
     * number : 1
     * name : 婴儿奶粉
     * id : 5381249302621799593
     * type : 1
     * infoId : 363228213
     * orderFromId : 5872883120288057404
     * price : 98.0
     */

    private List<GoodsesBean> goodses;

    public AddressBean getAddress() {
        return address;
    }

    public void setAddress(AddressBean address) {
        this.address = address;
    }

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public long getReceiptAddressId() {
        return receiptAddressId;
    }

    public void setReceiptAddressId(long receiptAddressId) {
        this.receiptAddressId = receiptAddressId;
    }

    public List<GoodsesBean> getGoodses() {
        return goodses;
    }

    public void setGoodses(List<GoodsesBean> goodses) {
        this.goodses = goodses;
    }

    public static class AddressBean {
        private String address;
        private String name;
        private long id;
        private String province;
        private String phone;
        private String city;
        private String district;
        private String title;
        private long userId;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

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

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }
    }

    public static class GoodsesBean {
        private int number;
        private String name;
        private long id;
        private int type;
        private int infoId;
        private long orderFromId;
        private double price;

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

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

        public long getOrderFromId() {
            return orderFromId;
        }

        public void setOrderFromId(long orderFromId) {
            this.orderFromId = orderFromId;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
}
