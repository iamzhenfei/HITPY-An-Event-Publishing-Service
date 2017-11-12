<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
<script src="resources/jquery.js"></script>
<link href="resources/bootstrap.min.js" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script type="text/javascript">
	    function join{
	        $.ajax({
	            type:"post",
	            url:"joinActivity",
	            dataType:"json",    //data传递的是一个json类型的值，而不是字符串，且必须标明dataType的类型，否则会出现400错误或者其他错误。
	            data:{"username":${username}, "aid":${aid}},
	            success:function (data) {
	                alert(feedback);
	            }
		   	});
	    }
	</script>

</head>

<body>
	<nav class="navbar navbar-inverse" role="navigation">
		<div class="navbar-header">
			<ul class="nav navbar-nav navbar-right">
				<li class="navbar-right"><a style="text-align: center">
						${username} </a></li>
			</ul>
		</div>
	</nav>
	${username}
	<br /> ${eventName}
	<br /> ${eventTime}
	<br /> ${eventLocation}
	<br />
	<div style="margin: 5% 10%">${content}</div>
	<br /> 参与者：${poster}
	<br /> 活动发布时间：${created}
	<br />
	<button class="btn btn-primary btn-lg" onclick="join()">参加活动</button>
</body>

</html>
