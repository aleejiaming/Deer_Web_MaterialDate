<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登入訊息</title>
</head>
<body>
<jsp:include page="../../banner.jsp"></jsp:include>
<div style="font-size:48px;color:black;">訊息</div>
<h1 style="color:red;">${message}</h1>

<% //還是沒用 冏冏冏冏冏冏冏冏冏冏冏冏冏冏冏
	//Cookie cookie = new Cookie(".appcred",request.getParameter(userName));
	Cookie cookie = new Cookie(".appcred","abby");
	response.addCookie(cookie);
//    response.setHeader("Refresh","2; url= http://localhost:8080/TestStruts2/customer/customerQry.action");        
%> 

</body>
</html>