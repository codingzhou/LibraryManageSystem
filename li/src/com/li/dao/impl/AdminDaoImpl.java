package com.li.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.li.bean.Admin;
import com.li.bean.Book;
import com.li.bean.ExportList;
import com.li.bean.ForbiddenList;
import com.li.bean.Reader;
import com.li.dao.AdminDao;
import com.li.tool.DBCountUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class AdminDaoImpl implements AdminDao{

	ComboPooledDataSource cds=new ComboPooledDataSource();
	QueryRunner queryRunner =new QueryRunner(cds);
	@Override
	public void addAdmin(Admin admin) {
		if(admin==null){
			throw new IllegalArgumentException("admin不能为空");
		}
		try {
			queryRunner.update("insert into admin values (?,?,?,?,?)",
					admin.getId(),admin.getPass(),admin.getName(),admin.getPhone(),admin.isState());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delAdmin(String adminID) {
		if(adminID==null){
			throw new IllegalArgumentException("adminID不能为空");
		}
		try {
			queryRunner.update("delete from admin where id=?",adminID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateAdmin(Admin admin) {
		if(admin==null){
			throw new IllegalArgumentException("adminID不能为空");
		}
		try {
			queryRunner.update("update admin set pass=?,name=?,phone=?,state=? where id=?",
					admin.getPass(),admin.getName(),admin.getPhone(),admin.isState());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateAdminState(String adminID, boolean state) {
		if(adminID==null){
			throw new IllegalArgumentException("adminID不能为空");
		}
		try {
			queryRunner.update("update admin set state=? where id=?",
					state,adminID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Admin queryAdminByID(String adminID) {
		Admin admin=null;
		if(adminID==null){
			throw new IllegalArgumentException("adminID不能为空");
		}
		try {
			admin=queryRunner.query("select * from admin where id=?", new BeanHandler<Admin>(Admin.class),adminID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return admin;
	}

	@Override
	public List<Admin> queryAllAdmin() {
		List<Admin> list=null;
		try {
			list=queryRunner.query("select * from admin", new BeanListHandler<Admin>(Admin.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Admin> queryAdminByState(boolean state) {
		List<Admin> list=null;
		try {
			list=queryRunner.query("select * from admin where state=?", new BeanListHandler<Admin>(Admin.class),state);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Book> queryImportBooks(String adminID) {
		if(adminID==null){
			throw new IllegalArgumentException("adminID不能为空");
		}
		List<Book> list=null;
		try {
			list=queryRunner.query("select * from book where adminID=?", new BeanListHandler<Book>(Book.class),adminID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ExportList> queryExportBooks(String adminID) {
		if(adminID==null){
			throw new IllegalArgumentException("adminID不能为空");
		}
		List<ExportList> list=null;
		try {
			list=queryRunner.query("select * from exportlist where adminID=?", new BeanListHandler<ExportList>(ExportList.class),adminID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ForbiddenList> queryForbidReader(String adminID) {
		if(adminID==null){
			throw new IllegalArgumentException("adminID不能为空");
		}
		List<ForbiddenList> list=null;
		try {
			list=queryRunner.query("select * from exportlist where adminID=?", new BeanListHandler<ForbiddenList>(ForbiddenList.class),adminID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int getAdminCount() {
		DBCountUtils dbCountUtils=new DBCountUtils(cds);
		int n=dbCountUtils.getCount("admin", "state", "0");
		 n+=dbCountUtils.getCount("admin", "state", "1");
		 return n;
	}
}
