<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="connect/styles-connect.jsp" %>
    <%@ include file="connect/jstl-connect.jsp" %>
    <title>See orders by product</title>
</head>
<body>
<%@ include file="admin-navbar.jsp" %>
<div class="myContent">
    <div class="container">
        <p>Информация о заказах для продукта: ${requestScope.productName}</p>
        <%@include file="orders-table.jsp"%>
    </div>
    <%@ include file="connect/js-connect.jsp" %>
</body>
</html>
</div>