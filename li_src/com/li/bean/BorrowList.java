
package com.li.bean;

import java.util.Date;

public class BorrowList {
	private String id;
	private String bookID;
	private String stuID;
	private Date borrowDate;
	private Date backDate;
	public String getBookID() {
		return bookID;
	}
	public void setBookID(String bookID) {
		this.bookID = bookID;
	}
	public String getStuID() {
		return stuID;
	}
	public void setStuID(String stuID) {
		this.stuID = stuID;
	}
	public Date getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}
	public Date getBackDate() {
		return backDate;
	}
	public void setBackDate(Date backDate) {
		this.backDate = backDate;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
}

