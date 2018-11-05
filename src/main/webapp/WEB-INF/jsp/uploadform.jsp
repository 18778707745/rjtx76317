<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
    <form action="gotoAction" enctype="multipart/form-data" method="post">
        <table>
            <tr>
                <td>请选择文件：</td>
                <td><input type="file" name="file"></td>
            </tr>
            <tr>
                <td>开始上传</td>
                <td><input type="submit" value="上传"></td>
            </tr>
        </table>
    </form>
</body>
</html>