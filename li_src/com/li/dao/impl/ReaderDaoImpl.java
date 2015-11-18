package com.li.dao.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.li.bean.BorrowList;
import com.li.bean.ForbiddenList;
import com.li.bean.Reader;
import com.li.dao.ReaderDao;
import com.li.tool.DBCountUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ReaderDaoImpl implements ReaderDao{
	ComboPooledDataSource cds=new ComboPooledDataSource();
	QueryRunner queryRunner =new QueryRunner(cds);
	@Override
	public void addReader(Reader reader) {
		if(reader==null){
			throw new IllegalArgumentException("reader不能为空");
		}
		try {
			queryRunner.update("insert into reader values(?,?,?,?,?,?,?,?,?,?,?,?)",
						reader.getStuID(),reader.getPass(),reader.getName()
						,reader.getGrade(),reader.getDepatment(),reader.getMajor(),
						reader.getClassNum(),reader.getIdCardNum(),reader.getPhone(),
						reader.getBorrowNum(),reader.getArrearage(),reader.isState());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delReader(String stuID) {
		if(stuID==null){
			throw new IllegalArgumentException("stuID不能为空");
		}
		try {
			queryRunner.update("delete from reader where stuID=?",stuID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateReader(Reader reader) {
		if(reader==null){
			throw new IllegalArgumentException("reader不能为空");
		}
		try {
			queryRunner.update("update reader set pass=?,name=?,grade=?,depatment=?,major=?"
					+ ",classNum=?,idCardNum=?,phone=?,borrowNum=?,arrearage=?,state=? where stuID=?",
						reader.getPass(),reader.getName()
						,reader.getGrade(),reader.getDepatment(),reader.getMajor(),
						reader.getClassNum(),reader.getIdCardNum(),reader.getPhone(),
						reader.getBorrowNum(),reader.getArrearage(),reader.isState(),
						reader.getStuID());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateReaderState(String stuID, boolean state,String adminID) {
		if(stuID==null){
			throw new IllegalArgumentException("stuID不能为空");
		}
		try {
			queryRunner.update("update reader set state=? where stuID=?",state,stuID);
			if(state){  //封禁
				queryRunner.update("insert into forbiddenlist values(?,?,?,?)",UUID.randomUUID().toString(),
						adminID,stuID,new Date(System.currentTimeMillis()));
			}else{  //解封
				queryRunner.update("delete from forbiddenlist where stuID=?",stuID);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Reader queryReaderByID(String stuID) {
		if(stuID==null){
			throw new IllegalArgumentException("stuID不能为空");
		}
		Reader reader=null;
		try {
			reader=queryRunner.query("select * from reader where stuID=?", new BeanHandler<Reader>(Reader.class),stuID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reader;
	}

	@Override
	public List<BorrowList> queryReaderBorrows(String stuID) {
		if(stuID==null){
			throw new IllegalArgumentException("stuID不能为空");
		}
		List<BorrowList> list=null;
		try {
			list=queryRunner.query("select * from borrowlist where stuID=?", new BeanListHandler<BorrowList>(BorrowList.class),stuID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Reader> queryAllReaders(String orderBy, boolean orders,
			int start, int length) {
		if(orderBy==null){
			orderBy="stuID";
			orders=true;
		}
		List<Reader> list=null;
		try {
			if(orders){				
				list=queryRunner.query("select * from reader where order by ? limit ?,?", 
						new BeanListHandler<Reader>(Reader.class),orderBy,start,length);
			}else{
				list=queryRunner.query("select * from reader where order by ? desc limit ?,?", 
						new BeanListHandler<Reader>(Reader.class),orderBy,start,length);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Reader> queryReadersArge(int start, int length) {
		List<Reader> list=null;
		try {
				list=queryRunner.query("select * from reader where arrearage!=0 limit ?,?", 
						new BeanListHandler<Reader>(Reader.class),start,length);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ForbiddenList queryReaderForbidInfo(String stuID) {
		ForbiddenList fl=null;
		try {
			fl=queryRunner.query("select * from forbiddenlist where stuID=?", 
					new BeanHandler<ForbiddenList>(ForbiddenList.class),stuID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fl;
	}

	@Override
	public int getReadersCount() {
		DBCountUtils dbCountUtils=new DBCountUtils(cds);
		int n=dbCountUtils.getCount("reader", "state", "0");
		 n+=dbCountUtils.getCount("reader", "state", "1");
		 return n;
	}

}
