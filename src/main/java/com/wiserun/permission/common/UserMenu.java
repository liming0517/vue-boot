package com.wiserun.permission.common;

import java.util.List;

public class UserMenu implements TreeAware<UserMenu> {
	private String tabId;
	private String tabName;

	private String menuId;
	private String text;
	private String parentId;
	private String url;
	private List<UserMenu> children;

	public void setId(String id) {
		this.menuId = id;
	}

	@Override
	public String getId() {
		return menuId;
	}

	@Override
	public String getParentId() {
		return parentId;
	}

	@Override
	public String getUrl() {
		return url;
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public List<UserMenu> getChildren() {
		return children;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setChildren(List<UserMenu> children) {
		this.children = children;
	}

	public String getTabId() {
		return tabId;
	}

	public void setTabId(String tabId) {
		this.tabId = tabId;
	}

	public String getTabName() {
		return tabName;
	}

	public void setTabName(String tabName) {
		this.tabName = tabName;
	}

}
