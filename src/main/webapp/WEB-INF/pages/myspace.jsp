<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style>
#MyBody {
	background-color: rgb(0, 0, 0);
}

nav {
	margin: 0;
	padding: 0;
}

div {
	margin: 0;
	padding: 0
}

#container {
	width: 70%;
	height: 100%;
	border: 0px solid #ccc;
	background: rgb(0, 0, 0);
}

</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${username}的空间</title>
<script src="resources/jquery.js"></script>
<link href="resources/bootstrap.min.js" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body id="MyBody">
	<nav class="navbar navbar-inverse" role="navigation">
		<div class="navbar-header">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand" href="index">HIT_PY</a>
					<ul class="nav navbar-nav navbar-right" id="logout">
						<li class="navbar-right"><a href="logout">退出</a></li>
					</ul>
				</div>
			</div>
		</div>
	</nav>
	<div id="container">
		<h1 style="color: mintcream">${username}的空间</h1>
		<blockquote class="blockquote-reverse">
			<p style="color: mintcream">${ps}</p>
			<footer style="color: mintcream">${username}</footer>
		</blockquote>
<div id = "c2" style="width: 85%;margin-left: 7.5%; ">		
		<ul class="list-group">
  <li class="list-group-item"><b><span class="label label-default">入学年份</span> <span style="float:right"> ${entryYear}</span>   </b></li>
  <li class="list-group-item"><b><span class="label label-default">性别</span>    <span style="float:right">${gender}</span>  </b></li>
  <li class="list-group-item"><b><span class="label label-default">发起活动数量</span>     <span style="float:right">${uid}</span></b></li>
</ul>
</div>
	<h4 style="color: mintcream">我参加的活动</h4>
<div class="bs-docs-example">
            <table class="table">
              <tbody>
              	<c:forEach items="${activitylist}" var="p">
	                <tr>
	                  <td>
	                  	<a href="showActivity?aid=${p.aid}">${p.name}</a>
	                  </td>
	                </tr>
	            </c:forEach>
              </tbody>
            </table>
          </div>
	</div>

</body>
</html>