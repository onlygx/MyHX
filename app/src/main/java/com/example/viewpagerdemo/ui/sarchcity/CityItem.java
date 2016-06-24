package com.example.viewpagerdemo.ui.sarchcity;

public class CityItem implements ContactItemInterface
{
	private String nickName;
	private String fullName;
	private String id;
	private String type;

	public CityItem(String nickName, String fullName,String id,String type)
	{
		super();
		this.nickName = nickName;
		this.setFullName(fullName);
		this.setId(id);
		this.setType(type);
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
