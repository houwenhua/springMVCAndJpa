package com.biz.std.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class StudentImage {

	private int studentimageid;
	private int studentid;
	//文件原名
	private String filename;
	//服务器文件名
	private String studentname;
	//oss路径
	private String osspath;
	
	@GeneratedValue
	@Id
	public int getStudentimageid() {
		return studentimageid;
	}
	public void setStudentimageid(int studentimageid) {
		this.studentimageid = studentimageid;
	}
	public int getStudentid() {
		return studentid;
	}
	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	public String getOsspath() {
		return osspath;
	}
	public void setOsspath(String osspath) {
		this.osspath = osspath;
	}
}
