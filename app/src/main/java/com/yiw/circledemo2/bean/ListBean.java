package com.yiw.circledemo2.bean;

import android.text.TextUtils;
import android.util.Log;

import java.util.List;

/**
 * Created by huaping on 2016/6/20.
 */

public  class ListBean {

    public final static String TYPE_URL = "1";
    public final static String TYPE_IMG = "2";
    public final static String TYPE_VIDEO = "3";
    private String address;
    private String id;
    private String content;
    String type;//1:链接  2:图片 3:视频
    private UserBean user;
    private String province;
    private String city;
    private String district;
    private long setTime;
    private long userId;
    private String linkImg;
    private String linkTitle;
    private List<String> photos;
    private String videoUrl;
    private static List<ZanListBean> zanList;
    private List<RecordListBean> recordList;
    private List<BannerListBean> bannerList;


    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public String getLinkImg() {
        return linkImg;
    }

    public void setLinkImg(String linkImg) {
        this.linkImg = linkImg;
    }

    public String getLinkTitle() {
        return linkTitle;
    }

    public void setLinkTitle(String linkTitle) {
        this.linkTitle = linkTitle;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
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

    public List<ZanListBean> getZanList() {
        return zanList;
    }

    public void setZanList(List<ZanListBean> zanList) {
        this.zanList = zanList;
    }

    public List<RecordListBean> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<RecordListBean> recordList) {
        this.recordList = recordList;
    }

    public List<BannerListBean> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<BannerListBean> bannerList) {
        this.bannerList = bannerList;
    }

    /**
     * 遍历 赞列表iD查看是否自己赞过
     *
     * @param curUserId
     * @return
     */
    public String getCurUserFavortId(String curUserId) {
        String favortid = "";
        if (!TextUtils.isEmpty(curUserId) && hasFavort()) {
            for (int i=0;i<zanList.size();i++) {
                ZanListBean item=zanList.get(i);
                String zid=item.getUser().getId();
                Log.v("LD",curUserId+"=站zid="+zid +"==名="+item.getUser().getNickName());
                if (curUserId.equals(zid)) {
                    favortid = zid + "";
                    return favortid;
                }
            }
        }
        return favortid;
    }

    /**
     * 赞列表是否y有数据
     *
     * @return
     */
    public boolean hasFavort() {
        if (zanList != null && zanList.size() > 0) {
            return true;
        }
        return false;
    }




}