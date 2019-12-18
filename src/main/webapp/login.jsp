<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登录</title>
    <link rel="shortcut icon" href="favicon.ico">
    <link href="https://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.staticfile.org/font-awesome/4.4.0/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="https://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/css/login-style.min.css?v=4.1.0" rel="stylesheet">
    <script type="text/javascript">
    	var uid = '<%=session.getAttribute("SESSION_USERNAME") %>';
    	if (uid != 'null') {
    		window.location.href = "index.jsp";
    	}
    	
    </script>
</head>

<body class="gray-bg">
<div class="middle-box text-center loginscreen  animated fadeInDown">
    <div>
        <!-- <div>
            <h1 class="logo-name">DATA</h1>
        </div> -->
        <h2>欢迎使用</h2>
        <form class="m-t" role="form">
            <div class="form-group">
                <input id="username" type="text" class="form-control" placeholder="用户名" required="">
            </div>
            <div class="form-group">
                <input id="password" type="password" class="form-control" placeholder="密码" required="">
            </div>
            <button type="button" class="btn btn-primary block full-width m-b" onclick="login()">登 录</button>
            <!-- <p class="text-muted text-center">
                <a href="login.html#">
                    <small>忘记密码了？</small>
                </a>
                |
                <a href="register.html">注册一个新账号</a>
            </p> -->
        </form>
    </div>
</div>
<script src="https://cdn.staticfile.org/jquery/2.1.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="resources/js/md5.js" type="text/javascript"></script>
<script type="text/javascript">
	function login() {
		var username = $("#username").val();
		var password = $("#password").val();
		if (username == "" || password == "") {
			alert("请填写用户名和密码后登录");
			return;
		}
		
		$.ajax({
			url : "login/web",
			data: {
				"username" : username,
				"password" : password
			},
			type:"post",
			success: function(data) {
	        	if (typeof(data) == "string") data = $.parseJSON(data);
	        	if (!data.success) {
	        		alert(data.message);
	        	} else {
	        		window.location.href = "index.jsp";
	        	}
	        	// $("#sms-message").text(data.message)
	        }
		});
		
		
	}
</script>
</body>
</html>