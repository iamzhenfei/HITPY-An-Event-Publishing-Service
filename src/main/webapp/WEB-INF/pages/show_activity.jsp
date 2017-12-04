<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>

<head>
    <script>
        function load() {

            // $("<h1 style=\"color=#66ccff\">？？？</h1>").prependTo($("#forum"));

            var user = "${username}";

            console.log(user);

            if (user == "") {
                document.getElementById("joinbutton").style.visibility = "hidden";
            }
            else {
                document.getElementById("joinbutton").style.visibility = "visible";
            }

        }
    </script>
    <style>
        #MyBody {
            background-color: rgb(41, 41, 41);
        }

        nav {
            margin: 0;
            padding: 0;
        }

        div {
            margin: 0;
            padding: 0;
            border-radius: 10px;
        }

        #container {
            width: 70%;
            height: 100%;
            border: 0px solid #ccc;
            background: rgb(0, 0, 0);
        }
    </style>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>login</title>
    <script src="resources/jquery.js"></script>
    <link href="resources/bootstrap.min.js"/>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <script type="text/javascript">
        function join() {
            var aid = ${aid};
            var name = "${eventName}";
            window.location.href = "joinActivity?aid=" + aid + "&name=" + name;
        }
    </script>

</head>

<body id="MyBody" onLoad="load()">
<nav class="navbar navbar-inverse" role="navigation">
    <div class="navbar-header">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="index">HIT_PY</a>
                <ul class="nav navbar-nav navbar-right" id="username">
                    <li class="navbar-right"><a style="text-align: center"
                                                href="myspace"> ${username} </a></li>
                </ul>
            </div>
        </div>
    </div>
</nav>
<div style="padding:3% 10% 10%">
    <!-- <br />${aid}  -->
    <br/>
    <h1 style="color: mintcream">${eventName}</h1>
    <br/> <a style="color: mintcream">活动时间：${eventTime} </a>
    <br/> <a style="color: mintcream">活动地点：${eventLocation}</a>
    <div>
        <br/>
        <a style="color: mintcream;">已参加活动：</a>
        <c:forEach items="${party}" var="p">
            <a style="color: mintcream;padding:0% 5%">${p}</a> <br/>
        </c:forEach>
    </div>
    <br/>
    <div style="margin:5% 5%;background: rgba(255, 255, 255, 0.8);">
        <br/>
        <div style="padding-left: 5%;">${content} </div>
        <br/>
    </div>
    <br/> <a style="color: mintcream"> 发布人员： ${poster} </a>
    <br/> <a style="color: mintcream"> 发布时间： ${created}</a>
    <br/>
    <br/>
    <br/>
    <br/>

    <br/>
    <div style="margin: 1% 25%">
        <button onclick="join()" type="button" class="btn btn-primary btn-lg btn-block" id="joinbutton"> 参加活动</button>
    </div>
</div>
</body>

</html>
