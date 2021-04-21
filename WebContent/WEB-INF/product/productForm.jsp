<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>材料庫存</title>
</head>
<body>
	<jsp:include page="../../banner.jsp"></jsp:include>
	
	<fieldset style="background-color: lightyellow">
		<legend>材料查詢</legend>
		<s:form action= "materialstoreQry">
			<!-- name="product_Type" 此參數是要讓傳給 OBject[]{ product_Type } Material_Store_Controller 
			result = dao.query(sql, new Object[] { product_Type }, -->
			<s:textfield label="材料類型" name="material_Type"></s:textfield>
			<s:submit value="查詢"></s:submit>
		</s:form>
	</fieldset>
	
	<s:if test="result.size()>0">
	<!--如果result有結果，便會觸發顯示-->
		<fieldset style="background-color: lightyellow">
			<legend>材料庫存</legend>
			<table border="1" width="100%">
				<thead>
					<tr>
						<td>材料編號</td>
						<td>材料名稱</td>
						<td>材料數量</td>
						<td>材料類型</td>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="result" status="item">
					<tr>
						<td><s:property value="material_Id" /></td>
						<td><s:property value="material_Name" /></td>
						<td><s:property value="material_Amount" /></td>
						<td><s:property value="material_Type" /></td>
					</tr>
					</s:iterator>
				</tbody>
			</table>
		</fieldset>
	</s:if>
	<s:else>
		<!--如果沒有資料-->
		<h1>
			<s:property value="message" />
		</h1>
	</s:else>
</body>
</html>