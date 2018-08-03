<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@include file="/manage/header.jsp"%>
<center>

<form action="${pageContext.request.contextPath}/manage/ManageServlet?op=doUpdate" method="post">
 <fieldset style="width: 500px; 	border-radius:25px;">
<legend style="width: 200px ; font-size: x-large;font-family: Lobster;color: white">Modify--Car</legend>
	<input type="hidden" name="bookId" value="${book.id}"></p>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Price:&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="price" value="${book.price}"><br/></p>
	&nbsp;&nbsp;&nbsp;Company:&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="author" value="${book.author}"><br/></p>
	Description:&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="description" value="${book.description}"><br/></p>
	<input style="border-radius:25px;width: 70px;height: 28px;background-color: white;" type="submit" value="OK">
	</fieldset>
</form>
</center>
</body>
</html>