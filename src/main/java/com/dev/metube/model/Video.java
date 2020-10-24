package com.dev.metube.model;

import java.util.Date;
import java.util.Map;

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
	private Date regDt;
	private Date modDt;

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
	
	public Date getRegDt() {
		return regDt;
	}
	
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	
	public Date getModDt() {
		return modDt;
	}
	
	public void setModDt(Date modDt) {
		this.modDt = modDt;
	}

	@Override
	public String toString() {
		return "Video [id=" + id + ", name=" + name + ", notes=" + notes + ", path=" + path + ", thumbnail=" + thumbnail
				+ ", category=" + category + ", public_yn=" + public_yn + ", regUser=" + regUser + ", regDt=" + regDt
				+ ", modDt=" + modDt + ", getId()=" + getId() + ", getName()=" + getName() + ", getNotes()="
				+ getNotes() + ", getPath()=" + getPath() + ", getThumbnail()=" + getThumbnail() + ", getCategory()="
				+ getCategory() + ", getPublic_yn()=" + getPublic_yn() + ", getRegUser()=" + getRegUser()
				+ ", getRegDt()=" + getRegDt() + ", getModDt()=" + getModDt() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
