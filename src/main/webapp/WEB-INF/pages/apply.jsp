<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="apple-touch-icon"
          href="//mindmup.s3.amazonaws.com/lib/img/apple-touch-icon.png"/>
    <link rel="shortcut icon"
          href="http://mindmup.s3.amazonaws.com/lib/img/favicon.ico">
    <link
            href="http://twitter.github.com/bootstrap/assets/js/google-code-prettify/prettify.css"
            rel="stylesheet">
    <link
            href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-combined.no-icons.min.css"
            rel="stylesheet">
    <link
            href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-responsive.min.css"
            rel="stylesheet">
    <link
            href="http://netdna.bootstrapcdn.com/font-awesome/3.0.2/css/font-awesome.css"
            rel="stylesheet">
    <script type="text/javascript" src="resources/jquery.js"></script>
    <script type="text/javascript" src="resources/ckeditor/ckeditor.js"></script>
    <script type="text/javascript">
        function join() {
            var contact = document.getElementById("contact").value;
            var reason = document.getElementById("reason").value;
            $.ajax({
                type: "post",
                url: "apply",
                dataType: "json",    //data传递的是一个json类型的值，而不是字符串，且必须标明dataType的类型，否则会出现400错误或者其他错误。
                data: {"aid": String("${aid}"), "contact": String(contact), "reason": String(reason)},
                success: function (data) {
                    alert(data.feedback);
                }
            });
        }
    </script>


    <!-- 创建编辑器并设置属性 -->


    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>HIT_PY</title>
    <link href="resources/bootstrap.min.js"/>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <style>
        body {
            background-color: rgb(0, 0, 0);
        }
    </style>
</head>
<body>

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
<h1 style="color: mintcream"> ${name} </h1>

<label for="reason">联系方式:</label>
<input type="text" id="contact" name="contact" value=""
       placeholder="联系方式" class="form-control"/>
<br/>
<br/>
<label for="reason">参加原因:</label>
<textarea rows="5" cols="70" id="reason" name="reason" class="form-control" placeholder="参加原因"></textarea>

<br/>
<br/>

<button id="btn" class="btn btn-primary btn-lg"
        style="width: 40%; position: relative; left: 30%;" onclick=join()>提交申请
</button>
</body>
</html>