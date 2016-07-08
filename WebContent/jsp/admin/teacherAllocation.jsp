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
		style="width: 950px; height: 475px" url="getCourseInfos.action"
		toolbar="#toolbar" rownumbers="true" fitColumns="true"
		singleSelect="true">
		<thead>
			<tr>
				<th field="id" width="40">开课编号</th>
				<th field="courseId" width="40">课程号</th>
				<th field="courseName" width="70">课程名</th>
				<th field="teachers" width="100">课程教师</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"
			onclick="addTeacher()">添加课程教师</a> <a href="#"
			class="easyui-linkbutton" iconCls="icon-remove" plain="true"
			onclick="deleteTeacher()">删除课程教师</a>
	</div>

	<div id="dlg" class="easyui-dialog"
		style="width: 420px; height: 300px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table cellpadding="5">
				<tr>
					<td><label style="font-size: 15px;">课程信息</label></td>
				</tr>
				<tr>
					<td>开课编号</td>
					<td><input id="id" name="courseInfo.id"
						class="easyui-validatebox" style="width:200px;" readonly></td>
				</tr>
				<tr>
					<td>课程号</td>
					<td><input id="courseId" name="courseInfo.course.id"
						class="easyui-validatebox" style="width:200px;" readonly></td>
				</tr>
				<tr>
					<td>课程名</td>
					<td><input id="courseName" name="courseInfo.course.courseName"
						class="easyui-validatebox"  style="width:200px;" readonly></td>
				</tr>
				<tr>
					<td>任课教师</td>
					<td><input id="courseTeachers" name="courseInfo.teachers"
						class="easyui-validatebox" style="width:200px;" readonly></td>
				</tr>
				<tr id="addTeacherDiv">
					<td>选择你想要添加的教师:</td>
					<td><input id="couresTeacherList" class="easyui-combobox"
						name="teacherToAdd.id"  style="width:200px;" panelHeight="auto"></td>
				</tr>
				<tr id="deleteTeacherDiv">
					<td>选择你想要删除的教师：</td>
					<td><input id="couresTeacherList_1" class="easyui-combobox"
						name="teacherToRemove.id" style="width:200px;" panelHeight="auto"></td>
				</tr>
			</table>
		</form>
	</div>

	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="saveCourseTeacher()">保存</a> <a href="#"
			class="easyui-linkbutton" iconCls="icon-cancel"
			onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>

	<script>
		function addTeacher() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$('#deleteTeacherDiv').hide();
				$('#addTeacherDiv').show();
				$('#couresTeacherList').combobox({
					url : 'getOtherTeacherList.action?id=' + row.id,
					valueField : 'id',
					textField : 'username'

				});
				$('#dlg').dialog('open').dialog('setTitle',
						'添加课程教师');
				$("#id").val(row.id);
				$("#courseId").val(row.courseId);
				$('#courseName').val(row.courseName);
				$('#courseTeachers').val(row.teachers);
				url = 'addCourseTeacher.action';
			}
		}
	</script>

	<script>
		function deleteTeacher() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$('#addTeacherDiv').hide();
				$('#deleteTeacherDiv').show();
				$('#couresTeacherList_1').combobox({
					url : 'getCourseTeacherList.action?id=' + row.id,
					valueField : 'id',
					textField : 'username'

				});
				$('#dlg').dialog('open').dialog('setTitle',
						'删除课程教师');
				$("#id").val(row.id);
				$("#courseId").val(row.courseId);
				$('#courseName').val(row.courseName);
				$('#courseTeachers').val(row.teachers);
				url = 'deleteCourseTeacher.action';
			}
		}
	</script>

	<script>
		function saveCourseTeacher() {
			$('#fm').form('submit', {
				url : url,
				success : function() {
					$('#dlg').dialog('close'); // close the dialog
					$('#dg').datagrid('reload'); // reload the user data
				}
			});
		}
	</script>
</body>
</html>