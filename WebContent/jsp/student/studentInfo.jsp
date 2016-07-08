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
				toolbar:'#toolbar',
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
		<div style="margin-left: 60px; margin-top: 20px; margin-right: 60px;">
			<div style="margin-bottom: 20px;">
				<label style="font-size: 15px;">上传个人照片</label>
			</div>
			<form id="uploadPicForm" method="post" enctype="multipart/form-data">
				<div>
					<input name="pic" class="easyui-filebox"
						data-options="prompt:'Please select a file.'" style="width: 100%;" />
				</div>
				<div style="margin-top: 30px;">
					<input id="uploadFileBtn" type="submit" value="Upload"
						onclick="uploadPic()" class="easyui-linkbutton"
						style="width: 100%" />
				</div>
			</form>
		</div>
	</div>


	<div id="toolbar">
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true"
			onclick="editStudentInfo()">编辑个人信息</a>
	</div>

	<div id="dlg" class="easyui-dialog"
		style="width: 350px; height: 420px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table>
				<tr>
					<td><label style="font-size: 15px">学生信息</label></td>	
				</tr>
				<tr style="display: none;">
					<td>用户Id:</td>
					<td><input id="id" name="info.id" class="easyui-validatebox"></td>
				</tr>
				<tr style="display: none;">
					<td>学号:</td>
					<td><input id="stuId" name="info.stuId" class="easyui-validatebox"></td>
				</tr>
				<tr style="display: none;">
					<td>准考证号:</td>
					<td><input id="examCardNum" name="info.examCardNum" class="easyui-validatebox"></td>
				</tr>
				<tr style="display: none;">
					<td>院系：</td>
					<td><input id="department" name="info.department" class="easyui-validatebox"></td>
				</tr>
				<tr style="display: none;">
					<td>专业:</td>
					<td><input id="major" name="info.major" class="easyui-validatebox"></td>
				</tr>
				<tr style="display: none;">
					<td>学制:</td>
					<td><input id="majorYear" name="info.majorYear" class="easyui-validatebox"></td>
				</tr>
				<tr style="display: none;">
					<td>用户照片：</td>
					<td><input id="pic" name="info.pic" class="easyui-validatebox"></td>
				</tr>
				<tr>
					<td>姓名:</td>
					<td><input id="stuName" name="info.stuName"
						class="easyui-validatebox"></td>
				</tr>
				<tr>
					<td>性别:</td>
					<td><select id="sex" name="info.sex" class="easyui-combobox"
						panelHeight="auto"">
							<option value="男">男</option>
							<option value="女">女</option>
					</select></td>
				</tr>
				<tr>
					<td>民族:</td>
					<td><input id="nation" name="info.nation"
						class="easyui-validatebox"></td>
				</tr>
				<tr>
					<td>籍贯:</td>
					<td><input id="nativePlace" name="info.nativePlace"
						class="easyui-validatebox"></td>
				</tr>
				<tr>
					<td>政治面貌:</td>
					<td><select id="politicalState" name="info.politicalState" class="easyui-combobox"
						panelHeight="auto"">
							<option value="党员">党员</option>
							<option value="团员">团员</option>
							<option value="群众">群众</option>
					</select></td>
				</tr>
				<tr>
					<td>出生日期:</td>
					<td><input id="birthday" name="info.birthday"
						class="easyui-datebox" data-options="sharedCalendar:'#cc'"></td>
				</tr>
				<tr>
					<td>入学日期:</td>
					<td><input id="admissionDay" name="info.admissionDay"
						class="easyui-datebox" data-options="sharedCalendar:'#cc'"></td>
				</tr>
				<tr>
					<td>身份证号:</td>
					<td><input id="identityID" name="info.identityID"
						class="easyui-validatebox"></td>
				</tr>
				<tr>
					<td>电话:</td>
					<td><input id="phone" name="info.phone"
						class="easyui-validatebox"></td>
				</tr>
				<tr>
					<td>QQ:</td>
					<td><input id="QQ" name="info.QQ" 
						class="easyui-validatebox"></td>
				</tr>
				<tr>
					<td>邮箱:</td>
					<td><input id="email" name="info.email"
						class="easyui-validatebox"></td>
				</tr>
				<tr>
					<td>地址:</td>
					<td><input id="address" name="info.address"
						class="easyui-validatebox"></td>
				</tr>
			</table>
		</form>
	</div>

	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="updateStudentInfo()">保存</a> <a href="#"
			class="easyui-linkbutton" iconCls="icon-cancel"
			onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>

	<div id="cc" class="easyui-calendar"></div>

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
		var userId = ${id};
		function editStudentInfo() {
			$('#dlg').dialog('open').dialog('setTitle',
			'编辑个人信息');
			$.post('getJsonStudentInfo.action', {
				id : userId
			}, function(result) {
				$('#id').val(result.data.id);
				$('#stuId').val(result.data.stuId);
				$('#stuName').val(result.data.stuName);
				$('#sex').val(result.data.sex);
				$('#nation').val(result.data.nation);
				$('#birthday').datebox('setValue',result.data.birthday);
				$('#nativePlace').val(result.data.nativePlace);
				$('#identityID').val(result.data.identityID);
				$('#phone').val(result.data.phone);
				$('#pic').val(result.data.pic);
				$('#admissionDay').datebox('setValue',result.data.admissionDay);
				$('#politicalState').val(result.data.politicalState);
				$('#examCardNum').val(result.data.examCardNum);
				$('#department').val(result.data.department);
				$('#major').val(result.data.major);
				$('#majorYear').val(result.data.majorYear);
				$('#QQ').val(result.data.QQ); 
				$('#email').val(result.data.email);
				$('#address').val(result.data.address);
			}, 'json');			
			url = 'updateStudentInfo.action';
		}

		function updateStudentInfo() {
			/* 身份证号码格式检验 */
			var str1 = document.getElementById('identityID').value.trim();
			if (str1 != "尚未填写") {
				reg = /^[0-9a-zA-Z]{18}$/;
				if (!reg.test(str1)) {
					$('#dialogInformation').text(
							"身份证号码格式错误，请输入18位身份证号码!");
					$('#dialog').dialog('open').dialog('setTitle', '警告');
					return false;
				} 
			}
			/* 手机号码格式检验 */
			var str2 = document.getElementById('phone').value.trim();
			if (str2 != "尚未填写") {
				reg = /^[0-9]{11}$/;
				if (!reg.test(str2)) {
					$('#dialogInformation').text(
							"手机号码格式错误，请输入11位手机号码!");
					$('#dialog').dialog('open').dialog('setTitle', '警告');
					return false;
				} 
			}
			/* QQ号码格式检验 */
			var str3 = document.getElementById('QQ').value.trim();
			if (str3 != "尚未填写") {
				reg = /^[0-9]+$/;
				if (!reg.test(str3)) {
					$('#dialogInformation').text(
							"QQ号码格式错误，请输入正确的QQ号码!");
					$('#dialog').dialog('open').dialog('setTitle', '警告');
					return false;
				}
			}
			/* 邮箱地址格式检验 */
			var str4 = document.getElementById('email').value.trim();
			if (str4 != "尚未填写") {
				reg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
				if (!reg.test(str4)) {
					$('#dialogInformation').text(
							"邮箱地址格式错误，请输入正确的邮箱地址!");
					$('#dialog').dialog('open').dialog('setTitle', '警告');
					return false;
				}
			}
			$('#fm').form('submit', {
				url : url,
				success : function() {
					$('#dlg').dialog('close'); // close the dialog
					$('#pg').datagrid('reload'); // reload the user data
				}
			});	
		}

		function uploadPic() {
			$('#uploadPicForm')
					.form(
							{
								url : 'uploadFile.action?id=' + userId,
								success : function(result) {
									result = JSON.parse(result);
									if (result.success) {
										$('#dialogInformation')
												.text(
														"上传个人图片成功！");
										$('#dialog').dialog('open').dialog(
												'setTitle', '成功');

									} else {
										$('#dialogInformation')
												.text(
														"上传图片失败，请检查是否选择图片或图片过大！ ");
										$('#dialog').dialog('open').dialog(
												'setTitle', '失败');
									}

								}
							});
		}
	</script>
</body>
</html>