<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
	<%@include file="/manage/header.jsp"%>
	<div style="margin-top: 100px">
    <center >
    <form enctype="multipart/form-data" action="${pageContext.request.contextPath}/manage/ManageServlet?op=addBook" method="post">
    <fieldset style="border-radius:25px;width: 500px;">
<legend style="width: 300px ;font-family: Lobster ">Input Car Message</legend>
<br></br>
    		<input type="text" name="name" id="name" placeholder=" Name:"/>
    		</p>
    	    <input type="text" name="author"placeholder=" Company:"/>
    	        </p>
    		<input type="text" name="price"placeholder=" Price:"/>
    		</p>
    		<input   type="text" name="description"placeholder=" Description:"/> 
    		</p>
    		<input  style="margin-left: 100px" type="file" name="file"/>
    		</p>
    		<select name="categoryId">
    					<c:forEach items="${cs}" var="c">
    						<option value="${c.id}">${c.name}</option>
    					</c:forEach>
    				</select>
    				<button type="submit" style="border-radius:25px;width: 70px;height: 28px;background-color: white;">OK</button>
    				<br></br>
    				
    				</fieldset>
    </form>
    </center>
    </div>
  </body>
</html>
