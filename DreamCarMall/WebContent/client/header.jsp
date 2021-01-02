<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="https://fonts.googleapis.com/css?family=Lobster"
	rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>MyDreamCar</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="styl 'esheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/util.js"></script>
<link rel="stylesheet" type="text/css" href="/MyBookStore/css/style.css">
<link rel="stylesheet" type="text/css" href="/MyBookStore/css/lanren.css">
</head>


<body>
	<div id="head">
		My-Dream<img src="/MyBookStore/img/smallcar.png" style="height: 40px;" />Car
	</div>
	<div id="menu1">
		<a style="font-size: x-large;"
			href="${pageContext.request.contextPath}/client/index.jsp">Here
			Begin&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
		<c:if test="${sessionScope.customer==null}">
			<a style="font-size: large;"
				href="${pageContext.request.contextPath}/client/login.jsp">Sign-in&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
			<a style="font-size: large;"
				href="${pageContext.request.contextPath}/client/regist.jsp">Sign-up&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
		</c:if>
		<c:if test="${sessionScope.customer!=null}">

			<label style="text-align: center; color: pink; font-size: x-large;">Welcome!
				&nbsp;&nbsp;&nbsp;${sessionScope.customer.username}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
			<a style="font-size: large;"
				href="${pageContext.request.contextPath}/client/ClientServlet?op=customerLogout">Sign-out&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
		</c:if>
		<a style="font-size: large;"
			href="${pageContext.request.contextPath}/client/showCart.jsp">Cart&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
<a style="font-size:large;" href="${pageContext.request.contextPath}/manage/index.jsp">Go-Manage&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
	</div>