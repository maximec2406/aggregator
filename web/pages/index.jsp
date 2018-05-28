<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="indexBean" type="ru.levelp.myapp.web.IndexBean" scope="request"></jsp:useBean>
<%--
  Created by IntelliJ IDEA.
  User: ulbas
  Date: 13.05.2018
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Hello!</h1>
    <c:choose>
        <c:when test="${userName}">
            ${userName}
            <p><a href="/loginout">Login out</a></p>
        </c:when>
        <c:otherwise>
            <p><a href="/login">Login</a></p>
        </c:otherwise>
    </c:choose>

    <div>
        <table>
            <tbody>
            <c:forEach var="part" items="${indexBean.parts}"> <!--теги jstl, \${} язык el expression-->
                <tr>
                    <td>${part.partId}</td>
                    <td>${part.title}</td>
                    <td><c:if test="${part.legacy}">Legacy</c:if></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <a href="add-part">Добавить деталь</a>
    </div>

</body>
</html>
