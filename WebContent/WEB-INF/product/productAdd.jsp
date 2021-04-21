<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>材料庫存新增</title>
</head>
<body>
	<jsp:include page="../../banner.jsp"></jsp:include>
	<fieldset style="background-color:lightyellow">
	<legend>材料庫存新增</legend>
	<!--   Struts的ValueStack -->
		<s:form method="post">  
		<s:textfield label="材料名稱" name="material.material_Name" id="material_Name" required="required"></s:textfield>
		<s:textfield label="材料數量" name="material.material_Amount" id="material_Amount" required="required"></s:textfield>
		<s:textfield label="材料類型" name="material.material_Type" id="material_Type" required="required"></s:textfield>
		<s:submit value="新增" id="btn_Save"></s:submit>
		<s:reset value="取消"></s:reset>
		</s:form>
		<s:property value="message"/>
	</fieldset>
</body>
</html>