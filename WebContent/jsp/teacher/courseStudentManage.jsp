<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程学生管理界面</title>
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
		style="width: 950px; height: 475px" toolbar="#toolbar"
		rownumbers="true" fitColumns="true" singleSelect="true"></table>

	<div id="toolbar">
		<table>
			<tr>
				<td>课程：</td>
				<td><select id="teacherCourse" class="easyui-combogrid"
					name="courseInfoId" style="width: 200px" panelHeight="auto"
					data-options="
			panelWidth: 500,
			idField: 'id',
			textField: 'courseName',
			url: 'getTeacherCourses.action?id=${id}',
			method: 'get',
			columns: [[
				{field:'id',title:'开课编号',width:40},
				{field:'courseId',title:'课程号',width:80},
				{field:'courseName',title:'课程名',width:120},
				{field:'year',title:'年份',width:40},
				{field:'term',title:'学期',width:40},
			]],
			fitColumns: true,
			onChange:function(newValue, oldValue){ updateDataGrid(newValue)}
			
		">
				</select></td>
				<td>操作类型：</td>
				<td><select id="actionType" name="actionType"
					class="easyui-combobox" style="width: 200px;" panelHeight="auto"
					data-options="
				onChange:function(newValue, oldValue){changeActionType(newValue)}
				">
						<option value="delete">删除学生</option>
						<option value="add">添加学生</option>
				</select></td>
				<td><input id="stuName" name="stuName"
					class="easyui-validatebox"></td>
				<td><a href="#" class="easyui-linkbutton" iconCls="icon-search"
					plain="true" onclick="searchUser()">查找用户</a></td>
				<td><a href="#" class="easyui-linkbutton" iconCls="icon-back"
					plain="true" onclick="back()">查看全体用户</a></td>
			</tr>
			<tr>
				<td></td>
				<td><a id="deleteBtn" href="#" class="easyui-linkbutton"
					iconCls="icon-remove" plain="true" onclick="deleteStudents()">删除课程学生</a></td>
				<td></td>
				<td><a id="addBtn" href="#" class="easyui-linkbutton"
					iconCls="icon-add" plain="true" disabled="true"
					onclick="addStudents()">添加课程学生</a></td>
			</tr>
		</table>

	</div>

	<script type="text/javascript">
		var courseInfoId;
		var actionType = "delete";
		$(function() {
			$('#dg').datagrid({
				url : 'getCourseStudents.action',
				columns : [ [ {
					field : 'id',
					title : '用户Id',
					width : 60
				}, {
					field : 'username',
					title : '学生学号',
					width : 90
				}, {
					field : 'status',
					title : '学籍状态',
					width : 90
				}, {
					field : 'registerTime',
					title : '注册时间',
					width : 90
				} ] ]
			});
			$('#dg').datagrid(
					{
						onDblClickRow : function() {
							var row = $('#dg').datagrid('getSelected');
							if (row) {
								var text = row.username;
								var url = "studentDetailFromTeacher.action?id="
										+ row.id;
								window.parent.Open(text, url);
							}
						}
					});
		});
		function updateDataGrid(id) {
			courseInfoId = id;
			$('#dg').datagrid('load', {
				courseInfoId : id,
				actionType : actionType
			});
		}

		function changeActionType(value) {
			if (value == "add") {
				actionType = "add";
				$('#addBtn').linkbutton('enable');
				$('#deleteBtn').linkbutton('disable');
				$('#dg').datagrid('load', {
					courseInfoId : courseInfoId,
					actionType : actionType
				});
			}
			if (value == "delete") {
				actionType = "delete";
				$('#addBtn').linkbutton('disable');
				$('#deleteBtn').linkbutton('enable');
				$('#dg').datagrid('load', {
					courseInfoId : courseInfoId,
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
			$.messager.confirm('提示', '确定要添加选中的用户吗?', function(r) {
				if (r) {
					$.post('addCourseStudents.action', {
						students : studentIdList,
						courseInfoId : courseInfoId
					}, function(result) {
						if (result.success) {
							$('#dg').datagrid('load', {
								courseInfoId : courseInfoId,
								actionType : actionType
							});
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
			$.messager.confirm('提示', '确定要删除选中的用户吗?', function(r) {
				if (r) {
					$.post('deleteCourseStudents.action', {
						students : studentIdList,
						courseInfoId : courseInfoId
					}, function(result) {
						if (result.success) {
							$('#dg').datagrid('load', {
								courseInfoId : courseInfoId,
								actionType : actionType
							});
						}

					}, 'json');

				}

			});
		}

		function searchUser() {
			var studentName = $('#stuName').val();
			$('#dg').datagrid('load', {
				courseInfoId : courseInfoId,
				actionType : actionType,
				stuName : studentName
			});
		}

		function back() {
			$('#stuName').val("");
			$('#dg').datagrid('load', {
				courseInfoId : courseInfoId,
				actionType : actionType
			});
		}

		function getSelections() {
			var ss = [];
			var rows = $('#dg').datagrid('getSelections');
			for (var i = 0; i < rows.length; i++) {
				var row = rows[i];
				ss.push('<span>' + row.itemid + ":" + row.productid + ":"
						+ row.attr1 + '</span>');
			}
			$.messager.alert('Info', ss.join('<br/>'));
		}
	</script>
</body>
</html>