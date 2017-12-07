<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>

    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-timepicker-addon/1.6.3/jquery-ui-timepicker-addon.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-timepicker-addon/1.6.3/i18n/jquery-ui-timepicker-zh-CN.js"></script>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-timepicker-addon/1.6.3/jquery-ui-timepicker-addon.min.css"/>
    <script>
        $(function () {
            $("#eventTime").datetimepicker();
        });
    </script>
    <link
            href="http://netdna.bootstrapcdn.com/font-awesome/3.0.2/css/font-awesome.css"
            rel="stylesheet">
    <script type="text/javascript" src="resources/ckeditor/ckeditor.js"></script>
    <script>

        function loginfunction() {

        }
    </script>

    <script type="text/javascript">
        window.onload = function () {
            CKEDITOR.replace('editor1');
        };
    </script>
    <script>
        function bar() {
            if ("${username}" === "") {
                document.getElementById("login").style.visibility = "visible";
                document.getElementById("sign").style.visibility = "visible";
                document.getElementById("logout").style.visibility = "hidden";
                document.getElementById("username").style.visibility = "hidden";
            }
            else {
                document.getElementById("login").style.visibility = "hidden";
                document.getElementById("sign").style.visibility = "hidden";
                document.getElementById("logout").style.visibility = "visible";
                document.getElementById("username").style.visibility = "visible";
            }
        }
    </script>

    <script id="editor" type="text/plain">
            ${info.content}   //在这里输入编辑器的初始内容。

    </script>
    <!-- 创建编辑器并设置属性 -->
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>发表一个新活动-HITPY</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>HIT_PY</title>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <style>
        body {
            background-color: rgb(0, 0, 0);
        }
    </style>
</head>
<body onload="bar()">

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
                <ul class="nav navbar-nav navbar-right" id="logout">
                    <li class="navbar-right"><a href="logout">退出</a></li>
                </ul>
            </div>
        </div>
    </div>
</nav>
<form action="postnow" method="post" style="align:center; padding: 5% 5% 20%">
    <label style="color: #53aa78">活动名称</label>
    <input type="text" id="eventName" name="eventName" value=""
           placeholder="活动名称" class="form-control"/>
    <label style="color: #53aa78">活动类型</label><br/>
    <div class="btn-group" data-toggle="buttons">
        <label class="btn btn-default">
            <input type="checkbox" name="tag" autocomplete="off" value="xuexi"> 学习
        </label>
        <label class="btn btn-default">
            <input type="checkbox" name="tag" autocomplete="off" value="qinggan"> 情感
        </label>
        <label class="btn btn-default">
            <input type="checkbox" name="tag" autocomplete="off" value="youxi"> 游戏
        </label>
        <label class="btn btn-default">
            <input type="checkbox" name="tag" autocomplete="off" value="yundong"> 运动
        </label>
        <label class="btn btn-default">
            <input type="checkbox" name="tag" autocomplete="off" value="dianying"> 电影
        </label>
        <label class="btn btn-default">
            <input type="checkbox" name="tag" autocomplete="off" value="chifan"> 吃饭
        </label>
        <label class="btn btn-default">
            <input type="checkbox" name="tag" autocomplete="off" value="lvyou"> 旅游
        </label>
        <label class="btn btn-default">
            <input type="checkbox" name="tag" autocomplete="off" value="maimaimai"> 买买买
        </label>
        <label class="btn btn-default">
            <input type="checkbox" name="tag" autocomplete="off" value="shuma"> 数码
        </label>
        <label class="btn btn-default">
            <input type="checkbox" name="tag" autocomplete="off" value="xiju"> 戏剧
        </label>
        <label class="btn btn-default">
            <input type="checkbox" name="tag" autocomplete="off" value="yinyue"> 音乐
        </label>
        <label class="btn btn-default">
            <input type="checkbox" name="tag" autocomplete="off" value="qita"> 其他
        </label>
    </div>
    <br/>
    <label style="color: #53aa78">活动地点</label>
    <input type="text" id="eventLocation" name="eventLocation" value=""
           placeholder="活动地点" class="form-control"/>
    <label style="color: #53aa78">活动时间</label>
    <input type="text" id="eventTime" name="eventTime" value=""
           placeholder="活动时间" class="form-control"/>
    <label style="color: #53aa78">活动内容</label>
    <div class="adjoined-bottom" style="align:center;">
        <div class="grid-container">
            <div class="grid-width-100">
					<textarea name="content" id="content" rows="10" cols="80">
		                This is my textarea to be replaced with CKEditor.
		            </textarea>
                <script>
                    CKEDITOR.replace('content');
                </script>
            </div>
        </div>
    </div>
    <br/>
    <br/>
    <br/>
    <button id="btn" class="btn btn-primary btn-lg"
            style="width: 40%; position: relative; left: 30%;">发布信息
    </button>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
</form>
</body>
</html>