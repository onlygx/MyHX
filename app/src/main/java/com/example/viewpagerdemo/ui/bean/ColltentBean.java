package com.example.viewpagerdemo.ui.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/6/19.
 */
public class ColltentBean {

    /**
     * size : 1
     * total : 1
     * orderBy : null
     * pageSize : 10
     * pageNum : 1
     * startRow : 1
     * list : [{"id":2251987968923907275,"goodsId":363228213,"goods":{"name":"婴儿奶粉","id":363228213,"content":"这里是网页简介","baoYou":1,"ziTi":1,"intro":"","youPrice":0,"peiSong":1,"shopId":3632282114678830878,"sort":3,"price":98,"kuaiDi":1,"bannerList":[],"setTime":1463899276000},"setTime":1466263949000,"userId":8984249178456606085}]
     * navigatePages : 8
     * pages : 1
     * nextPage : 0
     * hasNextPage : false
     * prePage : 0
     * endRow : 1
     * isFirstPage : true
     * lastPage : 1
     * isLastPage : true
     * firstPage : 1
     * hasPreviousPage : false
     * navigatepageNums : [1]
     */

    private int size;
    private int total;
    private String orderBy;
    private int pageSize;
    private int pageNum;
    private int startRow;
    private int navigatePages;
    private int pages;
    private int nextPage;
    private boolean hasNextPage;
    private int prePage;
    private int endRow;
    private boolean isFirstPage;
    private int lastPage;
    private boolean isLastPage;
    private int firstPage;
    private boolean hasPreviousPage;
    /**
     * id : 2251987968923907275
     * goodsId : 363228213
     * goods : {"name":"婴儿奶粉","id":363228213,"content":"这里是网页简介","baoYou":1,"ziTi":1,"intro":"","youPrice":0,"peiSong":1,"shopId":3632282114678830878,"sort":3,"price":98,"kuaiDi":1,"bannerList":[],"setTime":1463899276000}
     * setTime : 1466263949000
     * userId : 8984249178456606085
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

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public boolean isIsFirstPage() {
        return isFirstPage;
    }

    public void setIsFirstPage(boolean isFirstPage) {
        this.isFirstPage = isFirstPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public boolean isIsLastPage() {
        return isLastPage;
    }

    public void setIsLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
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
        private long id;
        private int goodsId;
        private GoodsBean goods;
        private long setTime;
        private long userId;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public GoodsBean getGoods() {
            return goods;
        }

        public void setGoods(GoodsBean goods) {
            this.goods = goods;
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

        public static class GoodsBean {
            private String name;
            private int id;
            private String content;
            private int baoYou;
            private int ziTi;
            private String intro;
            private double youPrice;
            private int peiSong;
            private long shopId;
            private int sort;
            private double price;
            private int kuaiDi;
            private long setTime;
            private List<GoodBanner> bannerList;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getBaoYou() {
                return baoYou;
            }

            public void setBaoYou(int baoYou) {
                this.baoYou = baoYou;
            }

            public int getZiTi() {
                return ziTi;
            }

            public void setZiTi(int ziTi) {
                this.ziTi = ziTi;
            }

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public double getYouPrice() {
                return youPrice;
            }

            public void setYouPrice(double youPrice) {
                this.youPrice = youPrice;
            }

            public int getPeiSong() {
                return peiSong;
            }

            public void setPeiSong(int peiSong) {
                this.peiSong = peiSong;
            }

            public long getShopId() {
                return shopId;
            }

            public void setShopId(long shopId) {
                this.shopId = shopId;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public int getKuaiDi() {
                return kuaiDi;
            }

            public void setKuaiDi(int kuaiDi) {
                this.kuaiDi = kuaiDi;
            }

            public long getSetTime() {
                return setTime;
            }

            public void setSetTime(long setTime) {
                this.setTime = setTime;
            }

            public List<GoodBanner> getBannerList() {
                return bannerList;
            }

            public void setBannerList(List<GoodBanner> bannerList) {
                this.bannerList = bannerList;
            }
        }
    }
}
