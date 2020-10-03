package com.dev.metube.model;

import java.util.Date;

import com.dev.metube.type.EnumSex;
import com.dev.metube.type.EnumUserStatus;
import com.dev.metube.type.EnumUserType;

public class User {
	private int id;
	private String username;
	private String password;
	private String display_name;
	private EnumSex sex;
	private String phone;
	private String address;
	private EnumUserType type;
	private EnumUserStatus status;
	private Date register_date;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDisplay_name() {
		return display_name;
	}
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	public EnumSex getSex() {
		return sex;
	}
	public void setSex(EnumSex sex) {
		this.sex = sex;
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
	public EnumUserType getType() {
		return type;
	}
	public void setType(EnumUserType type) {
		this.type = type;
	}
	public EnumUserStatus getStatus() {
		return status;
	}
	public void setStatus(EnumUserStatus status) {
		this.status = status;
	}
	public Date getRegister_date() {
		return register_date;
	}
	public void setRegister_date(Date register_date) {
		this.register_date = register_date;
	}
	
	@Override
	public String toString() {
		return ">>> Model USER : ["
				+ "id= " + id + ", username= " + username + ", password= " + password + ", display_name= " + display_name
				+ ", sex= " + sex.name() + ", phone= " + phone + ", address= " + address + ", type= " + type.name()
				+ ", status= " + status.name() + ", register_date= " + register_date
				+ "]";
	}
}