<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"  %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>客戶資料維護</title>
</head>
<body>
	<jsp:include page="../../banner.jsp"></jsp:include>
	<script type="text/javascript">
	
	$(document).ready(			
			//事件程序
			function(){
				//使用選擇器先挑到id=customer_id 控制一個標籤物件
				var customeridEL=$("#customer_Name");
				//將單行輸入文字方塊 結合到jQuery UI Datepicker 行事曆UI
				var createdate=$("#create_Date").datepicker();
				$( "#create_Date" ).datepicker();
				var createdate2=$("#datepicker").datepicker();
				$( "#datepicker" ).datepicker();
			}
				);
	</script>		
	<fieldset style="background-color:lightyellow">
	<legend>客戶資料新增</legend>
	<!--   Struts的ValueStack -->
		<s:form method="post">   		<!--  id  資料抓取的對應 這裡對應 Material_Controller -> customeradd() -> JdbcOperation dao -->
		<s:textfield label="客戶姓名" name="customer.customer_Name" id="customer_Name" required="required"></s:textfield>
		<s:textfield label="客戶電話" name="customer.customer_Num" id="customer_Num" required="required"></s:textfield>
		<s:textfield label="電子郵件信箱" name="customer.customer_Email" id="customer_Email" required="required"></s:textfield>
		<s:textfield label="通訊地址" name="customer.customer_Address" id="customer_Address" required="required"></s:textfield>
		<s:textfield label="城市" name="customer.customer_City" id="customer_City" required="required"></s:textfield>
		<s:textfield label="建檔日期" name="customer.create_Date" id="create_Date" required="required"></s:textfield>
		<s:submit value="新增" id="btn_Save"></s:submit>
		<s:reset value="取消"></s:reset>
		</s:form>
		<s:property value="message"/>
	</fieldset>
</body>
</html>