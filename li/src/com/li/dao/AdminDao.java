package com.li.dao;

import java.util.List;

import com.li.bean.Admin;
import com.li.bean.Book;
import com.li.bean.ExportList;
import com.li.bean.ForbiddenList;


public interface AdminDao {
	
	/**
	 * 增加管理员
	 * @param admin
	 */
	void addAdmin(Admin admin);
	
	/**
	 * 通过id删除管理员
	 * @param adminID
	 */
	void delAdmin(String adminID);
	
	/**
	 * 更新管理员
	 * @param adminID
	 */
	void updateAdmin(Admin admin);
	
	/**
	 * 通过id解封或者封禁管理员
	 * @param adminID
	 * @param state  封禁状态，true表示封禁
	 */
	void updateAdminState(String adminID,boolean state);
	
	/**
	 * 通过id查询管理员
	 * @param adminID
	 * @return
	 */
	Admin queryAdminByID(String adminID);
	
	/**
	 * 查询所有的管理员
	 * @return
	 */
	List<Admin> queryAllAdmin();
	
	/**
	 * 通过封禁状态查询管理员
	 * @param state
	 * @return
	 */
	List<Admin> queryAdminByState(boolean state);
	
	/**
	 * 查询指定管理员入库的记录
	 * @param adminID
	 * @return
	 */
	List<Book> queryImportBooks(String adminID);
	
	/**
	 * 查询指定管理员出库（销毁）的记录
	 * @param adminID 指定管理员的id
	 * @return
	 */
	List<ExportList> queryExportBooks(String adminID);
	
	/**
	 * 查询指定管理员封禁的用户信息表
	 * @param adminID
	 * @return ForbiddenList对象
	 */
	List<ForbiddenList> queryForbidReader(String adminID);
	
	/**
	 * 查询所有管理员的个数
	 * @return
	 */
	int getAdminCount();
}
