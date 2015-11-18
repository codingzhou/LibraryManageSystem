package com.li.dao;

import java.util.List;

import com.li.bean.Book;
import com.li.bean.BorrowList;
import com.li.bean.ForbiddenList;
import com.li.bean.Reader;

public interface ReaderDao {
	
	/**
	 * 增加用户
	 * @param reader
	 */
	void addReader(Reader reader);
	
	/**
	 * 删除用户
	 * @param reader
	 */
	void delReader(String stuID);
	
	/**
	 * 更新用户信息
	 * @param reader
	 */
	void updateReader(Reader reader);
	
	/**
	 * 封禁或者解封读者
	 * @param stuID 读者ID  
	 * @param state 状态，true表示封禁
	 */
	void updateReaderState(String stuID,boolean state,String adminID);
	
	/**
	 * 通过stuID查询读者信息
	 * @param stuID
	 * @return
	 */
	Reader queryReaderByID(String stuID);
	
	/**
	 * 通过stuID查询读者在借的书籍信息
	 * @param stuID
	 * @return
	 */
	List<BorrowList> queryReaderBorrows(String stuID);
	
	/**
	 * 查询所有的用户，分页查询 
	 * @param orderBy 指定排序的列,如果为null默认按照学号升序排序
	 * @param orders 指定排序的规则,true为升序,false为降序
	 * @param start 开始的记录数
	 * @param length 查询的长度
	 * @return
	 */
	List<Reader> queryAllReaders(String orderBy,boolean orders,int start,int length);
	
	/**
	 * 查询欠费的用户，分页查询
	 * @param start 开始的记录数
	 * @param length 查询的长度
	 * @return
	 */
	List<Reader> queryReadersArge(int start,int length);
	
	/**
	 * 查询用户被封禁的信息
	 * @param stuID
	 * @return  如果为空表示为未封禁
	 */
	ForbiddenList queryReaderForbidInfo(String stuID);
	
	/**
	 * 获取读者的总数
	 * @return
	 */
	int getReadersCount();
	
}
