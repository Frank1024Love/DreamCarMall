<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
	<%@include file="/client/header.jsp"%>
	<center>
     <form action="${pageContext.request.contextPath}/client/ClientServlet?op=registCustomer" method="post">
  <fieldset style="width: 500px; 	border-radius:25px;">
<legend style="width: 200px ; font-size: x-large;font-family: Lobster;color: white">InputYourMessage</legend>
<br></br>
     		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Name:<input name="username"/>	
     		<br></br>
     		Password:<input type="password" name="password"/>
     		<br></br>
     		NickName:<input name="nickname"/>
     		<br></br>
     		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Phone:<input name="phonenum"/>
     		<br></br>
     		&nbsp;&nbsp;Address:<input name="address"/>
     		<br></br>
     		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Email:<input name="email"/>
     		<br></br>
     	    <input style="border-radius:25px;width: 70px;height: 28px;background-color: #ff0000;" type="submit" value="OK"/>
     	    <br></br>
     	    </fieldset>
     </form>
    </center>
  </body>
</html>
