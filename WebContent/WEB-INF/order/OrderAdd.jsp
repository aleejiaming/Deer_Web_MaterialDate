<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增訂單</title>
</head>
<jsp:include page="../../banner.jsp"></jsp:include>
<fieldset style="background-color:lightyellow">
	<legend>訂單資料新增</legend>
	<s:form method="post">   		
		<s:textfield label="客戶編號" name="order.customer_Id" id="customer_Id" required="required"></s:textfield>
		<s:textfield label="產品編號" name="order.product_Id" id="product_Id" required="required"></s:textfield>
		<s:textfield label="出貨數量" name="order.sales_Amount" id="sales_Amount" required="required"></s:textfield>
		<s:submit value="新增" id="btn_Save"></s:submit>
		<s:reset value="取消"></s:reset>
		</s:form>
		<s:property value="message"/>
</fieldset>
<body>

</body>
</html>