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
<script src="resources/jquery.js"></script>
<link href="resources/bootstrap.min.js" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
	<form action="loginnow" method="post">
		<div style="padding: 10px 100px 10px;">
			<div class="input-group">
				<span class="input-group-addon">用户</span> <input name="username"
					id="username" type="text" class="form-control" placeholder="输入用户名"
					onblur="check()">
			</div>
		</div>

		<div style="padding: 20px 100px 10px;">
			<div class="input-group">
				<span class="input-group-addon">密码</span> <input name="password"
					id="password" type="password" class="form-control"
					placeholder="输入密码" onblur="checkpwd()">
			</div>
		</div>

		<p style="padding: 20px 100px 10px;">
			<input type="submit" value="登录" class="btn btn-primary btn-lg"
				style="width: 40%; position: relative; left: 30%;">

		</p>
	</form>
</body>
</html>
