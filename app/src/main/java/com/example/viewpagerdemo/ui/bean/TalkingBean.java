package com.example.viewpagerdemo.ui.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/6/26.
 */
public class TalkingBean {


    /**
     * id : 8668560260610440000
     * type : 2
     * content : 78787
     * status : 0
     * setTime : 1466870809000
     * infoId : 6610239493807583000
     * receive : 1636596581847549400
     * talk : {"address":"","id":6277664815828782000,"content":"我也发个","setTime":1466853923000,"userId":7432712794770982000,"user":{"name":"","id":7432712794770982000,"head":"/images/account/defaultHead.jpg","nickName":"13306400285","phone":"13306400285","qq":"","wechat":"","thinksId":"13306400285"},"bannerList":[],"district":"","province":"","city":""}
     * record : {"id":6610239493807583000,"type":1,"content":"123","setTime":1466866677000,"userId":1636596581847549400,"infoId":6277664815828782000}
     * recordId : 9046210411150527000
     * send : 7432712794770982000
     * sendUser : {"name":"","id":7432712794770982000,"head":"/images/account/defaultHead.jpg","nickName":"13306400285","phone":"13306400285","qq":"","wechat":"","thinksId":"13306400285"}
     */

    private long id;
    private int type;
    private String content;
    private int status;
    private long setTime;
    private long infoId;
    private long receive;
    /**
     * address :
     * id : 6277664815828782000
     * content : 我也发个
     * setTime : 1466853923000
     * userId : 7432712794770982000
     * user : {"name":"","id":7432712794770982000,"head":"/images/account/defaultHead.jpg","nickName":"13306400285","phone":"13306400285","qq":"","wechat":"","thinksId":"13306400285"}
     * bannerList : []
     * district :
     * province :
     * city :
     */

    private TalkBean talk;
    /**
     * id : 6610239493807583000
     * type : 1
     * content : 123
     * setTime : 1466866677000
     * userId : 1636596581847549400
     * infoId : 6277664815828782000
     */

    private RecordBean record;
    private long recordId;
    private long send;
    /**
     * name :
     * id : 7432712794770982000
     * head : /images/account/defaultHead.jpg
     * nickName : 13306400285
     * phone : 13306400285
     * qq :
     * wechat :
     * thinksId : 13306400285
     */

    private SendUserBean sendUser;

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

    public long getSetTime() {
        return setTime;
    }

    public void setSetTime(long setTime) {
        this.setTime = setTime;
    }

    public long getInfoId() {
        return infoId;
    }

    public void setInfoId(long infoId) {
        this.infoId = infoId;
    }

    public long getReceive() {
        return receive;
    }

    public void setReceive(long receive) {
        this.receive = receive;
    }

    public TalkBean getTalk() {
        return talk;
    }

    public void setTalk(TalkBean talk) {
        this.talk = talk;
    }

    public RecordBean getRecord() {
        return record;
    }

    public void setRecord(RecordBean record) {
        this.record = record;
    }

    public long getRecordId() {
        return recordId;
    }

    public void setRecordId(long recordId) {
        this.recordId = recordId;
    }

    public long getSend() {
        return send;
    }

    public void setSend(long send) {
        this.send = send;
    }

    public SendUserBean getSendUser() {
        return sendUser;
    }

    public void setSendUser(SendUserBean sendUser) {
        this.sendUser = sendUser;
    }

    public static class TalkBean {
        private String address;
        private long id;
        private String content;
        private long setTime;
        private long userId;
        /**
         * name :
         * id : 7432712794770982000
         * head : /images/account/defaultHead.jpg
         * nickName : 13306400285
         * phone : 13306400285
         * qq :
         * wechat :
         * thinksId : 13306400285
         */

        private UserBean user;
        private String district;
        private String province;
        private String city;
        private List<?> bannerList;

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

        public long getSetTime() {
            return setTime;
        }

        public void setSetTime(long setTime) {
            this.setTime = setTime;
        }

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
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

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public List<?> getBannerList() {
            return bannerList;
        }

        public void setBannerList(List<?> bannerList) {
            this.bannerList = bannerList;
        }

        public static class UserBean {
            private String name;
            private long id;
            private String head;
            private String nickName;
            private String phone;
            private String qq;
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

            public String getQq() {
                return qq;
            }

            public void setQq(String qq) {
                this.qq = qq;
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
    }

    public static class RecordBean {
        private long id;
        private int type;
        private String content;
        private long setTime;
        private long userId;
        private long infoId;

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getSetTime() {
            return setTime;
        }

        public void setSetTime(long setTime) {
            this.setTime = setTime;
        }

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public long getInfoId() {
            return infoId;
        }

        public void setInfoId(long infoId) {
            this.infoId = infoId;
        }
    }

    public static class SendUserBean {
        private String name;
        private long id;
        private String head;
        private String nickName;
        private String phone;
        private String qq;
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

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
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
}
