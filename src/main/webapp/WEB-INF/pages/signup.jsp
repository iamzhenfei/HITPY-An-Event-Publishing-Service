<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">

<head>
<meta charset="utf-8">
<title>Signup - Bootstrap Admin Template</title>

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

</head>

<body>
	<div class="account-container register">
		<div class="content clearfix">
			<form action="addUser" method="post">
				<h1>Signup for Free Account</h1>
				<div class="login-fields" style="padding: 10px 200px 10px;">
					<p>Create your free account:</p>

					<div class="input-group-addon">
						<input type="text" id="username" name="username" value=""
							placeholder="用户名" class="form-control" onblur="checkusername()" />
					</div>
					<br />

					<div class="input-group-addon">
						<input type="password" id="password" name="password" value=""
							placeholder="密码" class="form-control" />
					</div>
					<br />

					<div class="input-group-addon">
						<input type="text" id="gender" name="gender" value=""
							placeholder="性别" class="form-control" />
					</div>
					<br />

					<div class="input-group-addon">
						<input type="text" id="entryYear" name="entryYear" value=""
							placeholder="入学年份" class="form-control" />
					</div>
					<br />
					<!-- /field -->

					<div class="input-group-addon">
						<input type="text" id="ps" name="ps" value="" placeholder="个人简介"
							class="form-control" />
					</div>
				</div>
				<button id="btn" class="btn btn-primary btn-lg"
					style="width: 40%; position: relative; left: 30%;">Register</button>
			</form>
		</div>
	</div>
</body>

</html>
