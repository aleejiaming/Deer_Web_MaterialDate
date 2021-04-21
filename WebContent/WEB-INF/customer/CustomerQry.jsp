<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客戶資料查詢</title>
</head>
<body>	
	<!-- jsp banner 的 styless -->
	<jsp:include page="../../banner.jsp"></jsp:include>
	<script type="text/javascript">
	//使用選擇器語法(選擇網頁物件) 下載瀏覽器引發的事件 進行初始化設定
	$(document).ready(
		//事件程序
		function(){
			//使用選擇器先挑到id=customer_id 控制一個標籤物件
			var customer_NumEL=$("#customer_Num");	
			//Binding(綁定)一個事件程序給你
				customer_NumEL.blur(
					function(){
						var cusnum = $(this).val();
						//採用AJAX
						//1.建構一個XMLHttpRequest物件
						var xhr = new XMLHttpRequest();
						//使用action將資料交給 cusid_Validate 處理
						var url ="http://localhost:8080/TestStruts2/product/cusnumValidate.action?cusnum="+customer_NumEL.val();
						//true採用非同步
						xhr.open('GET',url,true);
						//設定後端處理好了 回呼callback 準會一段事件程序處理
						xhr.onreadystatechange=function(e){
							//判斷兩個狀態 輪巡狀態state 一個Http Status code-200
							if(xhr.readyState==4 && xhr.status==200){
								//取得回應資料
								var result = xhr.responseText; //JSON String
								//反序列化成Json物件
								var jsonObject = JSON.parse(result);							
								//alter(jsonObject.information);
								$('#message').text(jsonObject.information);
															
							}else if(xhr.State==404){
								alter('請求端有問題，服務位址不對');
							}
						}
						//正式非同步請求
						xhr.send();
					}
				);
			}
		); //引發點onload
	</script>
	
	<fieldset style="background-color: lightyellow">
		<legend>訂單查詢</legend>
		<s:form method="post">  <!-- 對應 Material_Controller >> String customerorder 查詢的關鍵字 -->
		<div id="message"></div>
			<s:textfield label="客戶電話" name="customer_Num" id ="customer_Num" required="true"></s:textfield>
			<s:submit value="查詢"></s:submit>
		</s:form>
	</fieldset>

	<s:if test="result.size()>0">
		<!--如果result有結果，便會觸發顯示-->
		<fieldset>
			<legend>客戶訂單</legend>
			<table border="1" width="100%">
				<thead>
					<tr>
						<td>客戶編號</td>
						<td>客戶姓名</td>
						<td>客戶電話</td>
						<td>電子郵件信箱</td>
						<td>通訊地址</td>
						<td>城市</td>						
						<td>建檔日期</td>					
					</tr>
				</thead>
				<tbody>
					<!-- 走訪每一筆資料 -->
					<!-- 相讓資料出來方式是 最上方是名稱 下面每一條是依照 類型-五金名稱-數量 方式呈現 -->
					<!-- 走訪每一筆的情況下，我必須把客戶名子用另外的方式讀取 -->
				<s:iterator value="result" status="item">
					<tr>
						<td><s:property value="customer_Id" /></td>
						<td><s:property value="customer_Name" /></td>
						<td><s:property value="customer_Num" /></td>					
						<td><s:property value="customer_Email" /></td>	
						<td><s:property value="customer_Addres" /></td>				
						<td><s:property value="customer_City" /></td>
						<td><s:property value="create_Date" /></td>
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