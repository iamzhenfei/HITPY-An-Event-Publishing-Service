<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">

<head>
<meta charset="utf-8">
<title>Signup</title>

<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">

<link href="resources/bootstrap.min.css" rel="stylesheet"
	type="text/css" />

<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />

<link href="resources/font-awesome.css" rel="stylesheet">
<script src="resources/jquery.js"></script>
<script type="text/javascript">
    function checkusername(){
        $.ajax({
            type:"post",
            url:"ifUserExist",
            dataType:"json",    //data传递的是一个json类型的值，而不是字符串，且必须标明dataType的类型，否则会出现400错误或者其他错误。
            data:{"username":$("#username").val()},
            success:function (data) {
	            if(data.result=="1")
	            {
	            		alert("这个用户名不行");
	            }
            },
            error:function () {
                alert("网络错误");
            }
	   	});
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
</head>

<body>

    <h1 style="width: 125px;font-size: 50px;margin-left: auto;margin-right: auto;color:mintcream">注 册</h1>
	<form action="addUser" method="post">
        <div style="width: 400px;height: 500px;padding: 10px 50px;
        margin-top: 50px;margin-left: auto;margin-right: auto;
        border-style: solid;border-width:thin;background: rgba(255,255,255, 0.2);">
        
		<div class="login-fields">
			<div class="input-group" style="width: 300px;margin: 40px 0px;">
				<input type="text" id="username" name="username" value=""
					placeholder="用户名" class="form-control" onblur="checkusername()" />
			</div>

			<div class="input-group" style="width: 300px;margin: 40px 0px;">
				<input type="password" id="password" name="password" value=""
					placeholder="密码" class="form-control" />
			</div>

			<div class="input-group" style="width: 300px;margin: 40px 0px;">
				<input type="text" id="gender" name="gender" value=""
					placeholder="性别" class="form-control" />
			</div>

			<div class="input-group" style="width: 300px;margin: 40px 0px;">
				<input type="text" id="entryYear" name="entryYear" value=""
					placeholder="入学年份" class="form-control" />
			</div>

			<div class="input-group" style="width: 300px;margin: 40px 0px;">
				<input type="text" id="ps" name="ps" value="" placeholder="个人简介"
					class="form-control" />
			</div>
		</div>
		<button id="btn" class="btn btn-primary btn-lg"
			style="width: 300px;">注册</button>
		</div>

	</form>

</body>

</html>
