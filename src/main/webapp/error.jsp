<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head><title>Simple jsp page</title></head>
<body>
<h1>运行异常!</h1>
<s:debug>查看信息</s:debug>
<s:property value="exception.message"/><!-- 错误信息 -->
<br>
<%-- <s:property value="exceptionStack"/> --%>
  </body>
</html>