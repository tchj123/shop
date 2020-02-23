<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
</head>
<meta charset="UTF-8">
</head>

<body>
<c:if test="${username!=null}">
    尊敬的${username}  <a href="/logout">退出登陆</a> <br>
</c:if>
<c:if test="${username==null}">
    <a href="/html/login.html">登陆</a> <br>
    <a href="/html/register.html">注册</a> <br>
</c:if>
首页嗷

</body>

</html>