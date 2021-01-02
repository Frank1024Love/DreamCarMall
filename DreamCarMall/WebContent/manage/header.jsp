<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet" type="text/css">
<html>
  <head>
    <title>后台管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
 	<script type="text/javascript" src="${pageContext.request.contextPath}/js/util.js"></script>
    <link rel="stylesheet" type="text/css" href="/MyBookStore/css/style.css" >
    <link rel="stylesheet" type="text/css" href="/MyBookStore/css/lanren.css" >
  </head> 
  <body>
 	<div id="head">
		My-Dream<img src="/MyBookStore/img/smallcar.png" style="height: 40px;" />Car
	</div>
  <div  id="menu1">
			<a style="font-size:large;" href="${pageContext.request.contextPath}/manage/addCategory.jsp">Add-Category&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a> 
			<a style="font-size:large;"  href="${pageContext.request.contextPath}/manage/ManageServlet?op=showAllCategory">Find-Category&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
			<a style="font-size:large;" href="${pageContext.request.contextPath}/manage/ManageServlet?op=addBookUI">Add-Car&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
			<a style="font-size:large;" href="${pageContext.request.contextPath}/manage/ManageServlet?op=showPageBooks">Find-Car&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
			<a style="font-size:large;" href="${pageContext.request.contextPath}/client/index.jsp">Go-Client&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
	</div>
	
	
  
  
  