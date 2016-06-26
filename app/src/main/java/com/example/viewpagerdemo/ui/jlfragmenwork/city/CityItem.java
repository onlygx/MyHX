package com.example.viewpagerdemo.ui.jlfragmenwork.city;

public class CityItem implements ContactItemInterface
{
	private String nickName;
	private String fullName;
	private String id;
	private long Fid;
	private String type;
	private long friendId;
	private String phone;
	private String icon;
	int friendStatus;
	String thinksId;

	/**
	 *
	 * @param nickName  中午
	 * @param fullName  转拼音
	 * @param id 		 id
	 * @param type      无用
	 * @param friendId  好友ID
	 * @param phone     手机号
     * @param friendStatus  当前状态
     */
	public CityItem(String nickName, String fullName,String id,String type,
					long friendId,String phone,int friendStatus,long Fid,
					String icon,String thinksId
	)
	{
		super();
		this.setNickName(nickName);
		this.setFullName(fullName);
		this.setId(id);
		this.setType(type);
		this.setFriendId(friendId);
		this.setPhone(phone);
		this.setFriendStatus(friendStatus);
		this.setFid(Fid);
		this.setIcon(icon);
		this.setThinksId(thinksId);
	}




	@Override
	public long getFrendID() {
		return friendId;
	}

	@Override
	public String getThinkesId() {
		return thinksId;
	}

	public String getThinksId() {
		return thinksId;
	}

	public void setThinksId(String thinksId) {
		this.thinksId = thinksId;
	}

	@Override
	public String getIcons() {
		return icon;
	}

	@Override
	public int getStute() {
		return friendStatus;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public long getFid() {
		return Fid;
	}

	public void setFid(long fid) {
		Fid = fid;
	}

	public long getFriendId() {
		return friendId;
	}

	public void setFriendId(long friendId) {
		this.friendId = friendId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getFriendStatus() {
		return friendStatus;
	}

	public void setFriendStatus(int friendStatus) {
		this.friendStatus = friendStatus;
	}

	@Override
	public String getItemForIndex()
	{
		return fullName;
	}

	@Override
	public String getDisplayInfo()
	{
		return nickName;
	}

	@Override
	public String getID() {
		return id;
	}


	@Override
	public String getTypes() {
		return type;
	}

	public String getNickName()
	{
		return nickName;
	}

	public void setNickName(String nickName)
	{
		this.nickName = nickName;
	}

	public String getFullName()
	{
		return fullName;
	}

	public void setFullName(String fullName)
	{
		this.fullName = fullName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
