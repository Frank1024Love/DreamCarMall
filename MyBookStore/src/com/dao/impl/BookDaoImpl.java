package com.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.dao.BookDao;
import com.domain.Book;
import com.domain.Category;
import com.util.DBCPUtil;

public class BookDaoImpl implements BookDao {
	private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());
	
	public void deleteBook(String id) {
		try {
			qr.update("delete from books where id='"+id+"'");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void updateBook(String id,String author,String price,String decription)
	{
		try {
			qr.update("update books set author='"+author+"',price='"+price+"',description='"+decription+"' where id='"+id+"'");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void save(Book book) {
		try {
			qr.update("insert into books (id,name,author,price,path,filename,description,categoryId) values(?,?,?,?,?,?,?,?)", 
					book.getId(),book.getName(),book.getAuthor(),book.getPrice(),book.getPath(),book.getFilename(),book.getDescription(),
					book.getCategory().getId());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	

	public Book findBookById(String bookId) {
		try {
			Book book = qr.query("select * from books where id=?", new BeanHandler<Book>(Book.class), bookId);
			if(book!=null){
				Category c = qr.query("select * from categorys where id=(select categoryId from books where id=?)",new BeanHandler<Category>(Category.class),bookId);
				book.setCategory(c);
			}
			return book;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public int getTotalRecordsNum() {
		try {
			//Long
			Object obj = qr.query("select count(*) from books", new ScalarHandler(1));
			Long num = (Long)obj;
			return num.intValue();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public List findPageRecords(int startIndex, int pageSize) {
		try {
			List<Book> books = qr.query("select * from books limit ?,?", new BeanListHandler<Book>(Book.class), startIndex,pageSize);
			if(books!=null&&books.size()>0){
				for(Book book:books){
					Category c = qr.query("select * from categorys where id=(select categoryId from books where id=?)",new BeanHandler<Category>(Category.class),book.getId());
					book.setCategory(c);
				}
			}
			return books;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public int getTotalRecordsNum(String categoryId) {
		try {
			//Long
			Object obj = qr.query("select count(*) from books where categoryId=?", new ScalarHandler(1),categoryId);
			Long num = (Long)obj;
			return num.intValue();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public List findPageRecords(int startIndex, int pageSize, String categoryId) {
		try {
			List<Book> books = qr.query("select * from books where categoryId=? limit ?,?", new BeanListHandler<Book>(Book.class), categoryId,startIndex,pageSize);
			if(books!=null&&books.size()>0){
				for(Book book:books){
					Category c = qr.query("select * from categorys where id=?",new BeanHandler<Category>(Category.class),categoryId);
					book.setCategory(c);
				}
			}
			return books;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
