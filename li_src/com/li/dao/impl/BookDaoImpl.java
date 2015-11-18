package com.li.dao.impl;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.li.bean.Admin;
import com.li.bean.Book;
import com.li.bean.BorrowList;
import com.li.dao.BookDao;
import com.li.tool.DBCountUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class BookDaoImpl implements BookDao {
	ComboPooledDataSource cds=new ComboPooledDataSource();
	QueryRunner queryRunner =new QueryRunner(cds);
	@Override
	public void addBook(Book book) {
		if(book==null){
			throw new IllegalArgumentException("book不能为空");
		}
		try {
			queryRunner.update("insert into book values(?,?,?,?,?,?,?,?,?,?)",
					book.getBookID(),book.getISBN(),book.getBookName(),
					book.getAuthor(),book.getPubDate(),book.getAmdinID(),
					book.getImportDate(),book.getRemark(),book.isState(),
					book.getType());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delBook(String bookID, String adminID,String reason,String remark) {
		if(bookID==null){
			throw new IllegalArgumentException("bookID不能为空");
		}
		try {
			queryRunner.update("delete from book where bookID=?",bookID);
			queryRunner.update("insert into exportlist value(?,?,?,?,?,?)",UUID.randomUUID().toString()
					,bookID,adminID,new Date(System.currentTimeMillis()),reason,remark);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateBook(Book book) {
		if(book==null){
			throw new IllegalArgumentException("book不能为空");
		}
		try {
			queryRunner.update("update book set IBSN=?,bookName=?,author=?,pubDate=?,"
					+ "adminID=?,importDate=?,remark=?,state=?,type=?",book.getISBN(),book.getBookName(),
					book.getAuthor(),book.getPubDate(),book.getAmdinID(),
					book.getImportDate(),book.getRemark(),book.isState(),
					book.getType(),book.getBookID());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateBookState(String bookID, boolean state,String userID,Date backDate) {
		if(bookID==null){
			throw new IllegalArgumentException("bookID不能为空");
		}
		try {
			queryRunner.update("update book set state=? where bookID=?",state,bookID);
			Date date=new Date(System.currentTimeMillis());
			if(state){  //借书
				queryRunner.update("insert into borrowlist values(?,?,?,?,?)",
						UUID.randomUUID().toString(),bookID,userID,date,backDate);
			}else{  //还书
				BorrowList bl=queryBookBorrowInfo(bookID);
				queryRunner.update("delete from borrowlist where bookID=?",bookID);
				//TODO 根据需求看是否写触发器
				queryRunner.update("insert into borrowedlist values(?,?,?,?,?)",
					bl.getId(),bl.getBookID(),bl.getStuID(),bl.getBorrowDate(),date);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Book> queryAllBooks(String orderBy, boolean orders, int start,
			int length) {
		List<Book> list=null;
		try {
			if(orders){				
				list=queryRunner.query("select * from book order by ? limit ?,?", 
						new BeanListHandler<Book>(Book.class),orderBy,start,length);
			}else{
				list=queryRunner.query("select * from book order by ? desc limit ?,?", 
						new BeanListHandler<Book>(Book.class),orderBy,start,length);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Book> queryBookByName(String name, int start, int length) {
		List<Book> list=null;
		try {		
				list=queryRunner.query("select * from book where name like %?% limit ?,?", 
						new BeanListHandler<Book>(Book.class),name,start,length);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Book> queryBookByType(String type, int start, int length) {
		List<Book> list=null;
		try {		
				list=queryRunner.query("select * from book where type =? limit ?,?", 
						new BeanListHandler<Book>(Book.class),type,start,length);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Book> queryBookByAdmin(String adminID, int start, int length) {
		List<Book> list=null;
		try {		
				list=queryRunner.query("select * from book where adminID =? limit ?,?", 
						new BeanListHandler<Book>(Book.class),adminID,start,length);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Book queryBookByID(String id) {
		Book book=null;
		try {		
			book=queryRunner.query("select * from book where bookID =?", 
						new BeanHandler<Book>(Book.class),id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}

	@Override
	public Book queryBookByISBN(String id) {
		Book book=null;
		try {		
			book=queryRunner.query("select * from book where ISBN =?", 
						new BeanHandler<Book>(Book.class),id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}

	@Override
	public BorrowList queryBookBorrowInfo(String id) {
		BorrowList bl=null;
		try {
			bl=queryRunner.query("select * from borrowlist where id=?", 
					new BeanHandler<BorrowList>(BorrowList.class),id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bl;
	}

	@Override
	public int getBooksCount(String colName, String value) {
		DBCountUtils dbCountUtils=new DBCountUtils(cds);
		int n=dbCountUtils.getCount("book", "state", "0");
		 n+=dbCountUtils.getCount("book", "state", "1");
		 return n;
	}

}
