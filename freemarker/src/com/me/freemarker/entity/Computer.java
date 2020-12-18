package com.me.freemarker.entity;

import java.util.Date;
import java.util.Map;

public class Computer {
	private String sn; // 序列号
	private String model; // 型号
	private int state;	// 状态 1-在用 2-闲置 3-报废
	private String user; // 使用人
	private Date dop;	// 采购日期
	private Float price; // 购买价格
	private Map info;	// 电脑配置信息
	
	public Computer(String sn, String model, int state, String user, Date dop, Float price, Map info) {
		super();
		this.sn = sn;
		this.model = model;
		this.state = state;
		this.user = user;
		this.dop = dop;
		this.price = price;
		this.info = info;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Date getDop() {
		return dop;
	}
	public void setDop(Date dop) {
		this.dop = dop;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Map getInfo() {
		return info;
	}
	public void setInfo(Map info) {
		this.info = info;
	}
}
