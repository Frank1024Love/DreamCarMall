<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/client/header.jsp"%>
<center>
	<div style="margin-top: 100px"><img  style="border-radius: 10px;" width="410px" height="230px" src="/pic/${book.filename}"
		alt="${book.filename}" /> </div><br/>
	<div id="detail">${book}</div>
	<br /> <a style="font-size:x-large; text-decoration: none;"
		href="${pageContext.request.contextPath}/client/ClientServlet?op=buy&bookId=${book.id}">Buy&nbsp;&nbsp;&nbsp;&nbsp;</a>
	<a style="font-size: x-large; text-decoration: none;"
		href="javascript:window.history.back()">Back</a>
</center>
</body>
</html>
