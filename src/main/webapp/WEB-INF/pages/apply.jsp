<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript" src="resources/jquery.js"></script>
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
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>申请啊</title>
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