package com.example.viewpagerdemo.ui.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/6/19.
 */
public class IndexRaleaseBean {

    /**
     * size : 2
     * total : 2
     * hasPreviousPage : false
     * navigatepageNums : [1]
     * startRow : 1
     * pageNum : 1
     * pageSize : 10
     * orderBy : null
     * list : [{"address":"111","id":4308326996471532356,"content":"11111111","status":1,"setTime":1466320765000,"title":"山东的","bannerList":[{"id":9204805431003361743,"type":3,"info":4308326996471532356,"url":"/images/demand/9065134423808427121.jpg"}],"price":22,"province":"山东省","city":"济南市","district":"历下区","sendUserId":8984249178456606085,"sendUser":{"name":"","id":8984249178456606085,"head":"/images/account/defaultHead.jpg","phone":"13306400282","qq":"","thinksId":"13306400282","nickName":"13306400282","wechat":""}},{"address":"233","id":6797810865349608943,"content":"111","status":1,"setTime":1466320817000,"title":"山东2","bannerList":[{"id":5556461291625466071,"type":3,"info":6797810865349608943,"url":"/images/demand/5830667787874945792.jpg"}],"price":1,"province":"山东省","city":"济南市","district":"历下区","sendUserId":8984249178456606085,"sendUser":{"name":"","id":8984249178456606085,"head":"/images/account/defaultHead.jpg","phone":"13306400282","qq":"","thinksId":"13306400282","nickName":"13306400282","wechat":""}}]
     * firstPage : 1
     * lastPage : 1
     * nextPage : 0
     * isFirstPage : true
     * pages : 1
     * navigatePages : 8
     * endRow : 2
     * prePage : 0
     * isLastPage : true
     * hasNextPage : false
     */

    private int size;
    private int total;
    private boolean hasPreviousPage;
    private int startRow;
    private int pageNum;
    private int pageSize;
    private String orderBy;
    private int firstPage;
    private int lastPage;
    private int nextPage;
    private boolean isFirstPage;
    private int pages;
    private int navigatePages;
    private int endRow;
    private int prePage;
    private boolean isLastPage;
    private boolean hasNextPage;
    private List<Integer> navigatepageNums;
    /**
     * address : 111
     * id : 4308326996471532356
     * content : 11111111
     * status : 1
     * setTime : 1466320765000
     * title : 山东的
     * bannerList : [{"id":9204805431003361743,"type":3,"info":4308326996471532356,"url":"/images/demand/9065134423808427121.jpg"}]
     * price : 22.0
     * province : 山东省
     * city : 济南市
     * district : 历下区
     * sendUserId : 8984249178456606085
     * sendUser : {"name":"","id":8984249178456606085,"head":"/images/account/defaultHead.jpg","phone":"13306400282","qq":"","thinksId":"13306400282","nickName":"13306400282","wechat":""}
     */

    private List<ListBean> list;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public boolean isIsFirstPage() {
        return isFirstPage;
    }

    public void setIsFirstPage(boolean isFirstPage) {
        this.isFirstPage = isFirstPage;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public boolean isIsLastPage() {
        return isLastPage;
    }

    public void setIsLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public List<Integer> getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(List<Integer> navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private String address;
        private long id;
        private String content;
        private int status;
        private long setTime;
        private String title;
        private double price;
        private String province;
        private String city;
        private String district;
        private long sendUserId;
        /**
         * name : 
         * id : 8984249178456606085
         * head : /images/account/defaultHead.jpg
         * phone : 13306400282
         * qq : 
         * thinksId : 13306400282
         * nickName : 13306400282
         * wechat : 
         */

        private SendUserBean sendUser;
        /**
         * id : 9204805431003361743
         * type : 3
         * info : 4308326996471532356
         * url : /images/demand/9065134423808427121.jpg
         */

        private List<ShoppingListBanerBean> bannerList;

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

        public long getSetTime() {
            return setTime;
        }

        public void setSetTime(long setTime) {
            this.setTime = setTime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
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

        public List<ShoppingListBanerBean> getBannerList() {
            return bannerList;
        }

        public void setBannerList(List<ShoppingListBanerBean> bannerList) {
            this.bannerList = bannerList;
        }

        public static class SendUserBean {
            private String name;
            private long id;
            private String head;
            private String phone;
            private String qq;
            private String thinksId;
            private String nickName;
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

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getWechat() {
                return wechat;
            }

            public void setWechat(String wechat) {
                this.wechat = wechat;
            }
        }

    }
}
