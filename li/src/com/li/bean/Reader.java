package com.li.bean;


public class Reader {
	private String stuID;
	private String pass;
	private String name;
	private String grade;
	private String depatment;
	private String major;
	private String classNum;
	private String idCardNum;
	private String phone;
	private int borrowNum;
	private float arrearage;
	private boolean state;
	public String getStuID() {
		return stuID;
	}
	public void setStuID(String stuID) {
		this.stuID = stuID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getDepatment() {
		return depatment;
	}
	public void setDepatment(String depatment) {
		this.depatment = depatment;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getIdCardNum() {
		return idCardNum;
	}
	public void setIdCardNum(String idCardNum) {
		this.idCardNum = idCardNum;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	public String getClassNum() {
		return classNum;
	}
	public void setArrearage(float arrearage) {
		this.arrearage = arrearage;
	}
	public float getArrearage() {
		return arrearage;
	}
	public void setBorrowNum(int borrowNum) {
		this.borrowNum = borrowNum;
	}
	public int getBorrowNum() {
		return borrowNum;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getPass() {
		return pass;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public boolean isState() {
		return state;
	}

}
