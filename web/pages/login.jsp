<%--
  Created by IntelliJ IDEA.
  User: ulbas
  Date: 20.05.2018
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ligin</title>
</head>
<body>

<p style="color:red">${error}</p>

<form method="post" action="/login">
    <p><input name="user" type="text"></p>
    <p><input name="password" type="password"></p>
    <p><input type="submit"></p>
</form>

</body>
</html>
