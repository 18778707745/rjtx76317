<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
   <h2>用户注册</h2>
    <form action="register" enctype="multipart/form-data" method="post">
        <table>
            <tr>
                <td>用户头像：</td>
                <td><input type="file" name="headimage"></td>
            </tr>
            <tr>
                <td>上传：</td>
                <td><input type="submit" value="上传"></td>
            </tr>
        </table>
    </form>
</body>
</html>