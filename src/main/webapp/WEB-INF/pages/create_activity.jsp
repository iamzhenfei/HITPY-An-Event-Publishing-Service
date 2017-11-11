<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="apple-touch-icon"
	href="//mindmup.s3.amazonaws.com/lib/img/apple-touch-icon.png" />
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
<script type="text/javascript" src="resources/jquery.js"> </script>
<script type="text/javascript" src="resources/ckeditor/ckeditor.js"> </script>
<script>

	function loginfunction()
	{
		
	}
</script>

<script type="text/javascript">  
window.onload = function(){  
    CKEDITOR.replace('editor1');  
};  
</script>

<script id="editor" type="text/plain">
            ${info.content}   //在这里输入编辑器的初始内容。
</script>
<!-- 创建编辑器并设置属性 -->


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HIT_PY</title>
<link href="resources/bootstrap.min.js" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

</head>
<body>
	<form action="postnow" method="post">
		<!-- 
			<select name="eventType"> 
				<option value="0"> 社团活动 </option> 
				<option value="1"> 快去上课 </option>
				<option value="2"> 要睡懒觉 </option> 
			</select> 
		 -->

		<div class="input-group-addon">
			<input type="text" id="eventName" name="eventName" value=""
				placeholder="活动名称" class="form-control" />
		</div>
		<br />

		<div class="input-group-addon">
			<input type="text" id="eventLocation" name="eventLocation" value=""
				placeholder="活动地点" class="form-control" />
		</div>
		<br/>

		<div class="input-group-addon">
			<input type="text" id="eventTime" name="eventTime" value=""
				placeholder="活动时间" class="form-control" />
		</div>
		<br/>

		<div class="adjoined-bottom">
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

		<button id="btn" class="btn btn-primary btn-lg"
			style="width: 40%; position: relative; left: 30%;">发布信息</button>
	</form>
</body>
</html>