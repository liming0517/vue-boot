package com.wiserun.permission.common;

import java.util.List;

public interface TreeAware<T> {
	String getId();
	String getParentId();
	String getUrl();
	String getText();
	List<T> getChildren();
}
