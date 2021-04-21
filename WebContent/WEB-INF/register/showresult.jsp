<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
	<!--  usersave.action >>> Register_Controller
	>>> message = String.format("新增記錄數:%d", affect);
	or
	message = String.format("新增記錄失敗，訊息:%s", ex.getMessage());
	>>> showresult > {message} -->
	<jsp:include page="../../banner.jsp"></jsp:include>
	<h1 style="color:red;">${message}</h1>	
</body>
</html>