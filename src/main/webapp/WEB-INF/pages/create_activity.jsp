<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>

<head>
    <meta charset="UTF-8">
    <title>ueditor test</title>
    <script src="resources/bootstrap/js/jquery.js"></script>
	<script src="resources/bootstrap/js/bootstrap.min.js"></script>
	<link href="resources//bootstrap/css/bootstrap.min.css" rel="stylesheet" />
    
</head>

<body>
<form action="addActivity" method="post">
<div>
<script id="container" name="content" type="text/plain">乱码了吗</script>
</div>
<button type="submit" class="btn btn-default">发表</button>
</form>

<script src="ueditor/ueditor.config.js"></script>
<script src="ueditor/ueditor.all.min.js"></script>
<script charset="utf-8" src="ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">
   var editor = UE.getEditor('container');
</script>
</body>

</html>