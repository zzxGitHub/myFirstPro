package com.example.demo.model;

public class Content {
	private String firstTitle;
	private String title;
	private String content;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFirstTitle() {
		return firstTitle;
	}

	public void setFirstTitle(String firstTitle) {
		this.firstTitle = firstTitle;
	}

	public Content(String firstTitle, String title, String content) {
		super();
		this.firstTitle = firstTitle;
		this.title = title;
		this.content = content;
	}

	public Content() {
		super();
	}
	

}
