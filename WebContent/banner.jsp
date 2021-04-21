<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
 String path = request.getContextPath();
 String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<link rel="stylesheet" href="styles.css">

	<base href="<%=basePath %>">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.4/themes/base/jquery-ui.css">
 	<link rel="stylesheet" href="/resources/demos/style.css">
 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.4/jquery-ui.js"></script>
	<link rel="stylesheet" href="/TestStruts2/styles.css">
	<!-- 客戶維護的日曆選擇器 -->
	<script type="text/javascript" src="../jquery-ui-1.12.1.custom/datepicker-zh-TW.js"></script>
	
<div id='cssmenu'>
	<ul>
		<li><a href='register/login.action'><span>登入頁面</span></a></li>	
		<li><a href='register/register.action'><span>註冊作業</span></a></li> 	
		<!--   href 'customer/xxxx' 前面的customer 是action的命名空間	-->
		<li><a href='customer/customerQry.action'><span>客戶資料查詢</span></a></li>
		<li><a href='customer/customermaint.action'><span>客戶資料維護</span></a></li>	
		<li><a href='product/materialstoreQry.action'><span>材料庫存</span></a>
		<li><a href='product/materialStore.action'><span>材料庫存新增</span></a>
		<li><a href='product/orderAdd.action'><span>訂單新增</span></a>	
		<li><a href='product/orderQry.action'><span>訂單查詢</span></a>
	</ul>
</div>