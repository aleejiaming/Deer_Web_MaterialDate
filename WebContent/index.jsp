<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<base href="<%=basePath %>">
	<head>
	<title>My JSP 'index.jsp' starting page</title>
	<jsp:include page="banner.jsp"></jsp:include>
	</head>
<body>
  你好啊，下次我來接到登入畫面好了<br>
</body>
</html>