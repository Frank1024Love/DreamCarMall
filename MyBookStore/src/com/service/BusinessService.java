package com.service;

import java.util.List;

import com.commons.Page;
import com.domain.Book;
import com.domain.Category;
import com.domain.Customer;
import com.domain.Order;
import com.domain.OrderItem;

public interface BusinessService {
	/**
	 * 添加分类
	 * @param c
	 */
	void addCategory(Category c);
	
//	删除分类
	void deleteBook(String id);
	
	void updateBook(String id,String author,String price,String decription);
	/**
	 * 查询所有的分类
	 * @return
	 */
	List<Category> findAllCategories();
	/**
	 * 根据id查询分类
	 * @param categoryId
	 * @return 没有找到返回null
	 */
	Category findCategoryById(String categoryId);
	/**
	 * 添加书籍
	 * @param book
	 * 如果book关联的Category为null，要抛出参数错误异常
	 */
	void addBook(Book book);
	/**
	 * 根据id查询一本书
	 * @param bookId
	 * @return book，还有分类
	 */
	Book findBookById(String bookId);
	/**
	 * 根据用户要查看的页码，返回封装了所有与分页有关的Page对象
	 * @param num 要看的页码，如果为null或“”，默认为1
	 * @return
	 */
	Page findBookPageRecords(String num);
	Page findBookPageRecords(String num, String categoryId);
	/**
	 * 添加客户
	 * @param c
	 */
	void addCustomer(Customer c);
	/**
	 * 根据主键查询客户
	 * @param customerId
	 * @return
	 */
	Customer findCustomer(String customerId);
	/**
	 * 客户登录
	 * @param username
	 * @param password
	 * @return
	 */
	Customer customerLogin(String username,String password);
	/**
	 * 生成订单。订单中必须有订单项；必须有关联的客户信息
	 * @param o
	 * 
	 */
	void genOrder(Order o);
	/**
	 * 根据订单号查询订单
	 * @param ordernum
	 * @return
	 */
	Order findOrderByNum(String ordernum);
	/**
	 * 查询客户的订单
	 * @param c
	 */
	List<Order> findCustomerOrders(Customer c);
}
