<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<div class="page-bar">
	<c:if test="${param.currentPageNum>1}">
		<a href="javascript:page_nav(${param.formName},1);">首页</a>  
		<a href="javascript:page_nav(${param.formName},<c:out value="${param.prePageNum}"/>);">上一页</a>
	</c:if>
	<c:if test="${param.currentPageNum<=1}">
		首页&nbsp;&nbsp;上一页
	</c:if>
	<c:if test="${param.currentPageNum<param.totalPageNum}">
		<a href="javascript:page_nav(${param.formName},<c:out value="${param.nextPageNum}" />);">下一页</a>
		<a href="javascript:page_nav(${param.formName},<c:out value="${param.totalPageNum}"/>);">最后一页</a>
	</c:if> 
	<c:if test="${param.currentPageNum>=param.totalPageNum}">
		下一页&nbsp;&nbsp;尾页
	</c:if>&nbsp;&nbsp;
	&nbsp;&nbsp;第 ${param.currentPageNum}页/共${param.totalPageNum}页&nbsp;&nbsp;共${param.totalRecords }条记录
</div>