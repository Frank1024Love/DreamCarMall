<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
</dr>
</dr>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Page${page.pageNum }/${page.totalPageNum }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    			<a style="font-size: large;font-family: Lobster;text-decoration: none" href="${pageContext.request.contextPath}${page.url}&num=${page.prePageNum}">Last</a>
    			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    			<a style="font-size: large;font-family: Lobster;text-decoration: none" href="${pageContext.request.contextPath}${page.url}&num=${page.nextPageNum}">Next</a>