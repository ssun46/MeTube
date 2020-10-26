package com.dev.metube.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.dev.metube.type.EnumYn;

public class Video {
	private Integer id;
	private String name;
	private String notes;
	private String path;
	private String thumbnail;
	private Integer category;
	private EnumYn public_yn;
	private Integer regUser;
	private Date reg_date;
	private Date mod_date;
	
	private Integer user_id;
	private String display_name;
	private String category_name;
	private EnumYn reg_user_thumbnail_yn; 

	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getNotes() {
		return notes;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public EnumYn getPublic_yn() {
		return public_yn;
	}

	public void setPublic_yn(int public_yn) {
		this.public_yn = EnumYn.fromInteger(public_yn);
	}
	
	public Integer getRegUser() {
		return regUser;
	}
	
	public void setRegUser(Integer regUser) {
		this.regUser = regUser;
	}
	
	public String getReg_date() {
		return format.format(reg_date);
	}
	
	public void setReg_data(Date reg_date) {
		this.reg_date = reg_date;
	}
	
	public String getMod_date() {
		return format.format(mod_date);
	}
	
	public void setMod_date(Date mod_date) {
		this.mod_date = mod_date;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getDisplay_name() {
		return display_name;
	}

	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}

	public String getCategory_name() {
		return category_name;
	}

	public EnumYn getReg_user_thumbnail_yn() {
		return reg_user_thumbnail_yn;
	}

	public void setReg_user_thumbnail_yn(Integer reg_user_thumbnail_yn) {
		this.reg_user_thumbnail_yn = EnumYn.fromInteger(reg_user_thumbnail_yn);
	}

	@Override
	public String toString() {
		return "Video [id=" + id + ", name=" + name + ", notes=" + notes + ", path=" + path + ", thumbnail=" + thumbnail
				+ ", category=" + category + ", public_yn=" + public_yn + ", regUser=" + regUser + ", reg_date=" + reg_date
				+ ", mod_date=" + mod_date + ", getId()=" + getId() + ", getName()=" + getName() + ", getNotes()="
				+ getNotes() + ", getPath()=" + getPath() + ", getThumbnail()=" + getThumbnail() + ", getCategory()="
				+ getCategory() + ", getPublic_yn()=" + getPublic_yn() + ", getRegUser()=" + getRegUser()
				+ ", getReg_date()=" + getReg_date() + ", getMod_date()=" + getMod_date() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
