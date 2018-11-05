<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<h3>文件下载</h3>
	-->
	<a
		href="download?filename=${requestScope.user.headimage.originalFilename}">
		用户头像：${requestScope.user.headimage.originalFilename} </a>
</body>
</html>