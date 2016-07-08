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
		style="width: 950px; height: 475px" url="getClassList.action"
		toolbar="#toolbar" rownumbers="true" fitColumns="true"
		singleSelect="true">
		<thead>
			<tr>
				<th field="id" width="30">班级编号</th>
				<th field="className" width="50">班级名称</th>
				<th field="mainTeacher" width="50">班主任老师</th>
				<th field="department" width="50">班级院系</th>
				<th field="QQGroup" hidden="true">QQ群</th>
				<th field="slogan" hidden="true">班级标语</th>
				<th field="teacher" hidden="true">班主任电话</th>

			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"
			onclick="newClass()">新建班级</a> <a href="#" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="editClass()">编辑班级</a> <a
			href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
			onclick="destroyClass()">删除班级</a>
	</div>

	<div id="dlg" class="easyui-dialog"
		style="width: 380px; height: 320px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table cellpadding="5">
				<tr>
					<td><label style="font-size: 15px;">班级信息</label></td>
				</tr>
				<tr style="display: none">
					<td>班级编号</td>
					<td><input id="classId" name="temp.id"
						class="easyui-validatebox"></td>
				</tr>
				<tr>
					<td>班级名称</td>
					<td><input id="className" name="temp.className"
						class="easyui-validatebox"></td>
				</tr>
				<tr>
					<td>班级QQ群</td>
					<td><input id="classQQGroup" name="temp.QQGroup"
						class="easyui-validatebox"></td>
				</tr>
				<tr>
					<td>班级标语</td>
					<td><input id="classSlogan" name="temp.slogan"
						class="easyui-validatebox"></td>
				</tr>
				<tr>
					<td>班级院系</td>
					<td><input id="classDepartment" name="temp.department"
						class="easyui-validatebox"></td>
				</tr>
				<tr>
					<td>班主任老师</td>
					<td><input id="classMainTeacher" name="temp.mainTeacher"
						class="easyui-validatebox"></td>
				</tr>
				<tr>
					<td>老师电话</td>
					<td><input id="classTeacherPhone" name="temp.teacherPhone"
						class="easyui-validatebox"></td>
				</tr>
			</table>
		</form>
	</div>

	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="saveClass()">保存</a> <a href="#" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>

	<div id="dlg1" class="easyui-dialog"
		style="width: 300px; height: 140px; padding: 10px 20px" closed="true"
		buttons="#dlg1-buttons">
		<label id="dialogInfo" style="font-size: 15px;"></label>
	</div>

	<div id="dlg1-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="javascript:$('#dlg1').dialog('close')">确定</a>
	</div>

	<script type="text/javascript">
		$(function() {
			$('#dg').datagrid({
				onDblClickRow : function() {
					var row = $('#dg').datagrid('getSelected');
					if (row) {
						var text=row.className;
						var url="classStudentList.action?classId="+row.id;
						window.parent.Open(text,url);
					}
				}
			});
		});
		function newClass() {
			$('#dlg').dialog('open').dialog('setTitle', '新建班级');
			$('#fm').form('clear');
			url = 'addClass.action';
		}

		function editClass() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$('#dlg').dialog('open').dialog('setTitle', '编辑班级');
				$("#classId").val(row.id);
				$('#className').val(row.className);
				$("#classQQGroup").val(row.QQGroup);
				$('#classSlogan').val(row.slogan);
				$('#classDepartment').val(row.department);
				$('#classMainTeacher').val(row.mainTeacher);
				$('#classTeacherPhone').val(row.teacherPhone);
				url = 'editClass.action';
			}
		}

		function saveClass() {
			$('#fm').form('submit', {
				url : url,
				success : function(result) {
					$('#dlg').dialog('close'); // close the dialog
					$('#dg').datagrid('reload'); // reload the user data
				}
			});
		}

		function destroyClass() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$.messager.confirm('提示', '确定删除该班级吗？', function(r) {
					if (r) {
						$.post('deleteClass.action', {
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