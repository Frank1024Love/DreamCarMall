<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
	<%@include file="/manage/header.jsp"%>
	<div style="margin-top: 100px">
    <center>
    <table border="1" width="438">
    	<tr align="center">
    		<th>ID</th>
    		<th>Name</th>
    		<th>Description</th>
    	</tr>
    	<c:forEach items="${cs}" var="c" varStatus="vs">
    		<tr align="center" class="${vs.index%2==0?'odd':'even'}">
	    		<td>${vs.count}</td>
	    		<td>${c.name}</td>
	    		<td>${c.description}</td>
	   
	    	</tr>
    	</c:forEach>
    </table>
    </center>
    </div>
  </body>
</html>
