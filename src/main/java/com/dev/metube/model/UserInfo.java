package com.dev.metube.model;

import java.util.Date;

public class UserInfo {
	private long seq;
	private String id;
	private String name;
	private String password;
	private int sex;
	private String email;
	private String phone;
	private String address;
	private int status;
	private Date register_date;
	
	private UserRoles roles;
	
	UserInfo(UserInfo user) {
		this.seq = user.getSeq();
		this.id = user.getId();
		this.name = user.getName();
		this.password = user.getPassword();
		this.sex = user.getSex();
		this.email = user.getPhone();
		this.phone = user.getPhone();
		this.address = user.getAddress();
		this.status = user.getStatus();
		this.register_date = user.getRegister_date();
	}
	
	public Long getSeq() {
		return seq;
	}
	
	public void setSeq(long seq) {
		this.seq = seq;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getSex() {
		return sex;
	}
	
	public void setSex(int sex) {
		this.sex = sex;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getAddress() {
		
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public Date getRegister_date() {
		return register_date;
	}
	
	public void setRegister_date(Date register_date) {
		this.register_date = register_date;
	}
	
	public UserRoles getRoles() {
		return roles;
	}

	public void setRoles(UserRoles roles) {
		this.roles = roles;
	}

}
