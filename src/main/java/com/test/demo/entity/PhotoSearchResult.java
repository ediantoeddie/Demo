package com.test.demo.entity;

import java.io.Serializable;

public class PhotoSearchResult implements Serializable  {
	private static final long serialVersionUID = 3662549023696181233L;
	private String title;
	private String description;
	private String smallUrl;
	private String largeUrl;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSmallUrl() {
		return smallUrl;
	}

	public void setSmallUrl(String smallUrl) {
		this.smallUrl = smallUrl;
	}

	public String getLargeUrl() {
		return largeUrl;
	}

	public void setLargeUrl(String largeUrl) {
		this.largeUrl = largeUrl;
	}
}
