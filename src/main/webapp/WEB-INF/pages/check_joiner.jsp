<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <script src="https://code.jquery.com/jquery-2.2.4.min.js"
            integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
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
    <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
        <c:forEach items="${checklist}" var="p">
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="heading${p.id}">
                    <h4 class="panel-title">
                        <div style="color: #030206" class="pull-left">${p.joiner}：</div>
                        <div style="color: #030206">${p.contact}</div>
                        <div class="pull-right">
                            <button class="btn btn-danger btn-xs"
                                    onclick='window.location.href = "declinejoin?aname=${aname}&id=${p.id}";'>拒绝
                            </button>
                        </div>
                        <div class="pull-right">
                            <button class="btn btn-success btn-xs"
                                    onclick='window.location.href = "acceptjoin?aname=${aname}&id=${p.id}";'>同意
                            </button>
                        </div>
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                           href="#collapse${p.id}"
                           aria-expanded="false" aria-controls="collapse${p.id}">
                            展开申请理由
                        </a>
                    </h4>
                </div>
                <div id="collapse${p.id}" class="panel-collapse collapse" role="tabpanel"
                     aria-labelledby="heading${p.id}">
                    <div class="panel-body">
                            ${p.reason}
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
</body>
</html>