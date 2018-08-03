package com.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.beans.Cart;
import com.beans.CartItem;
import com.commons.Page;
import com.constant.Constants;
import com.domain.Book;
import com.domain.Category;
import com.domain.Customer;
import com.domain.Order;
import com.domain.OrderItem;
import com.service.BusinessService;
import com.service.impl.BusinessServiceImpl;
import com.util.IdGenertor;

public class ClientServlet extends HttpServlet {
	private BusinessService s = new BusinessServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = request.getParameter("op");
		if("showIndex".equals(op)){
			showIndex(request,response);
		}else if("showCategoryBooks".equals(op)){
			showCategoryBooks(request,response);
		}else if("showBookDetail".equals(op)){
			showBookDetail(request,response);
		}else if("buy".equals(op)){
			buy(request,response);
		}else if("changeNum".equals(op)){
			changeNum(request,response);
		}else if("delOneItem".equals(op)){
			delOneItem(request,response);
		}else if("delAllItems".equals(op)){
			delAllItems(request,response);
		}else if("registCustomer".equals(op)){
			registCustomer(request,response);
		}else if("customerLogin".equals(op)){
			customerLogin(request,response);
		}else if("customerLogout".equals(op)){
			customerLogout(request,response);
		}else if("genOrder".equals(op)){
			genOrder(request,response);
		}
	}
	//生成订单
	private void genOrder(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException  {
		//验证用户是否登录
		HttpSession session = request.getSession();
		Customer c = (Customer)session.getAttribute(Constants.CUSTOMER_LOGIN_FLAG);
			//没有登录：去登录
		if(c==null){
			response.sendRedirect(request.getContextPath()+"/client/login.jsp");
			return;
		}
		//登录：
		
			//取出购物车的信息-------->Order.订单号没搞
			//取出购物项的信息--------->OrderItem.id没搞
		Cart cart = (Cart)session.getAttribute(Constants.HTTPSESSION_CART);
		if(cart==null){
			response.getWriter().write("会话超时!");
			return;
		}
		Order order = new Order();
		order.setOrdernum(IdGenertor.genOrdernum());
		order.setQuantity(cart.getTotalQuantity());
		order.setMoney(cart.getTotalMoney());
		order.setCustomer(c);
		//搞定单项
		List<OrderItem> oItems = new ArrayList<OrderItem>();
		for(Map.Entry<String,CartItem> me:cart.getItems().entrySet()){
			CartItem cItem = me.getValue();//购物车中的购物项
			OrderItem oItem = new OrderItem();
			oItem.setId(IdGenertor.genGUID());
			oItem.setBook(cItem.getBook());//忘记会造成DAO异常
			oItem.setPrice(cItem.getMoney());
			oItem.setQuantity(cItem.getQuantity());
			oItems.add(oItem);
		}
		//建立订单项和订单的关系
		order.setItems(oItems);
		//保存订单：跳转到在线支付页面
		s.genOrder(order);
		request.setAttribute("order", order);
		request.getRequestDispatcher("/client/pay.jsp").forward(request, response);
	}
	//客户注销
	private void customerLogout(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		request.getSession().removeAttribute(Constants.CUSTOMER_LOGIN_FLAG);
		response.sendRedirect(request.getContextPath()+"/client/index.jsp");
	}
	//客户登录
	private void customerLogin(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Customer  c= s.customerLogin(username, password);
		request.getSession().setAttribute(Constants.CUSTOMER_LOGIN_FLAG, c);
		response.sendRedirect(request.getContextPath()+"/client/index.jsp");
	}
	//用户注册
	private void registCustomer(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		Customer c = new Customer();
		try {
			BeanUtils.populate(c, request.getParameterMap());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s.addCustomer(c);
		response.getWriter().write("注册成功！2秒后转向主页");
		response.setHeader("Refresh", "2;URL="+request.getContextPath()+"/client/index.jsp");
	}
	private void delAllItems(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		request.getSession().removeAttribute(Constants.HTTPSESSION_CART);
		response.sendRedirect(request.getContextPath()+"/client/showCart.jsp");
	}
	//删除一个购物项
	private void delOneItem(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		//取出购物车
		Cart cart = (Cart)request.getSession().getAttribute(Constants.HTTPSESSION_CART);
		cart.getItems().remove(bookId);
		response.sendRedirect(request.getContextPath()+"/client/showCart.jsp");
	}
	//修改购物项的数量
	private void changeNum(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		String num = request.getParameter("num");
		String bookId = request.getParameter("bookId");
		//取出购物车
		Cart cart = (Cart)request.getSession().getAttribute(Constants.HTTPSESSION_CART);
		CartItem item = cart.getItems().get(bookId);
		item.setQuantity(Integer.parseInt(num));
		response.sendRedirect(request.getContextPath()+"/client/showCart.jsp");
	}
	//把书籍放入购物车
	private void buy(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		Book book = s.findBookById(bookId);
		
		HttpSession session = request.getSession();
		Cart cart = (Cart)session.getAttribute(Constants.HTTPSESSION_CART);
		if(cart==null){
			cart = new Cart();
			session.setAttribute(Constants.HTTPSESSION_CART, cart);
		}
		//必定有购物车
		cart.addBook(book);
		//返回主页
		response.getWriter().write("购买成功！2秒后转向主页");
		response.setHeader("Refresh", "2;URL="+request.getContextPath()+"/client/index.jsp");
		
	}
	//查看书籍的详细内容
	private void showBookDetail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		String bookId = request.getParameter("bookId");
		Book book = s.findBookById(bookId);
		request.setAttribute("book", book);
		request.getRequestDispatcher("/client/bookDetail.jsp").forward(request, response);
	}
	//按照分类查询分页信息
	private void showCategoryBooks(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		//查询所有分类
		List<Category> cs = s.findAllCategories();
		request.setAttribute("cs", cs);
		//查询所有书籍：分页
		String num = request.getParameter("num");
		String categoryId = request.getParameter("categoryId");
		Page page = s.findBookPageRecords(num,categoryId);
		page.setUrl("/client/ClientServlet?op=showCategoryBooks&categoryId="+categoryId);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/client/listBooks.jsp").forward(request, response);
	}
	
	//展现主页
	private void showIndex(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		//查询所有分类
		List<Category> cs = s.findAllCategories();
		request.setAttribute("cs", cs);
		//查询所有书籍：分页
		String num = request.getParameter("num");
		Page page = s.findBookPageRecords(num);
		page.setUrl("/client/ClientServlet?op=showIndex");
		request.setAttribute("page", page);
		request.getRequestDispatcher("/client/listBooks.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
