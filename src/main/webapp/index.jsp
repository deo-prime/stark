<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>运维平台</title>
    <link rel="stylesheet" type="text/css" href="resources/jquery-easyui/1.7.0/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="resources/jquery-easyui/1.7.0/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="resources/jquery-easyui/1.7.0/demo/demo.css">
    <script type="text/javascript" src="resources/jquery-easyui/1.7.0/jquery.min.js"></script>
    <script type="text/javascript" src="resources/jquery-easyui/1.7.0/jquery.easyui.min.js"></script>
    <script src="resources/js/loadMenu.js"></script>
    <link rel="stylesheet" href="resources/css/wu.css">
    <script type="text/javascript">
    	var uid = '<%=session.getAttribute("SESSION_USERNAME") %>';
    	if (uid == 'null') {
    		window.location.href = "login.jsp";
    	}
    	
    	function logout() {
    		if (confirm("确定退出吗")) {
    			window.location.href = "logout.jsp";
    		}
    	}
    	
    	function changePassword() {
    		$('#dlg').dialog('open').dialog('center').dialog('setTitle','修改密码');
            $('#fm').form('clear');
    	}
    	
    	function updatePassword() {
    		var oldPassword = $("#oldPassword").val();
        	var password = $("#password").val();
        	var confirmPassword = $("#confirmPassword").val();
        	
        	if (oldPassword == "") {alert("请输入原密码"); return;}
        	if (password != confirmPassword) {alert("两次密码需要填写一致"); return;}

        	$.ajax({
        		url : "sys/user/updatePassword", 
        		type : "post",
        		dataType: "json",
        		data: {
        			"oldPassword": oldPassword,
        			"password": password
        		},
        		success: function(data) {
        			alert(data.message);
        			if (data.success) {
        				$('#dlg').dialog('close');        // close the dialog
        			}
        		}
        	});
    	}
    </script>
    <style type="text/css">
    .tabs-panels>.panel>.panel-body {
        overflow: hidden;
    }
    </style>
</head>
<body class="easyui-layout">
    <div class="wu-header" data-options="region:'north',border:false,split:true">
        <div class="wu-header-left">
            <h1>运维平台</h1>
        </div>
        <div class="wu-header-right">
            <p><!-- <strong class="easyui-tooltip" title="2条未读消息"> --><%=session.getAttribute("SESSION_USERNAME") %></strong>，欢迎您！</p>
            <p><a href="#" onclick="changePassword()">修改密码</a>|<!-- <a href="#">支持论坛</a>|<a href="#">帮助中心</a>| --><a href="#" onclick="logout()">安全退出</a></p>
        </div>
    </div>

    <!--<div data-options="region:'east',split:true" title="East" style="width:100px;"></div>-->
    <div data-options="region:'west',split:true" title="菜单栏" style="width:200px;">
        <div id="layout_west_accordion" class="easyui-accordion" data-options="border:false,fit:true">
            <!--<div title="About" data-options="iconCls:'icon-ok'" style="overflow:auto;padding:10px;">-->
            <!--<ul class="easyui-tree">-->
            <!--<li iconCls="icon-users"><a href="javascript:void(0)" data-icon="icon-users" data-link="temp/layout-3.html" iframe="0">用户管理</a></li>-->
            <!--<li iconCls="icon-users"><a href="javascript:void(0)" data-icon="icon-users" data-link="temp/layout-3.html" iframe="0">菜单管理</a></li>-->
            <!--</ul>-->
            <!--</div>-->
            <!--<div title="Help" data-options="iconCls:'icon-help'" style="padding:10px;">-->
            <!--<p>The accordion allows you to provide multiple panels and display one or more at a time. Each panel has built-in support for expanding and collapsing. Clicking on a panel header to expand or collapse that panel body. The panel content can be loaded via ajax by specifying a 'href' property. Users can define a panel to be selected. If it is not specified, then the first panel is taken by default.</p>-->
            <!--</div>-->
            <!--<div title="Ajax" data-options="href:'menuitem.html'" style="padding:10px">-->
            <!--</div>-->
        </div>
    </div>
    <div data-options="region:'center'">
        <div id="tab_pannel" class="easyui-tabs" data-options="border:false,fit:true">
            <!--<div title="首页">-->
            <div title="首页" data-options="href:'welcome.html',closable:false,iconCls:'icon-tip',cls:'pd3'"></div>
            <!--<div title="首页" data-options="href:'temp/welcome.html',closable:false,iconCls:'icon-tip',cls:'pd3'"></div>-->
        </div>
    </div>
    
    <div id="dlg" class="easyui-dialog" style="width:400px" data-options="closed:true,modal:true,border:'thin',buttons:'#dlg-buttons'">
        <form id="fm" method="post" novalidate style="margin:0;padding:20px 50px">
            <div style="margin-bottom:10px">
                <input id="oldPassword" name="oldPassword" class="easyui-passwordbox" label="原密码:" style="width:100%">
            </div>
            <div style="margin-bottom:10px">
                <input id="password" name="password" class="easyui-passwordbox" label="密码:" style="width:100%">
            </div>
            <div style="margin-bottom:10px">
            	<input id="confirmPassword" name="confirmPassword" class="easyui-passwordbox" label="确认密码:" style="width:100%">
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="updatePassword()" style="width:90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
    </div>

    <div class="wu-footer" data-options="region:'south',border:true,split:true">
        &copy; 2019 All Rights Reserved
    </div>
<script>


</script>
</body>
</html>