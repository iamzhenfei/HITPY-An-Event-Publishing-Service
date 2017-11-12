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
<style>
body {
    background-image:
        url("http://cdn.akamai.steamstatic.com/steamcommunity/public/images/items/8930/80c102d6a56b1fc2a6a01c5586a88d7d1b42cbd1.jpg");
    background-repeat: no-repeat; /* 不平铺。图片只展示一次。 */
    background-size: contain;
    background-size: cover;
    background-attachment: fixed;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
<script src="resources/jquery.js"></script>
<link href="resources/bootstrap.min.js" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
	<form action="loginnow" method="post">
		<div style="width: 400px;height: 300px;padding: 10px 50px;
		margin-top: 150px;margin-left: auto;margin-right: auto;
		border-style: solid;border-width:thin;background: rgba(255,255,255, 0.2);">
		
			<div class="input-group" style="width: 300px;margin: 40px 0px;">
				<span class="input-group-addon">用户</span> <input name="username"
					id="username" type="text" class="form-control" placeholder="输入用户名"
					onblur="check()" style="width: 250px;">
			</div>

			<div class="input-group" style="width: 300px;margin: 40px 0px;">
				<span class="input-group-addon">密码</span> <input name="password"
					id="password" type="password" class="form-control"
					placeholder="输入密码" onblur="checkpwd()" style="width: 250px;">
			</div>

			<p>
				<input type="submit" value="登录" class="btn btn-primary btn-lg"
					style="width: 300px;">
			</p>
		</div>
	</form>
</body>
</html>
