<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>班级学生管理界面</title>
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
		style="width: 950px; height: 475px" url="getClassStudents.action"
		toolbar="#toolbar" rownumbers="true" fitColumns="true"
		singleSelect="false">
		<thead>
			<tr>
				<th field="id" width="30">用户Id</th>
				<th field="username" width="70">学生学号</th>
				<th field="status" width="70">学籍状态</th>
				<th field="registerTime" width="70">注册时间</th>
			</tr>
		</thead>
	</table>

	<div id="toolbar">
		<div style="margin: 5px;">
			<table>
				<tr>
					<td>操作类型：</td>
					<td><select id="actionType" name="actionType"
						class="easyui-combobox" style="width: 200px;" panelHeight="auto"
						data-options="
				onChange:function(newValue, oldValue){changeActionType(newValue)}
				">
							<option value="delete">删除学生</option>
							<option value="add">添加学生</option>
					</select></td>
				</tr>
			</table>
		</div>
		<div style="margin-bottom: 5px;">
			<a id="deleteBtn" href="#" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" onclick="deleteStudents()">删除班级学生</a>
			<a id="addBtn" href="#" class="easyui-linkbutton" iconCls="icon-add"
				plain="true" disabled="true" onclick="addStudents()">添加班级学生</a>
		</div>

	</div>

	<script type="text/javascript">
		var idOfClass = ${classId};
		
		$(function() {
			$('#dg').datagrid('load', {
				classId : idOfClass,
				actionType : "delete"
			});
		});

		function changeActionType(value) {
			if (value == "add") {
				actionType = "add";
				$('#addBtn').linkbutton('enable');
				$('#deleteBtn').linkbutton('disable');
				$('#dg').datagrid('load', {
					classId : idOfClass,
					actionType : actionType
				});
			}
			if (value == "delete") {
				actionType = "delete";
				$('#addBtn').linkbutton('disable');
				$('#deleteBtn').linkbutton('enable');
				$('#dg').datagrid('load', {
					classId : idOfClass,
					actionType : actionType
				});
			}

		}

		function addStudents() {
			var studentIdList = '';
			var rows = $('#dg').datagrid('getSelections');
			for (var i = 0; i < rows.length; i++) {
				var row = rows[i];
				studentIdList += row.id + ":";
			}
			$.messager.confirm('提示',
					'确定添加选中的学生到班级吗?',
					function(r) {
						if (r) {
							$.post('addClassStudents.action', {
								students : studentIdList,
								classId : idOfClass
							}, function(result) {
								if (result.success) {
									$('#dg').datagrid('reload');
								}

							}, 'json');

						}

					});
		}

		function deleteStudents() {
			var studentIdList = '';
			var rows = $('#dg').datagrid('getSelections');
			for (var i = 0; i < rows.length; i++) {
				var row = rows[i];
				studentIdList += row.id + ":";
			}
			$.messager.confirm('Confirm',
					'确定删除选中的学生吗?',
					function(r) {
						if (r) {
							$.post('deleteClassStudents.action', {
								students : studentIdList,
								classId : idOfClass
							}, function(result) {
								if (result.success) {
									$('#dg').datagrid('reload');
								}

							}, 'json');

						}

					});
		}

		/* function getSelections() {
			var ss = [];
			var rows = $('#dg').datagrid('getSelections');
			for (var i = 0; i < rows.length; i++) {
				var row = rows[i];
				ss.push('<span>' + row.itemid + ":" + row.productid + ":"
						+ row.attr1 + '</span>');
			}
			$.messager.alert('Info', ss.join('<br/>'));
		} */
	</script>
</body>
</html>