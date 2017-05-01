<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="connect/styles-connect.jsp" %>
    <%@ include file="connect/jstl-connect.jsp" %>
    <title>See orders</title>
</head>
<body>
<%@ include file="admin-navbar.jsp" %>
<div class="myContent">
    <div class="container">
        <c:if test="${requestScope.orders ne null}">
            <p>Информация о заказах пользователя ${requestScope.fullName}:</p>
            <%@include file="orders-table.jsp"%>
        </c:if>
        <c:if test="${requestScope.orders eq null}">
            У пользователя ${requestScope.fullName} нет заказов
        </c:if>

    </div>
</div>
    <%@ include file="connect/js-connect.jsp" %>
</body>
</html>
