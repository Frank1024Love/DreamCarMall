package com.junit;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.domain.Book;
import com.domain.Category;
import com.util.DBCPUtil;

public class BookDaoTest {
	
	private QueryRunner qr = null;
	
	@Before
	public void init() {
		qr = new QueryRunner(DBCPUtil.getDataSource());
	}
	
	@Test
	public void saveTest() {
		try {
			qr.update("insert into books (id,name,author,price,path,filename,description,categoryId) values(?,?,?,?,?,?,?,?)", 
					"1","java","aa",23,"","","bb","JZK9ATG3G5R6AGE7LQ3AEMMITLTRFUL7");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	@Test
	public void findBookByIdTest() {
		String bookId = "4a5c1e6c-926f-4a96-8777-e4d905419837";
		try {
			Book book = qr.query("select * from books where id=?", new BeanHandler<Book>(Book.class), bookId);
			if(book!=null){
				Category c = qr.query("select * from categorys where id=(select categoryId from books where id=?)",new BeanHandler<Category>(Category.class),bookId);
				book.setCategory(c);
			}
			System.out.println(book);
			Assert.assertNotNull(book);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
