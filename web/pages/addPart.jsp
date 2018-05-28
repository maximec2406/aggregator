<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ulbas
  Date: 13.05.2018
  Time: 13:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="addPartBean" type="ru.levelp.myapp.web.AddPartBean" scope="request"></jsp:useBean>
<%@ taglib prefix="e" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add Part</title>
</head>
<body>

<form method="post" action="/add-part" enctype="application/x-www-form-urlencoded">
    <input type="text" id="partId" name="partId">
    <label for="partId" name>Идентификатор детали</label>
    <input type="text" id="title" name="title">
    <label for="title">Наименовани детали</label>
    <select name="supplier" id="supplier">
        <e:forEach var="supplier" items="${addPartBean.suppliers}">
            <option value="${supplier.id}">${supplier.name}</option>
        </e:forEach>
    </select>
    <label for="supplier">Производитель</label>
    <button type="submit">Сохранить</button>
</form>



</body>
</html>
