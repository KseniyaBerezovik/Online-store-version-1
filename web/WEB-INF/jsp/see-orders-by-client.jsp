<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="connect/styles-connect.jsp" %>
    <%@ include file="connect/jstl-connect.jsp" %>
    <title>See orders</title>
</head>
<body>
<%@ include file="admin-navbar.jsp" %>
<div class="myContent col-lg-offset-3">
    <c:if test="${requestScope.orders ne null}">
        <p>Информация о заказах пользователя ${requestScope.fullName}:</p>
        <%@include file="orders-table.jsp" %>
    </c:if>
    <c:if test="${requestScope.orders eq null}">
        <h4>У пользователя ${requestScope.fullName} нет заказов</h4>
    </c:if>
</div>
<%@ include file="connect/js-connect.jsp" %>
</body>
</html>
