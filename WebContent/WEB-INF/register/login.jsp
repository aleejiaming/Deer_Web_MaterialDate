<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Deer_Web_Material</title>
</head>
<body>
<jsp:include page="../../banner.jsp"></jsp:include>
 <h1>管理者登錄</h1>
  
  	<fieldset style="background-color:lightyellow"> 	
  	<!-- 尚未完成login.action 預計把 session Filter 做出來之後 就可以導向這裡嘞 --> 	
  		<legend>會員登入</legend>
		<s:form>  	
  		<s:textfield label="使用者名稱" name="user.username"></s:textfield>
  		<s:password label="使用者密碼" name="user.userpassword"></s:password>
  		<s:submit value="確定" id="btnlogin"></s:submit>
  		<s:reset value="取消"></s:reset>  		
  		</s:form>
  		<s:property value="message"/>
  	</fieldset>
  	
</body>
</html>