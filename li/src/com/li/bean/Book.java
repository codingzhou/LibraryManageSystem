package com.li.bean;

import java.util.Date;

public class Book {
	private String bookID;
	private String ISBN;
	private String bookName;
	private String author;
	private Date pubDate;
	private String amdinID;
	private Date importDate;
	private String remark;
	private boolean state;
	private String type;
	public String getBookID() {
		return bookID;
	}
	public void setBookID(String bookID) {
		this.bookID = bookID;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}
	public Date getPubDate() {
		return pubDate;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setAmdinID(String amdinID) {
		this.amdinID = amdinID;
	}
	public String getAmdinID() {
		return amdinID;
	}
	public void setImportDate(Date importDate) {
		this.importDate = importDate;
	}
	public Date getImportDate() {
		return importDate;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRemark() {
		return remark;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public boolean isState() {
		return state;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
	
}
