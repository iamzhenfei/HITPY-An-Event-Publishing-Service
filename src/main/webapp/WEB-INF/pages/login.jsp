<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<script> 
	function check()
	{
		if(document.getElementById("username").value=="")
		{
			console.log(document.getElementById("username").innerHTML);
			console.log("????");
		}
	}
	
	function checkpwd()
	{
		if(document.getElementById("username").value=="")
		{
			console.log(document.getElementById("username").innerHTML);
			console.log("????");
		}
	}
</script>
<script> 
	function loginfunction()
	{
		
	}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
<script src="resources/bootstrap/js/jquery.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<link href="resources//bootstrap/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
	<form action="loginnow" method="post">
		<div style="padding: 10px 100px 10px;">
			<div class="input-group">
				<span class="input-group-addon">用户</span> <input name="username" id="username"
					type="text" class="form-control" placeholder="输入用户名"
					onblur="check()">
			</div>
		</div>
	
		<div style="padding: 20px 100px 10px;">
			<div class="input-group">
				<span class="input-group-addon">密码</span> <input name="password" id="password"
					type="password" class="form-control" placeholder="输入密码"
					onblur="checkpwd()">
			</div>
		</div>
	
		<p>
			<input type="submit" value="登录">
		</p>
	</form>
</body>
</html>