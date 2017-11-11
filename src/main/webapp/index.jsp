<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HIT_PY</title>

<script src="resources/bootstrap/js/jquery.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<link href="resources//bootstrap/css/bootstrap.min.css" rel="stylesheet" />

</head>
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
						<li class="navbar-right"><a href="signup">注册</a></li>
					</ul>
				</div>
			</div>
		</div>
	</nav>
	<div class="jumbotron">
		<div class="container">
			<h1>别注册了</h1>
			<p>
			    <a href = "createActivity">
				<button class="btn btn-primary btn-lg">发布活动</button>
				</a>
			</p>
		</div>
	</div>

</body>
</html>
