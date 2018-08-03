<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
	<%@include file="/manage/header.jsp"%>
	<div style="margin-top: 100px">
    <center>
    <table border="1" width="600" >
    	<tr>
    		<th>序号</th>
    		<th>图片</th>
    		<th>车名</th>
    		<th>生产商</th>
    		<th>价格</th>
    		<th>描述</th>
    		<th>车辆类型</th>
    		<th>操作</th>
    	</tr>
    	<c:forEach items="${page.records}" var="b" varStatus="vs">
    		<tr class="${vs.index%2==0?'odd':'even'}">
	    		<td>${vs.count}</td>
	    		<td>
	    			<%-- <img src="${pageContext.request.contextPath}/images/${b.path}/${b.filename}" alt="${b.filename}"/> --%>
	    			<img src="/pic/${b.filename}" alt="${b.filename}"/>
	    		</td>
	    		<td>${b.name }</td>
	    		<td>${b.author }</td>
	    		<td>${b.price }</td>
	    		<td>${b.description }</td>
	    		<td>${b.category.name }</td>
	    		<td>
	    			<a href="${pageContext.request.contextPath}/manage/ManageServlet?op=updateBook&bookId=${b.id}">修改</a>
	    			<a href="${pageContext.request.contextPath}/manage/ManageServlet?op=deleteBook&bookId=${b.id}">删除</a>
	    		</td>
	    	</tr>
    	</c:forEach>
    	<tr>
    		<td align="center" colspan="8">
    			<%@include file="/common/page.jsp"%>
    		</td>
    	</tr>
    </table>
    </center>
    </div>
  </body>
</html>
