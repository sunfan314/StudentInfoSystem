<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>密码修改界面</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/easyui/themes/color.css">
<script
	src="${pageContext.request.contextPath}/jquery/jquery-2.1.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script>

</head>
<body style="padding-left: 250px; padding-top: 100px">
	<div class="easyui-panel" title=" 密码修改" style="width: 400px;">
		<div>
			<form id="ff" class="easyui-form" method="post"
				data-options="novalidate:true">
				<table cellpadding="5">
					<tr>
						<td>初始密码：</td>
						<td><input class="easyui-textbox" type="password"
							id="oldPassword" name="oldPassword" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>新密码：</td>
						<td><input class="easyui-textbox" type="password"
							id="newPassword" name="newPassword" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>请再次输入新密码：</td>
						<td><input class="easyui-textbox" type="password"
							id="newPasswordAgain" name="newPasswordAgain"
							data-options="required:true"></input></td>
					</tr>
					<tr>
						<td></td>
						<td><a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 130px" onclick="changePassword()">更新</a></td>
					</tr>

				</table>
			</form>
			>
		</div>


	</div>

	<div id="dlg" class="easyui-dialog"
		style="width: 300px; height: 140px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<label id="dialogInfo" style="font-size: 15px;"></label>
	</div>

	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="javascript:$('#dlg').dialog('close')">确定</a>
	</div>

	<script type="text/javascript">
		var userId = ${id};

		function changePassword() {
			var oldPassword = $('#oldPassword').val().trim();
			var newPassword = $('#newPassword').val().trim();
			var newPasswordAgain = $('#newPasswordAgain').val().trim();
			if (oldPassword.length == 0) {
				$('#dialogInfo').text("请输入初始密码！");
				$('#dlg').dialog('open').dialog('setTitle', '警告');
				return false;
			}
			if (newPassword.length == 0 || newPasswordAgain.length == 0) {
				$('#dialogInfo').text("请输入新密码！");
				$('#dlg').dialog('open').dialog('setTitle', '警告');
				return false;
			}
			if (newPassword == newPasswordAgain) {
				reg = /^[a-zA-Z0-9\.@#\$%\^&\*\(\)\[\]\\?\\\/\|\-~`\+\=\,\r\n\:\'\"]{6,20}$/;
				if (!reg.test(newPassword)) {
					$('#dialogInfo').text("密码格式错误，请输入6至20位由数组字母或特殊字符组成的密码！");
					$('#dlg').dialog('open').dialog('setTitle', '警告');
					$('#ff').form('clear');
					return false;
				}
				$.post('updatePassword.action', {
					oldPassword : oldPassword,
					newPassword : newPassword,
					id : userId
				}, function(result) {
					if (result.success) {
						$('#dialogInfo').text("修改密码成功！");
						$('#dlg').dialog('open').dialog('setTitle', '成功');
					} else {
						$('#dialogInfo').text("初始密码输入错误!");
						$('#dlg').dialog('open').dialog('setTitle', '警告');
					}
				}, 'json');
				
			} else {
				$('#dialogInfo').text("两次密码输入不一致！");
				$('#dlg').dialog('open').dialog('setTitle', '警告');
			}
			$('#ff').form('clear');
		}
	</script>
</body>
</html>