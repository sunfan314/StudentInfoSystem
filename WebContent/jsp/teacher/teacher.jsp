<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>教师用户界面</title>
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
	<div region="north" class="north" title="学生用户界面">
		<h3>欢迎,&nbsp;${user.username}&nbsp;&nbsp;&nbsp;<a href="login.action">退出登录</a></h3>
	</div>
	<div region="center" title="功能面板">
		<div class="easyui-tabs" fit="true" border="false" id="tabs">
			<div title="首页">
				<h1 style="font-size: 30px; margin: 40px;">欢迎来到教师用户界面</h1>
			</div>

		</div>
	</div>
	<div region="west" class="west" title="菜单">
		<ul id="tree"></ul>
	</div>

	<div id="tabsMenu" class="easyui-menu" style="width: 120px;">
		<div name="close">关闭</div>
		<div name="Other">关闭其他</div>
		<div name="All">关闭所有</div>
	</div>


	<script>
		//动态菜单数据
		var teacherId = ${user.id};

		var treeData = [ {
			text : "课程管理",
			children : [ {
				text : "学生管理",
				attributes : {
					url : "courseStudentManage.action?id="+teacherId
				}
			}, {
				text : "成绩管理",
				attributes : {
					url : "studentScoreManage.action?id="+teacherId
				}
			} ]
		},{
			text : "个人信息",
			children : [ {
				text : "密码修改",
				attributes : {
					url : "changePassword.action?id=" + teacherId
				}
			}]
		} ];
		
		//实例化树形菜单
		$("#tree").tree({
			data : treeData,
			lines : true,
			onClick : function(node) {
				if (node.attributes) {
					Open(node.text, node.attributes.url);
				}
			}
		});

		//在右边center区域打开菜单，新增tab
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
		};

		//绑定tabs的右键菜单
		$("#tabs").tabs({
			onContextMenu : function(e, title) {
				e.preventDefault();
				$('#tabsMenu').menu('show', {
					left : e.pageX,
					top : e.pageY
				}).data("tabTitle", title);
			}
		});

		//实例化menu的onClick事件
		$("#tabsMenu").menu({
			onClick : function(item) {
				CloseTab(this, item.name);
			}
		});

		//几个关闭事件的实现
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
	</script>

</body>
</html>

</html>