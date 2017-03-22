package com.custommultiexpand.bean;

/**
 * @author czc
 * @date 2016年9月20日 下午8:24:11
 * @Description leftfragment侧边栏的每一项
 */
public class SideBarItem {

	public static final int _First = 1;//一级
	public static final int _Second = 2;//二级
	public static final int _Third = 3;//三级
	
	
	private String itemName;
	private SideBarItem parentItem;
	private int level = _First;
	private float order;
	private int resId;
	
	
	private boolean isExpand = false;
	private boolean hasChild = false;
	private boolean isCheck = false;

	public SideBarItem() {
		super();
	}

	public SideBarItem(String itemName, float order,int resId) {
		super();
		this.itemName = itemName;
		this.order = order;
		this.resId = resId;
	}
	
	

	public boolean isCheck() {
		return isCheck;
	}

	public void setCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}

	public boolean isExpand() {
		return isExpand;
	}

	public void setExpand(boolean isExpand) {
		this.isExpand = isExpand;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public SideBarItem getParentItem() {
		return parentItem;
	}

	public void setParentItem(SideBarItem parentItem) {
		this.parentItem = parentItem;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public float getOrder() {
		return order;
	}

	public void setOrder(float order) {
		this.order = order;
	}

	public int getResId() {
		return resId;
	}

	public void setResId(int resId) {
		this.resId = resId;
	}
	
	

	public boolean isHasChild() {
		return hasChild;
	}

	public void setHasChild(boolean hasChild) {
		this.hasChild = hasChild;
	}

	@Override
	public String toString() {
		return "SideBarItem [isExpand=" + isExpand + ", itemName=" + itemName + ", parentItem=" + parentItem
				+ ", level=" + level + ", order=" + order + "]";
	}
	
	

}
