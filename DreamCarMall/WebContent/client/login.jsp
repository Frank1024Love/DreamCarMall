<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
	<%@include file="/client/header.jsp"%>
	
	<div id="login">
	<center>
     <form action="${pageContext.request.contextPath}/client/ClientServlet?op=customerLogin" method="post">
    <fieldset style="width: 500px; 	border-radius:25px;">
<legend style="width: 200px ; font-size: x-large;font-family: Lobster;color: white">InputYourMessage</legend>
<br></br>
     				&nbsp;&nbsp;&nbsp;Name:&nbsp;&nbsp;&nbsp;&nbsp;<input style="border-radius:25px;" name="username"/>
    <p></p>
     				Password:<input style="border-radius:25px;type="password" name="password"/>
     <p></p>
     				<button type="submit" style="border-radius:25px;width: 70px;height: 28px;background-color: white;">Sign-in</button>
     				<p></p>
     	</fieldset>
     </form>
     </center>
    </div>
  </body>
</html>
