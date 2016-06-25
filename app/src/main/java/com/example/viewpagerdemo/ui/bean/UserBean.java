package com.example.viewpagerdemo.ui.bean;

/**
 * Created by Administrator on 2016/5/24.
 */
public class UserBean extends BaseBean{


    /**
     * ids : null
     * seCode : null
     * id : 18660132803
     * name : 18660132803
     * head : /images/account/defaultHead.jpg
     * sex : null
     * intro : null
     * thinksId : null
     */

    private String ids;
    private String seCode;
    private long id;
    private String name;
    private String nickName;
    private String head;
    private String sex;
    private String intro;
    private String thinksId;
    String userName;
    String userPws;


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPws() {
        return userPws;
    }

    public void setUserPws(String userPws) {
        this.userPws = userPws;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getSeCode() {
        return seCode;
    }

    public void setSeCode(String seCode) {
        this.seCode = seCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getThinksId() {
        return thinksId;
    }

    public void setThinksId(String thinksId) {
        this.thinksId = thinksId;
    }
}
