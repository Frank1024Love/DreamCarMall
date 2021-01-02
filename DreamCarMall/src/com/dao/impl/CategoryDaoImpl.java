package com.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.dao.CategoryDao;
import com.domain.Category;
import com.util.DBCPUtil;

public class CategoryDaoImpl implements CategoryDao {
	private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());
	public void save(Category c) {
		try {
			qr.update("insert into categorys (id,name,description) values (?,?,?)", c.getId(),c.getName(),c.getDescription());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public List<Category> findAll() {
		try {
			return qr.query("select * from categorys",new BeanListHandler<Category>(Category.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Category findById(String categoryId) {
		try {
			return qr.query("select * from categorys where id=?",new BeanHandler<Category>(Category.class),categoryId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
