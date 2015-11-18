package com.li.dao;

import java.util.Date;
import java.util.List;

import com.li.bean.Book;
import com.li.bean.BorrowList;

public interface BookDao {
	
	/**
	 * 添加书籍
	 * 
	 * @param book
	 *            需要添加的书籍类
	 */
	void addBook(Book book);

	/**
	 * 删除书籍
	 * 
	 * @param bookID
	 *            要删除书籍的编号
	 * @param adminID
	 *            执行操作管理员的id
	 * @param reason
	 * 			     删除的原因
	 * @param remark
	 * 			     备注
	 */
	void delBook(String bookID, String adminID,String reason,String remark);

	/**
	 * 修改书籍
	 * 
	 * @param book
	 *            更新的书籍信息
	 */
	void updateBook(Book book);

	/**
	 * 借还书,状态指示借书还是还书
	 * @param bookID
	 * @param state true表示被借出
	 * @param userID 借书人ID
	 * @param backDate 应该归还的日期，如果是还书则为null
	 */
	void updateBookState(String bookID,boolean state,String userID,Date backDate);
	
	/**
	 * 查询所有的书籍，按照指定的顺序分页查询
	 * @param orderBy  指定排序的列,如果为null默认按照书名升序排序
	 * @param orders   指定排序的规则,true为升序,false为降序
	 * @param start    开始的记录数
	 * @param length   查询的长度
	 * @return
	 */
	List<Book> queryAllBooks(String orderBy,boolean orders,int start,int length);
	
	/**
	 * 通过书名查询书籍，分页查询
	 * 
	 * @param name 姓名
	 * @param start 开始的记录数
	 * @param length 查询的长度
	 * @return
	 */
	List<Book> queryBookByName(String name, int start, int length);

	/**
	 * 通过类型查询书籍，分页查询
	 * @param type  书籍的类型
	 * @param start  开始的记录数
	 * @param length  查询的长度
	 * @return 
	 */
	List<Book> queryBookByType(String type,int start,int length);
	
	/**
	 * 通过管理员ID查询书籍，分页查询，查询指定管理员最后操作的书籍
	 * @param adminID  管理员ID
	 * @param start  开始的记录数
	 * @param length  查询的长度
	 * @return 查询的书籍列表
	 */
	List<Book> queryBookByAdmin(String adminID,int start,int length);
	
	/**
	 * 通过书籍ID查询书籍
	 * 
	 * @param id
	 */
	Book queryBookByID(String id);

	/**
	 * 通过ISBN查询书籍
	 * @param id
	 * @return 查询的书籍
	 */
	Book queryBookByISBN(String id);
	
	/**
	 * 通过书籍ID查询借阅详细信息
	 * @param id  书籍ID
	 * @return  借阅详细信息包括借阅人ID、借阅时间、应还时间
	 */
	BorrowList queryBookBorrowInfo(String id);
	
	/**
	 * 通过指定列的名称和列的值查询图书的数量
	 * @param colName  指定的列名称
	 * @param value  这一列所在值的名称
	 * @return
	 */
	int getBooksCount(String colName,String value);

}
