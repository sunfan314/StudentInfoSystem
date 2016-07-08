<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息界面</title>
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

	<div data-options="region:'west',title:'学生信息',split:false"
		style="width: 700px;">

		<div data-options="region:'center'">
			<table id="pg" class="easyui-propertygrid" style="width: 100%"
				data-options="
				url:'getStudentInfo.action?id=${id}',
				method:'get',
				showGroup:true,
				scrollbarSize:0
			">
			</table>
		</div>
	</div>

	<div data-options="region:'center',title:'学生照片',split:false">
		<div style="margin-left: 60px; margin-top: 60px; margin-right: 60px;">
			<div style="margin-bottom: 20px;">
				<label style="font-size: 15px;">个人照片:</label>
			</div>
			<div id="stu_pic">
				<img
					src="http://localhost:8080/StudentInfoSystem/picture/studentPic/${id}/${picPath}"
					style="width: 100px; height: 100px" />
			</div>
		</div>
	</div>


	<div id="cc" class="easyui-calendar"></div>

	
</body>
</html>