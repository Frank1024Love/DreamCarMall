<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
	<%@include file="/client/header.jsp"%>
    <c:if test="${empty sessionScope.cart.items}">
    	<h3>您没有购买任何车辆</h3>
    </c:if>
    <c:if test="${!empty sessionScope.cart.items}">
    	<h2>您购买的车辆如下</h2>
    	<center>
    	<table border="1" width="538">
    		<tr>
    			<th>车名</th>
    			<th>数量</th>
    			<th>单价</th>
    			<th>总价</th>
    			<th>操作</th>
    		</tr>
    		<c:forEach items="${sessionScope.cart.items}" var="me" varStatus="vs">
    			<tr class="${vs.index%2==0?'odd':'even' }">
	    			<td>${me.value.book.name}</td>
	    			<td><input type="text" id="quantity" value="${me.value.quantity}" size="2" onchange="changeNum(this,${me.value.quantity},'${me.value.book.id}')"/></td>
	    			<td>${me.value.book.price}</td>
	    			<td>${me.value.money}</td>
	    			<td>
	    				<a href="javascript:delOneItem('${me.key}')">删除</a>
	    			</td>
	    		</tr>
    		</c:forEach>
    		<tr>
    			<td colspan="5" align="right">
    				<a href="javascript:delAllItems()">清空购物车</a>
    				总数量：${sessionScope.cart.totalQuantity}
    				应付金额：${sessionScope.cart.totalMoney}
    				<a href="${pageContext.request.contextPath}/client/ClientServlet?op=genOrder">去结算</a>
    			</td>
    		</tr>
    	</table>
    	</center>
    </c:if>
    <script type="text/javascript">
    	function delAllItems(){
    		var sure = window.confirm("确定要删除该项吗？");
    		if(sure){
    			window.location.href="${pageContext.request.contextPath}/client/ClientServlet?op=delAllItems";
    		}
    	}
    	function delOneItem(bookId){
    		var sure = window.confirm("确定要删除该项吗？");
    		if(sure){
    			window.location.href="${pageContext.request.contextPath}/client/ClientServlet?op=delOneItem&bookId="+bookId;
    		}
    	}
    	function changeNum(inputObj,oldNum,bookId){
    		var sure = window.confirm("确定要修改数量吗?");
    		if(sure){
    			//修改新数量
    			var num = inputObj.value;
    				//验证：用户输入的必须是自然数。 字母\1.1\-1排除
    			if(!/^[1-9][0-9]*$/.test(num)){
    				alert("请输入正确的数量");
    				return;
    			}
    			//提交给服务器修改该项的数量
    			window.location.href="${pageContext.request.contextPath}/client/ClientServlet?op=changeNum&num="+num+"&bookId="+bookId;
    		}else{
    			//显示原来的值
    			inputObj.value = oldNum;
    		}
    	}
    </script>
  </body>
</html>
