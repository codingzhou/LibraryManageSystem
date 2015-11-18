package com.li.tool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * 获得数据库中特定记录的数量
 * @author li
 *
 */
public class DBCountUtils {
	private Connection conn;
	private DataSource ds;
	private DBCountUtils(){
	}
	/**
	 * 构造方法需要一个数据源
	 * @param ds
	 */
	public DBCountUtils(DataSource ds) {
		super();
		this.ds = ds;
		try {
			conn=ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获得指定表中某个字段的值的个数
	 * @param tableNames  表名
	 * @param column  列名
	 * @param value   值
	 * @return
	 */
	public int getCount(String tableNames, String column,String value){
		String sql="select count(*) from "+tableNames+" c where c."+column+"=?";
		int n=0;
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, value);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				n=rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}
	
}
