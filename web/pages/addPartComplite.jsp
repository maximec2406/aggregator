<%--
  Created by IntelliJ IDEA.
  User: ulbas
  Date: 19.05.2018
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="bean" type="ru.levelp.myapp.web.AddPartCompliteBean" scope="request"></jsp:useBean>
<html>
<head>
    <title>Part added</title>
</head>
<body>

    <h1>Part  has been added successfuly.</h1>
    <div>
        <p>Part ${bean.partId}</p>
        <p>Name ${bean.title}</p>
        <p>Supplier ${bean.supplierName}</p>
    </div>

</body>
</html>
