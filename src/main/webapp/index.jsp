<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Test</title>
</head>
<body>
<input type="text" id="username" name="username" />
<input type="password" id="password" name="password"/> 
<input type="button" id="btn" value="登录"/>
<script src="resources/jquery.js"></script>
<script type="text/javascript">
    $(function () {
        $("#btn").click(function () {
            $.ajax({
                type:"post",
                url:"checks.do",
                dataType:"json",    //data传递的是一个json类型的值，而不是字符串，且必须标明dataType的类型，否则会出现400错误或者其他错误。
                data:{"username":$("#username").val(),"password":$("#password").val(),"charcode":$("#codes").val()},
                success:function (data) {
                if(data.result=="success"){
                 window.location.href='success';
                   }else
                     alert("密码错误");
                },
                error:function () {
                    alert("网络错误");
                }
            });
        });
    });
</script>
</body>
</html>