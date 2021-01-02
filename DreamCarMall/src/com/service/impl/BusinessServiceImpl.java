package com.service.impl;

import java.util.List;

import com.commons.Page;
import com.dao.BookDao;
import com.dao.CategoryDao;
import com.dao.CustomerDao;
import com.dao.OrderDao;
import com.dao.impl.BookDaoImpl;
import com.dao.impl.CategoryDaoImpl;
import com.dao.impl.CustomerDaoImpl;
import com.dao.impl.OrderDaoImpl;
import com.domain.Book;
import com.domain.Category;
import com.domain.Customer;
import com.domain.Order;
import com.domain.OrderItem;
import com.service.BusinessService;
import com.util.IdGenertor;
//开发主线：业务主线
public class BusinessServiceImpl implements BusinessService {
	private CategoryDao categoryDao = new CategoryDaoImpl();
	private BookDao bookDao = new BookDaoImpl();
	private CustomerDao customerDao = new CustomerDaoImpl();
	private OrderDao orderDao = new OrderDaoImpl();
	public void addCategory(Category c) {
		c.setId(IdGenertor.genGUID());
		categoryDao.save(c);
	}
	public void deleteBook(String id) {
		bookDao.deleteBook(id);
	}
	
	public void updateBook(String id,String author,String price,String decription){
		bookDao.updateBook(id,author,price,decription);
	}
	
	public List<Category> findAllCategories() {
		return categoryDao.findAll();
	}

	public Category findCategoryById(String categoryId) {
		return categoryDao.findById(categoryId);
	}

	public void addBook(Book book) {
		if(book==null){
			throw new IllegalArgumentException("The book can not be null");
		}
		if(book.getCategory()==null){
			throw new IllegalArgumentException("The book's category can not be null");
		}
		book.setId(IdGenertor.genGUID());
		bookDao.save(book);
	}

	public Book findBookById(String bookId) {
		return bookDao.findBookById(bookId);
	}

	public Page findBookPageRecords(String num) {
		int pageNum = 1;
		if(num!=null&&!num.equals("")){
			pageNum = Integer.parseInt(num);
		}
		int totalRecordsNum = bookDao.getTotalRecordsNum();
		Page page = new Page(pageNum, totalRecordsNum);
		List records = bookDao.findPageRecords(page.getStartIndex(),page.getPageSize());
		page.setRecords(records);
		return page;
	}

	public Page findBookPageRecords(String num, String categoryId) {
		int pageNum = 1;
		if(num!=null&&!num.equals("")){
			pageNum = Integer.parseInt(num);
		}
		int totalRecordsNum = bookDao.getTotalRecordsNum(categoryId);
		Page page = new Page(pageNum, totalRecordsNum);
		List records = bookDao.findPageRecords(page.getStartIndex(),page.getPageSize(),categoryId);
		page.setRecords(records);
		return page;
	}

	public void addCustomer(Customer c) {
		c.setId(IdGenertor.genGUID());
		customerDao.save(c);
	}

	public Customer findCustomer(String customerId) {
		return customerDao.findOne(customerId);
	}

	public Customer customerLogin(String username, String password) {
		return customerDao.find(username,password);
	}

	public void genOrder(Order o) {
		if(o.getCustomer()==null){
			throw new IllegalArgumentException("订单所属的客户信息没有");
		}
		if(o.getItems()==null||o.getItems().size()==0){
			throw new IllegalArgumentException("订单中没有订单项，没有必要生成");
		}
		orderDao.save(o);
	}

	public Order findOrderByNum(String ordernum) {
		return orderDao.findByNum(ordernum);
	}

	public List<Order> findCustomerOrders(Customer c) {
		return orderDao.findByCustomer(c.getId());
	}


}
