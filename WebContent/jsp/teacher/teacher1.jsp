<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>��ʦ�������</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css">
<script
	src="${pageContext.request.contextPath}/jquery/jquery-2.1.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script>
<style>
article, aside, figure, footer, header, hgroup, menu, nav, section {
	display: block;
}

.west {
	width: 200px;
	padding: 10px;
}

.north {
	height: 60px;
}
</style>
</head>
<body class="easyui-layout">
	<div region="north" class="north" title="��ʦ�������">
		<h3>��ӭ,&nbsp;${user.username}&nbsp;&nbsp;&nbsp;<a href="login.action">�˳���¼</a></h3>
	</div>
	<div region="center" title="�������">
		<div class="easyui-tabs" fit="true" border="false" id="tabs">
			<div title="��ҳ">
				<h1 style="font-size: 30px; margin: 40px;">��ӭ������ʦ�������</h1>
			</div>

		</div>
	</div>
	<div region="west" class="west" title="�˵�">
		<ul id="tree"></ul>
	</div>

	<div id="tabsMenu" class="easyui-menu" style="width: 120px;">
		<div name="close">�ر�</div>
		<div name="Other">�ر�����</div>
		<div name="All">�ر�����</div>
	</div>


	<script>
		$(function() {
			//��̬�˵�����
			var teacherId=${user.id};
			
			var treeData = [ {
				text : "�γ̹���",
				children : [ {
					text : "ѧ������",
					attributes : {
						url : "courseStudentManage.action?id="+teacherId
					}
				}, {
					text : "�ɼ�����",
					attributes : {
						url : "studentScoreManage.action?id="+teacherId
					}
				} ]
			} ];

			//ʵ�������β˵�
			$("#tree").tree({
				data : treeData,
				lines : true,
				onClick : function(node) {
					if (node.attributes) {
						Open(node.text, node.attributes.url);
					}
				}
			});

			//���ұ�center����򿪲˵�������tab
			function Open(text, url) {
				if ($("#tabs").tabs('exists', text)) {
					$('#tabs').tabs('select', text);
				} else {
					var content = "<iframe frameborder='0' scrolling='auto' style='width:100%;height:99%' src="
							+ url + "></iframe>";
					$('#tabs').tabs('add', {
						title : text,
						closable : true,
						content : content
					});
				}
			}
			;

			//��tabs���Ҽ��˵�
			$("#tabs").tabs({
				onContextMenu : function(e, title) {
					e.preventDefault();
					$('#tabsMenu').menu('show', {
						left : e.pageX,
						top : e.pageY
					}).data("tabTitle", title);
				}
			});

			//ʵ����menu��onClick�¼�
			$("#tabsMenu").menu({
				onClick : function(item) {
					CloseTab(this, item.name);
				}
			});

			//�����ر��¼���ʵ��
			function CloseTab(menu, type) {
				var curTabTitle = $(menu).data("tabTitle");
				var tabs = $("#tabs");

				if (type === "close") {
					tabs.tabs("close", curTabTitle);
					return;
				}

				var allTabs = tabs.tabs("tabs");
				var closeTabsTitle = [];

				$.each(allTabs, function() {
					var opt = $(this).panel("options");
					if (opt.closable && opt.title != curTabTitle
							&& type === "Other") {
						closeTabsTitle.push(opt.title);
					} else if (opt.closable && type === "All") {
						closeTabsTitle.push(opt.title);
					}
				});

				for (var i = 0; i < closeTabsTitle.length; i++) {
					tabs.tabs("close", closeTabsTitle[i]);
				}
			}
		});
	</script>

</body>
</html>

</html>