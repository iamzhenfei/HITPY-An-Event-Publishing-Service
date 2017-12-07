<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>HIT_PY-发现</title>

    <style>
        body {
            background-image: url("http://cdn.akamai.steamstatic.com/steamcommunity/public/images/items/8930/80c102d6a56b1fc2a6a01c5586a88d7d1b42cbd1.jpg");
            background-repeat: no-repeat; /* 不平铺。图片只展示一次。 */
            background-size: contain;
            background-size: cover;
            background-attachment: fixed;
        }
    </style>

    <link href="./resources/bootstrap.min.js"/>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>

<script src="resources/jquery.js"></script>

<script>
    function load() {
        var user = "${username}";
        if (user == "") {
            document.getElementById("login").style.visibility = "visible";
            document.getElementById("sign").style.visibility = "visible";
            document.getElementById("logout").style.visibility = "hidden";
            document.getElementById("username").style.visibility = "hidden";
            document.getElementById("explore").style.visibility = "hidden";
        }
        else {
            document.getElementById("login").style.visibility = "hidden";
            document.getElementById("sign").style.visibility = "hidden";
            document.getElementById("logout").style.visibility = "visible";
            document.getElementById("username").style.visibility = "visible";
            document.getElementById("explore").style.visibility = "visible";
        }
    }
</script>
<body onLoad="load()">
<nav class="navbar navbar-inverse" role="navigation">
    <div class="navbar-header">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="index">HIT_PY</a>
                <ul class="nav navbar-nav navbar-right" id="login">
                    <li class="navbar-right"><a href="login">登录</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right" id="sign">
                    <li class="navbar-right"><a href="signup">注册</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right" id="username">
                    <li class="navbar-right"><a style="text-align: center"
                                                href="myspace"> ${username} </a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right" id="explore">
                    <li class="navbar-right"><a href="explore">发现</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right" id="logout">
                    <li class="navbar-right"><a href="logout">退出</a></li>
                </ul>

            </div>
        </div>
    </div>
</nav>
<div class="container" style="padding: 10px 100px 10px;">
    <h1 style="color: mintcream">在这里，发现你感兴趣的内容</h1>
</div>
<div class="jumbotron"
     style="opacity: 1; background-color: rgb(0, 0, 0);">
    <div class="bs-docs-example" style="padding: 30px 100px 10px;">
        <table class="table">
            <tbody>
            <tr>
                <th style="color: mintcream">活动名称</th>
                <th style="color: mintcream">发布者</th>
                <th style="color:mintcream;">活动时间</th>
            </tr>
            <c:forEach items="${activitylist}" var="p">
                <tr>
                    <td><a href="showActivity?aid=${p.aid}" style="color: #66ccff">${p.name}</a></td>
                    <td><a href="space?username=${p.username}" style="color: #66ccff">${p.username}</a> </td>
                    <td><a style="color: mintcream">${p.time}</a> </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>


</body>
</html>

