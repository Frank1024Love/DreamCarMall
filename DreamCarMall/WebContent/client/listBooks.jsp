<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/client/header.jsp"%>

<center>
	<div id="default">
		<div class="lanren">
			<div class="full-length">
				<div class="container">
					<h2>
						<span>Hot~Car</span>
					</h2>
					<ul>
					<c:forEach items="${page.records }" var="b">
						<li>
							<div class="port-7 effect-3">
								<div class="image-box">
									<img src="/pic/${b.filename}" alt="Image-1">
								</div>
								<div class="text-desc">
									<h3>${b.name}</h3>
									<p>${b.author}&nbsp;&nbsp;&nbsp;&nbsp;$&nbsp;${b.price }</p>
									</br>
									<a
										href="${pageContext.request.contextPath}/client/ClientServlet?op=showBookDetail&bookId=${b.id}"
										class="btn">Detail</a>
								</div>
							</div>
						</li>
						</c:forEach>
					</ul>
					
				</div>
				
			</div>
		</div>
</div>

	<div class="dropdown">
		<span>&nbsp;&nbsp;&nbsp;CarType</span>
		<div class="dropdown-content">
			<c:forEach items="${cs}" var="c">
				<p>
					<a style="text-decoration: none; font-size: medium;"
						href="${pageContext.request.contextPath}/client/ClientServlet?op=showCategoryBooks&categoryId=${c.id}">${c.name}</a>
				</p>
			</c:forEach>
		</div>
	</div>

<%@include file="/common/page.jsp"%>
</center>
</body>
</html>
