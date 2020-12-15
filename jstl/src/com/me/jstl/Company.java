package com.me.jstl;

public class Company {
	private String cname;
	private String url;
	public Company(String cname, String url) {
		this.cname = cname;
		this.url = url;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
