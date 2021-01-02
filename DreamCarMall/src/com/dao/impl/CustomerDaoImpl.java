package com.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.dao.CustomerDao;
import com.domain.Customer;
import com.util.DBCPUtil;

public class CustomerDaoImpl implements CustomerDao {
	private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());
	public void save(Customer c) {
		try {
			qr.update("insert into customers (id,username,password,nickname,phonenum,address,email) values (?,?,?,?,?,?,?)", 
					c.getId(),c.getUsername(),c.getPassword(),c.getNickname(),c.getPhonenum(),c.getAddress(),c.getEmail());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Customer findOne(String customerId) {
		try {
			return qr.query("select * from customers where id=?", new BeanHandler<Customer>(Customer.class), customerId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Customer find(String username, String password) {
		try {
			return qr.query("select * from customers where username=? and password=?", new BeanHandler<Customer>(Customer.class), username,password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
