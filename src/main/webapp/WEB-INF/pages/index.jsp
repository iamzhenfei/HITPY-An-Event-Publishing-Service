
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HIT_PY</title>

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

<link href="./resources/bootstrap.min.js" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>

<script> 
	function load(){
		var user = "${username}";
		
		console.log(user);
		
		
		if (user == "")
	    {	
			document.getElementById("login").style.visibility="visible";
			document.getElementById("sign").style.visibility="visible";
	    }
	    else
	    {
	    	document.getElementById("login").style.visibility="hidden";
			document.getElementById("sign").style.visibility="hidden";
	    }
		
	}
	
	function onclickname(){
		window.location.href="create_activity"; 
	}
</script>

<body onLoad="load()">

	<nav class="navbar navbar-inverse" role="navigation">
		<div class="navbar-header">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand">HIT_PY</a>
					<ul class="nav navbar-nav navbar-right" id="login">
						<li class="navbar-right"><a href="login">登录</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right" id="sign">
						<li class="navbar-right"><a href="signup">注册</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li class="navbar-right">${username}</li>
					</ul>
				</div>
			</div>
		</div>
	</nav>
	<div class="jumbotron" style="opacity:1; background-color:rgb(0,0,0);" >
		<div class="container">
			<h1 style="color=#66ccff">别注册了</h1>
			<p>
				<button class="btn btn-primary btn-lg" onclick="onclickname()">Money
					Talks</button>
			</p>
		</div>
	</div>
	<!-- 
	<div class="container">
		<div class="row">
			<div class="col-md-4">
				<p>
					<button class="btn btn-default" onclick="onclickname()">Maps</button>
				</p>
			</div>
			<div class="col-md-4">
				<p>
					<button class="btn btn-default" onclick="onclickname()">Can</button>
				</p>
			</div>
			<div class="col-md-4">
				<p>
					<button class="btn btn-default" onclick="onclickname()">Talk</button>
				</p>
			</div>
		</div>
		<hr>
	</div>
	 -->
</body>
</html>
