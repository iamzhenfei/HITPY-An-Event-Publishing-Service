<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>审核${aname}参加申请</title>
    <link href="https://cdn.bootcss.com/bootstrap/2.3.2/css/bootstrap.css" rel="stylesheet" type="text/css">
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
    <h4 style="color: mintcream; padding: 10px 100px 10px">参加人员列表</h4>
    <div class="accordion" id="accordion2">
        <c:forEach items="${checklist}" var="p">
            <div class="accordion-group">
                <div class="accordion-heading">
                    <div style="color: mintcream" class="pull-left">${p.joiner}：</div>
                    <div style="color: mintcream">${p.contact}</div>
                    <div class="pull-right">
                        <button class="btn btn-danger"
                                onclick='window.location.href = "declinejoin?aname=${aname}&id=${p.id}";'>拒绝
                        </button>
                    </div>
                    <div class="pull-right">
                        <button class="btn btn-success"
                                onclick='window.location.href = "acceptjoin?aname=${aname}&id=${p.id}";'>同意
                        </button>
                    </div>
                    <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2"
                       href="#collapse${p.id}">
                        点击显示申请理由
                    </a>
                </div>
                <div id="collapse${p.id}" class="accordion-body collapse" style="height: 0px; ">
                    <div class="accordion-inner" style="color: mintcream">
                            ${p.reason}
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/2.3.2/js/bootstrap.js"></script>
</body>
</html>