package com.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.commons.Page;
import com.domain.Book;
import com.domain.Category;
import com.service.BusinessService;
import com.service.impl.BusinessServiceImpl;
import com.util.IdGenertor;

public class ManageServlet extends HttpServlet {
	private BusinessService s = new BusinessServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = request.getParameter("op");
		if("addCategory".equals(op)){
			addCategory(request,response);
		}else if("updateBook".equals(op)){
			updateBook(request,response);
		}
		else if("doUpdate".equals(op)){
			doUpdate(request,response);
		}
		else if("deleteBook".equals(op)){
			deleteBook(request,response);
		}else if("showAllCategory".equals(op)){
			showAllCategory(request,response);
		}else if("addBookUI".equals(op)){
			addBookUI(request,response);
		}else if("addBook".equals(op)){
			addBook(request,response);
		}else if("showPageBooks".equals(op)){
			showPageBooks(request,response);
		}
	}

	private void doUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String bookId = request.getParameter("bookId");
		String price = request.getParameter("price");
		String author = request.getParameter("author");
		String description = request.getParameter("description");
		s.updateBook(bookId, author, price, description);
		 request.getRequestDispatcher("/manage/index.jsp").forward(request, response);
	}

	private void updateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String bookId = request.getParameter("bookId");
		Book book = s.findBookById(bookId);
		request.setAttribute("book", book);
		request.getRequestDispatcher("/manage/updateBook.jsp").forward(request, response);
	}

	//查询书籍分页
	private void showPageBooks(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException  {
		String num = request.getParameter("num");//用户要看的页码
		Page page = s.findBookPageRecords(num);
		page.setUrl("/manage/ManageServlet?op=showPageBooks");
		request.setAttribute("page", page);
		request.getRequestDispatcher("/manage/listBooks.jsp").forward(request, response);
	}
	
	//添加图书
	private void addBook(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException  {
		//判断表单是不是multipart/form-data类型的
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(!isMultipart){
			throw new RuntimeException("The form is not multipart/form-data");
		}
		//解析请求内容
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		List<FileItem> items = new ArrayList<FileItem>();
		try {
			items = sfu.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		Book book = new Book();//空对象
		
		for(FileItem item:items){
			//普通字段：把数据封装到Book对象中
			if(item.isFormField()){
				processFormFiled(item,book);
			}else{
			//上传字段：上传
				processUploadFiled(item,book);
			}
		}
		
		
		//把书籍信息保存到数据库中
		s.addBook(book);
		response.sendRedirect(request.getContextPath()+"/common/message.jsp");
	}
	//处理文件上传
	private void processUploadFiled(FileItem item, Book book) {
		//存放路径：不要放在WEB-INF中
//		String storeDirectory = getServletContext().getRealPath("/images");
		String storeDirectory = "d:/ChromDownload";
		File rootDirectory = new File(storeDirectory);
		if(!rootDirectory.exists()){
			rootDirectory.mkdirs();
		}
		//搞文件名
		String filename = item.getName();//  a.jpg
		if(filename!=null){
			filename = IdGenertor.genGUID()+"."+ FilenameUtils.getExtension(filename);//LKDSJFLKSFKS.jpg
			book.setFilename(filename);
		}
		//计算子目录
//		String path = genChildDirectory(storeDirectory, filename);
//		book.setPath(path);
		
		//文件上传
		try {
//			item.write(new File(rootDirectory, path+"/"+filename));
			item.write(new File(rootDirectory, "/"+filename));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
//	private String genChildDirectory(String realPath, String fileName) {
//		int hashCode = fileName.hashCode();
//		int dir1 = hashCode&0xf;
//		int dir2 = (hashCode&0xf0)>>4;
//		
//		String str = dir1+File.separator+dir2;
//		
//		File file = new File(realPath,str);
//		if(!file.exists()){
//			file.mkdirs();
//		}
//		
//		return str;
//		
//	}
	//把FileItem中的数据封装到Book中
	private void processFormFiled(FileItem item, Book book) {
		try {
			String fieldName = item.getFieldName();//name
			String fieldValue = item.getString("UTF-8");//jpm
			BeanUtils.setProperty(book, fieldName, fieldValue);
			//单独处理书籍所属的分类
			if("categoryId".equals(fieldName)){
				Category c = s.findCategoryById(fieldValue);
				book.setCategory(c);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	//转向添加书籍页面，查询所有的分类
	private void addBookUI(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException  {
		List<Category> cs = s.findAllCategories();
		request.setAttribute("cs", cs);
		request.getRequestDispatcher("/manage/addBook.jsp").forward(request, response);
	}
	//查询所有分类
	private void showAllCategory(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		List<Category> cs = s.findAllCategories();
		request.setAttribute("cs", cs);
		request.getRequestDispatcher("/manage/listCategory.jsp").forward(request, response);
	}
	//添加分类
	private void addCategory(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		Category c = new Category();
		try {
			BeanUtils.populate(c, request.getParameterMap());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s.addCategory(c);
		//处理完后，要页面转向。Redirect After Post
		response.sendRedirect(request.getContextPath()+"/common/message.jsp");
	}
	private void deleteBook(HttpServletRequest request,
		HttpServletResponse response)throws ServletException, IOException {
		String bookId=request.getParameter("bookId");
		s.deleteBook(bookId);
		//处理完后，要页面转向。Redirect After Post
		response.sendRedirect(request.getContextPath()+"/common/message.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
