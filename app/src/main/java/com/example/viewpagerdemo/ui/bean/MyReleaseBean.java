package com.example.viewpagerdemo.ui.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/6/19.
 */
public class MyReleaseBean {


    /**
     * size : 3
     * total : 3
     * list : [{"address":"222","id":5396049256288112570,"content":"111","status":1,"price":111,"bannerList":[{"id":8504962231862052609,"type":3,"info":5396049256288112570,"url":"/images/demand/6004184100064048979.jpg"}],"title":"对方","setTime":1466311653000,"province":"安徽省","sendUser":{"name":"","id":8984249178456606085,"head":"/images/account/defaultHead.jpg","qq":"","nickName":"13306400282","wechat":"","phone":"13306400282","thinksId":"13306400282"},"district":"枞阳县","city":"安庆市","sendUserId":8984249178456606085},{"address":"11","id":5484280903962478929,"content":"11","status":1,"price":11,"bannerList":[{"id":6128884978578209020,"type":3,"info":5484280903962478929,"url":"/images/demand/6032016335249521198.jpg"}],"title":"12","setTime":1466309509000,"province":"安徽省","sendUser":{"name":"","id":8984249178456606085,"head":"/images/account/defaultHead.jpg","qq":"","nickName":"13306400282","wechat":"","phone":"13306400282","thinksId":"13306400282"},"district":"枞阳县","city":"安庆市","sendUserId":8984249178456606085},{"address":"111","id":9206811046026823635,"content":"22","status":1,"price":33,"bannerList":[{"id":3875521310189075127,"type":3,"info":9206811046026823635,"url":"/images/demand/9067197773912258653.jpeg"}],"title":"11","setTime":1466309277000,"province":"安徽省","sendUser":{"name":"","id":8984249178456606085,"head":"/images/account/defaultHead.jpg","qq":"","nickName":"13306400282","wechat":"","phone":"13306400282","thinksId":"13306400282"},"district":"怀宁县","city":"安庆市","sendUserId":8984249178456606085}]
     * startRow : 1
     * orderBy : null
     * pageSize : 10
     * pageNum : 1
     * hasPreviousPage : false
     * navigatepageNums : [1]
     * navigatePages : 8
     * firstPage : 1
     * prePage : 0
     * lastPage : 1
     * isFirstPage : true
     * isLastPage : true
     * hasNextPage : false
     * endRow : 3
     * nextPage : 0
     * pages : 1
     */

    private int size;
    private int total;
    private int startRow;
    private String orderBy;
    private int pageSize;
    private int pageNum;
    private boolean hasPreviousPage;
    private int navigatePages;
    private int firstPage;
    private int prePage;
    private int lastPage;
    private boolean isFirstPage;
    private boolean isLastPage;
    private boolean hasNextPage;
    private int endRow;
    private int nextPage;
    private int pages;
    /**
     * address : 222
     * id : 5396049256288112570
     * content : 111
     * status : 1
     * price : 111.0
     * bannerList : [{"id":8504962231862052609,"type":3,"info":5396049256288112570,"url":"/images/demand/6004184100064048979.jpg"}]
     * title : 对方
     * setTime : 1466311653000
     * province : 安徽省
     * sendUser : {"name":"","id":8984249178456606085,"head":"/images/account/defaultHead.jpg","qq":"","nickName":"13306400282","wechat":"","phone":"13306400282","thinksId":"13306400282"}
     * district : 枞阳县
     * city : 安庆市
     * sendUserId : 8984249178456606085
     */

    private List<ListBean> list;
    private List<Integer> navigatepageNums;

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

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public boolean isIsFirstPage() {
        return isFirstPage;
    }

    public void setIsFirstPage(boolean isFirstPage) {
        this.isFirstPage = isFirstPage;
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

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public List<Integer> getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(List<Integer> navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }

    public static class ListBean {
        private String address;
        private long id;
        private String content;
        private int status;
        private double price;
        private String title;
        private long setTime;
        private String province;
        /**
         * name : 
         * id : 8984249178456606085
         * head : /images/account/defaultHead.jpg
         * qq : 
         * nickName : 13306400282
         * wechat : 
         * phone : 13306400282
         * thinksId : 13306400282
         */

        private SendUserBean sendUser;
        private String district;
        private String city;
        private long sendUserId;
        /**
         * id : 8504962231862052609
         * type : 3
         * info : 5396049256288112570
         * url : /images/demand/6004184100064048979.jpg
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

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
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

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public SendUserBean getSendUser() {
            return sendUser;
        }

        public void setSendUser(SendUserBean sendUser) {
            this.sendUser = sendUser;
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

        public long getSendUserId() {
            return sendUserId;
        }

        public void setSendUserId(long sendUserId) {
            this.sendUserId = sendUserId;
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
            private String wechat;
            private String phone;
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

            public String getWechat() {
                return wechat;
            }

            public void setWechat(String wechat) {
                this.wechat = wechat;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
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
}
