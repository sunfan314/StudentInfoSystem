<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员界面</title>
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
<body>
	<table id="dg" class="easyui-datagrid"
		style="width: 950px; height: 475px" url="getTeachers.action"
		toolbar="#toolbar" rownumbers="true" fitColumns="true"
		singleSelect="true">
		<thead>
			<tr>
				<th field="id" width="30">用户Id</th>
				<th field="username" width="70">教师编号</th>
				<th field="password" width="70">密码</th>
				<th field="registerTime" width="70">注册时间</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"
			onclick="newUser()">新建用户</a> <a href="#" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="editUser()">编辑用户</a> <a
			href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
			onclick="destroyUser()">删除用户</a>
	</div>

	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table cellpadding="5">
				<tr>
					<td><label style="font-size: 15px;">教师信息</label></td>
				</tr>
				<tr style="display: none;">
					<td>教师Id:</td>
					<td><input id="id" name="user.id" class="easyui-validatebox"></td>
				</tr>
				<tr>
					<td>教师编号：</td>
					<td><input id="username" name="user.username"
						class="easyui-validatebox"></td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><input id="password" name="user.password"
						class="easyui-validatebox"></td>
				</tr>
				<tr style="display: none">
					<td>注册时间</td>
					<td><input id="registerTime" name="user.registerTime"
						class="easyui-validatebox"></td>
				</tr>
			</table>
		</form>
	</div>

	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="saveUser()">保存</a> <a href="#" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>
	
	<div id="dialog" class="easyui-dialog"
		style="width: 300px; height: 140px; padding: 10px 20px" closed="true"
		buttons="#dialog-buttons">
		<label id="dialogInformation" style="font-size: 15px;"></label>
	</div>

	<div id="dialog-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="javascript:$('#dialog').dialog('close')">确定</a>
	</div>
	

	<script>
		function newUser() {
			$('#dlg').dialog('open').dialog('setTitle', '新建用户');
			$('#fm').form('clear');
			url = 'addTeacher.action';
		}
	</script>

	<script>
		function editUser() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$('#dlg').dialog('open').dialog('setTitle', '编辑用户');
				$("#id").val(row.id);
				$("#username").val(row.username);
				$("#password").val(row.password);
				$("#registerTime").val(row.registerTime);
				url = 'editTeacher.action';
			}
		}
	</script>

	<script>
		function saveUser() {
			var str = document.getElementById('password').value.trim();
			if (str.length != 0) {
				reg = /^[a-zA-Z0-9\.@#\$%\^&\*\(\)\[\]\\?\\\/\|\-~`\+\=\,\r\n\:\'\"]{6,20}$/;
				if (!reg.test(str)) {
					$('#dialogInformation').text(
							"密码格式错误，请输入6至20位由数组字母或特殊字符组成的密码!");
					$('#dialog').dialog('open').dialog('setTitle', '警告');
				} else {
					$('#fm').form('submit', {
						url : url,
						success : function() {
							$('#dlg').dialog('close'); // close the dialog
							$('#dg').datagrid('reload'); // reload the user data
						}
					});
				}
			} else {
				$('#dialogInformation').text("请输入用户密码！");
				$('#dialog').dialog('open').dialog('setTitle', '警告');
			}		
		}
	</script>

	<script>
		function destroyUser() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$.messager.confirm('提示', '确定删除该用户吗?', function(r) {
					if (r) {
						$.post('deleteUser.action', {
							id : row.id
						}, function(result) {
							if (result.success) {
								$('#dg').datagrid('reload');
							}

						}, 'json');

					}

				});
			}
		}
	</script>

</body>
</html>