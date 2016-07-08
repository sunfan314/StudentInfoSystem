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
		style="width: 950px; height: 475px" url="getStudents.action"
		toolbar="#toolbar" rownumbers="true" fitColumns="true"
		singleSelect="true">
		<thead>
			<tr>
				<th field="id" width="30">用户Id</th>
				<th field="username" width="70">学生学号</th>
				<th field="password" width="70">密码</th>
				<th field="status" width="70">学籍状态</th>
				<th field="registerTime" width="70">注册时间</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"
			onclick="newUser()">新建用户</a> <a href="#" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="editUser()">编辑用户</a> <a
			href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
			onclick="destroyUser()">删除用户</a><input id="stuName" name="stuName"
			class="easyui-validatebox"><a href="#"
			class="easyui-linkbutton" iconCls="icon-search" plain="true"
			onclick="searchUser()">查找用户</a><a href="#"
			class="easyui-linkbutton" iconCls="icon-back" plain="true"
			onclick="back()">查看全体用户</a>
	</div>

	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table cellpadding="5">
				<tr>
					<td><label style="font-size: 15px;">学生信息</label></td>
				</tr>
				<tr style="display: none;">
					<td>用户Id:</td>
					<td><input id="id" name="user.id" class="easyui-validatebox"></td>
				</tr>
				<tr>
					<td>学生学号：</td>
					<td><input id="username" name="user.username"
						class="easyui-validatebox"></td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><input id="password" name="user.password"
						class="easyui-validatebox"></td>
				</tr>
				<tr>
					<td>学籍状态：</td>
					<td><select id="status" class="easyui-combobox"
						name="user.status" panelHeight="auto">
							<option value="在读">在读</option>
							<option value="离校">离校</option>
					</select></td>
				</tr>
				<tr style="display: none;">
					<td>注册时间：</td>
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

	<script type="text/javascript">
		$(function() {
			$('#dg').datagrid({
				onDblClickRow : function() {
					var row = $('#dg').datagrid('getSelected');
					if (row) {
						var text = row.username;
						var url = "studentDetail.action?id=" + row.id;
						window.parent.Open(text, url);
					}
				}
			});
		});
		function newUser() {
			$('#dlg').dialog('open').dialog('setTitle', '新建用户');
			$('#fm').form('clear');
			url = 'addStudent.action';
		}

		function editUser() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$('#dlg').dialog('open').dialog('setTitle', '编辑用户');
				$("#id").val(row.id);
				$("#username").val(row.username);
				$("#password").val(row.password);
				$("#registerTime").val(row.registerTime);
				url = 'editStudent.action';
			}
		}

		function saveUser() {
			/* 密码格式检验：6-20位包含数字字母特殊字符 */
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

		function destroyUser() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$.messager.confirm('提示', '确认删除该用户吗?', function(r) {
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

		function searchUser() {
			var studentName = $('#stuName').val();
			if (stuName == "") {
				$('#dialogInformation').text("学生学号不能为空！");
				$('#dialog').dialog('open').dialog('setTitle', '警告');
				return false;
			}
			$('#dg').datagrid('load', {
			    stuName:studentName
			});
		}
		
		function back(){
			$('#stuName').val("");
			$('#dg').datagrid('load', {
			});
		}
		
	</script>

</body>
</html>