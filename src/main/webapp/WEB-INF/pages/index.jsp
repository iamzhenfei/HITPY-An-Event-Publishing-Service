<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HIT_PY</title>
<link href="./resources/bootstrap.min.js" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>

<script> 
	function onclickname(){
		console.log("Hello World");
	}
</script>

<body>
	<nav class="navbar navbar-inverse" role="navigation">
		<div class="navbar-header">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand">HIT_PY</a>
					<ul class="nav navbar-nav navbar-right">
						<li class="navbar-right"><a href="login">登录</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li class="navbar-right"><a href="signup.jsp">注册</a></li>
					</ul>
				</div>
			</div>
		</div>
	</nav>
	<div class="jumbotron">
		<div class="container">
			<h1>别注册了</h1>
			<p>
				<button class="btn btn-primary btn-lg" onclick="onclickname()">Money
					Talks</button>
			</p>
		</div>
	</div>

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

</body>
</html>