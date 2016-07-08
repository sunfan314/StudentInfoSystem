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
				<th field="id" width="20">开课编号</th>
				<th field="courseId" width="20">课程号</th>
				<th field="courseName" width="70">课程名</th>
				<th field="year" width="50">开课年份</th>
				<th field="term" width="50">开课学期</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"
			onclick="newCourse()">新建课程</a> <a href="#" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="editCourse()">编辑课程</a> <a
			href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
			onclick="destroyCourse()">删除课程</a>
	</div>

	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table cellpadding="5">
				<tr>
					<td><label style="font-size: 15px;">课程信息</label></td>
				</tr>
				<tr style="display: none">
					<td>开课编号</td>
					<td><input id="id" name="courseInfo.id"
						class="easyui-validatebox"></td>
				</tr>
				<tr>
					<td>开设课程</td>
					<td><input id="course" class="easyui-combobox"
						name="courseInfo.course.courseId" panelHeight="auto"
						data-options="valueField:'courseId',textField:'courseName',url:'getCourseList.action'"></td>
				</tr>
				<tr>
					<td>开课年份</td>
					<td><input id="year" name="courseInfo.year"
						class="easyui-numberspinner" value="2016"
						data-options="min:2014,increment:1"></td>
				</tr>
				<tr>
					<td>开课学期</td>
					<td><input id="term" name="courseInfo.term"
						class="easyui-numberspinner" value="2"
						data-options="min:1,max:3,increment:1"></td>
				</tr>
			</table>
		</form>
	</div>

	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="saveCourse()">保存</a> <a href="#" class="easyui-linkbutton"
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
		function newCourse() {
			$('#dlg').dialog('open').dialog('setTitle', '新建课程');
			$('#fm').form('clear');
			url = 'addCourseInfo.action';
		}

		function editCourse() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$('#dlg').dialog('open').dialog('setTitle', '编辑课程');
				$("#id").val(row.id);
				$('#course').combobox('setValue', row.courseId);
				$("#year").numberspinner('setValue', row.year);
				$('#term').numberspinner('setValue', row.term);
				url = 'editCourseInfo.action';
			}
		}

		function saveCourse() {
			$('#fm').form('submit', {
				url : url,
				success : function(result) {
					result = JSON.parse(result);
					if (result.success) {
						$('#dlg').dialog('close'); // close the dialog
						$('#dg').datagrid('reload'); // reload the user data
					} else {
						$('#dialogInfo').text("课程时间设置错误！不能设置以往年份课程！");
						$('#dlg1').dialog('open').dialog('setTitle', '警告');
					}
				}
			});
		}

		function destroyCourse() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$.messager.confirm('提示', '确定删除该课程吗？', function(r) {
					if (r) {
						$.post('deleteCourseInfo.action', {
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