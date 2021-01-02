<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
	<%@include file="/manage/header.jsp"%>
	
	<div style="margin-top: 100px;font-family: Lobster">
    <center>
    <form action="${pageContext.request.contextPath}/manage/ManageServlet?op=addCategory" method="post">
    <fieldset style="border-radius:25px;width: 300px;">
<legend style="width: 150px ; ">Input Type Message</legend>
</p>
    	   <input type="text" name="name" id="name"placeholder=" Type:"/>
    				</p>
    		<input type="text" name="description"placeholder=" Description:"/>
    		</p> 	
    <button style="border-radius:25px;width: 70px;height: 28px;background-color: white;" onclick="toSubmit()">OK</button> 				
</p>

    	</fieldset>
    </form>
    </center>
    </div>
    <script type="text/javascript">
    	function toSubmit(){
    		var nameValue = document.getElementById("name").value;
    		if(nameValue.trim()==""){
    			alert("Please Input Car Type");
    			return;
    		}
    		document.forms[0].submit();
    	}
    </script>
  </body>
</html>
