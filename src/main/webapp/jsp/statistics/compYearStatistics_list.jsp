<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags"  prefix="s"%>
<%@ include file="../common/taglib.jsp"%>
<link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function confirm(){
		if(isNaN(document.queryForm.startYear.value)){
			alert("输入的开始年份不合法！");
			return false;
		}
		if(isNaN(document.queryForm.endYear.value)){
			alert("输入的结束年份不合法！");
			return false;
		}
		return true;
	}
	
	function submitSearch(){
		if(confirm()){
			document.queryForm.submit();
		}
	}
</script>
<div class="action  divaction">
	<div class="t">年度统计列表</div>
	<div class="pages">
	     <s:form action="compYearStatistics_getList.action" name="queryForm">
	       		<label for="time">开始年份</label>
	       		<s:textfield name="startYear" id="startYear" cssClass="nwinput" value="" theme="simple"></s:textfield>
	       		<label for="end-time">结束年份</label>
	       		<s:textfield name="endYear" id="endYear" cssClass="nwinput" value="" theme="simple"></s:textfield>
		        <%-- <s:submit cssClass="submit_01" value="查询"/> --%>
		        <input type="button" value="提交" class="submit_01"
								onclick="submitSearch()"/>
	     </s:form>
	    
		<table width="90%" border="0" cellspacing="0" cellpadding="0" class="list items">
	      <tr class="even">
	        <td>编号</td>
	        <td>总计</td>
	        <td>年份</td>
	        <td>操作</td>
	      </tr>
	      <s:iterator value="compYearList" id="claimVouyearStatistics" begin="0" status="s">
	      <tr>
	        <td><s:property value="#claimVouyearStatistics.id"/></td>
	        <td>￥<s:property value="#claimVouyearStatistics.totalCount"/></td>
	        <td><s:property value="#claimVouyearStatistics.year"/>年</td>
	        <td>
	        <a href="compYearStatistics_getDetail.action?currYear=<s:property value="#claimVouyearStatistics.year"/>">
	        <!-- <a href="claimVoucherStatistics_getDeptVoucherDetailByMonth.action?year=2013&selectMonth=7&departmentId=2"> -->
	        <img src="${images}/search.gif" width="16" height="15" />
	        </a>
	        </td>
	      </tr>
	      </s:iterator>
	      <tr>
	      	<td bgcolor="yellow">总计</td>
	      	<td bgcolor="yellow">￥<s:property value="totalCount"/></td>
	      	<td></td>
	      	<td></td>
	      </tr>	
	    </table>        
       </div>
      </div>