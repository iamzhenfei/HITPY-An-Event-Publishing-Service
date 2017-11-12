<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

=======
>>>>>>> refs/remotes/jzf/dev
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
<script src="resources/jquery.js"></script>
<link href="resources/bootstrap.min.js" />
<link rel="stylesheet"
    href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    
    <script type="text/javascript">
        function join(){
            $.ajax({
                type:"post",
                url:"joinActivity",
                dataType:"json",    //data传递的是一个json类型的值，而不是字符串，且必须标明dataType的类型，否则会出现400错误或者其他错误。
                data:{"username":String("${username}"), "aid":String("${aid}")},
                success:function (data) {
                    alert(data.feedback);
                }
            });
        }
    </script>
    
</head>

<body>
    ${username}
    <br />${aid}
    <br /> ${eventName}
    <br /> ${eventTime}
    <br /> ${eventLocation}
    <br />
    <div style="margin:5% 10% ">
        ${content}
    </div>
    <br /> ${poster}
    <br /> ${created}
    <br />
    <c:forEach items="${party}" var="p">
            <a>${p}</a>
    </c:forEach>
    <button onclick="join()">  </button>
</body>

</html>
