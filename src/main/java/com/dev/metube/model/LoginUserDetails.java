package com.dev.metube.model;

import java.util.Date;
import org.springframework.security.core.authority.AuthorityUtils;

public class LoginUserDetails extends org.springframework.security.core.userdetails.User {
	private static final long serialVersionUID = 1L;
	private int no;
	private String username;
	private String password;
	private String display_name;
	private String sex;
	private String phone;
	private String address;
	private String type;
	private String status;
	private Date register_date;

	public LoginUserDetails(User user) {
		super(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getType().name()));
		this.no = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.display_name = user.getDisplay_name();
		this.sex = user.getSex().name();
		this.phone = user.getPhone();
		this.address = user.getAddress();
		this.type = user.getType().name();
		this.status = user.getStatus().name();
		this.register_date = user.getRegister_date();
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getRegister_date() {
		return register_date;
	}

	public void setRegister_date(Date register_date) {
		this.register_date = register_date;
	}

}
