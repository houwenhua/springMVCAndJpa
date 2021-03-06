package com.biz.std.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Student {

	private Integer id;
	private String number;
	private String name;
	private String picture;
	private String sex;
	private Date birthday;
	public Student() {
		super();
	}

	public Student(String number, String name, String picture, String sex, Date birthday) {
		super();
		this.number = number;
		this.name = name;
		this.picture = picture;
		this.sex = sex;
		this.birthday = birthday;
	}

	public Student(Integer id, String number, String name, String picture, String sex, Date birthday) {
		super();
		this.id = id;
		this.number = number;
		this.name = name;
		this.picture = picture;
		this.sex = sex;
		this.birthday = birthday;
	}

	@GeneratedValue
	@Id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", number=" + number + ", name=" + name + ", picture=" + picture + ", sex=" + sex
				+ ", birthday=" + birthday + "]";
	}
	
}
