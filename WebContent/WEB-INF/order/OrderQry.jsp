<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="UTF-8">
<title>訂單系統查詢</title>
</head>
<body>
	<jsp:include page="../../banner.jsp"></jsp:include>
		<fieldset style="background-color: lightyellow">
		<legend>客戶訂單查詢</legend>
		<s:form action= "orderQry">
			<s:textfield label="客戶電話" name="customer_Num"></s:textfield>
			<s:submit value="查詢"></s:submit>
		</s:form>
	</fieldset>
	<s:if test="result.size()>0">
		<table border="1" width="100%" >
			<thead>
				<tr>	
					<td>訂單編號:</td>
					<td>客戶編號:</td>
					<td>客戶名稱:</td>
					<td>客戶電話:</td>
					<td>產品編號:</td>
					<td>產品名稱:</td>
					<td>產品類型:</td>
					<td>交易地點:</td>
					<td>出貨數量:</td>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="result" status="item">				
				<tr>				 
					<td><s:property value="order_Id" /></td>
					<td><s:property value="cus_Id" /></td>
					<td><s:property value="customer_Name" /></td>
					<td><s:property value="customer_Num" /></td>
					<td><s:property value="mat_Id" /></td>
					<td><s:property value="material_Name" /></td>
					<td><s:property value="material_Type" /></td>
					<td><s:property value="customer_Addres" /></td>
					<td><s:property value="sales_Amount" /></td>
				</tr>
				</s:iterator>
			</tbody>
		</table>
	</s:if>
	<s:else>
		<!--如果沒有資料-->
		<h1>
			<s:property value="message" />
		</h1>
	</s:else>
</body>
</html>