<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>成绩管理</title>
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
<body class="easyui-layout">
	<div data-options="region:'north',split:false" style="height: 50px">
		<table style="padding-top: 10px">
			<tr>
				<td>课程：</td>
				<td><select id="teacherCourse" class="easyui-combogrid"
					name="courseInfoId" style="width: 230px;" panelHeight="auto"
					data-options="
			panelWidth: 500,
			idField: 'id',
			textField: 'courseName',
			url: 'getTeacherCourses.action?id=${id}',
			method: 'get',
			columns: [[
				{field:'id',title:'开课编号',width:20},
				{field:'courseId',title:'课程号',width:40},
				{field:'courseName',title:'课程名',width:80},
				{field:'year',title:'年份',width:30},
				{field:'term',title:'学期',width:20}
			]],
			fitColumns: true,
			onChange:function(newValue, oldValue){ courseSelected(newValue)}
			
		">
				</select></td>
			</tr>
		</table>
	</div>
	<div data-options="region:'center'">
		<table id="dg" class="easyui-datagrid"
			title="学生课程成绩管理"
			style="width: 100%; height: 100%;" rownumbers="true"
			fitColumns="true"
			data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#tb',
				method: 'get',
				onClickRow: onClickRow
			">
			<thead>
				<th data-options="field:'studentId',width:30">用户Id</th>
				<th data-options="field:'studentName',width:50">学生学号</th>
				<th data-options="field:'score',width:40,editor:'numberbox'">课程成绩</th>
			</thead>
		</table>
	</div>

	<div id="tb" style="height: auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" id="saveBtn"
			data-options="iconCls:'icon-save',plain:true,disabled:true"
			onclick="saveStudentScoreData()">保存</a>
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
		var courseInfoId;
		var homeworkId;
		var editIndex = undefined;
		function endEditing() {
			if (editIndex == undefined) {
				return true
			}
			if ($('#dg').datagrid('validateRow', editIndex)) {
				$('#dg').datagrid('endEdit', editIndex);
				editIndex = undefined;
				return true;
			} else {
				return false;
			}
		}
		function onClickRow(index) {
			if (editIndex != index) {
				if (endEditing()) {
					$('#dg').datagrid('selectRow', index).datagrid('beginEdit',
							index);
					editIndex = index;
				} else {
					$('#dg').datagrid('selectRow', editIndex);
				}
			}
		}
		function courseSelected(value) {
			courseInfoId = value;
			$('#dg').datagrid({
				url : 'getCourseStudentScore.action?id=' + courseInfoId
			});
			$('#saveBtn').linkbutton('enable');

		}

		function saveStudentScoreData() {
			if (endEditing()) {
				$('#dg').datagrid('acceptChanges');
			}
			var dataList = new Array();
			var rows = $('#dg').datagrid('getRows');
			for (var i = 0; i < rows.length; i++) {
				var row = rows[i];
				dataList.push(row);
			}
			$
					.post(
							'updateStudentCourseScore.action',
							{
								data : JSON.stringify(dataList)
							},
							function(result) {
								if (result.success) {
									$('#dialogInfo')
											.text(
													"你已经成功更新课程成绩信息");
									$('#dlg').dialog('open').dialog('setTitle',
											'成功');
								}
							}, 'json');
		}
	</script>
</body>
</html>