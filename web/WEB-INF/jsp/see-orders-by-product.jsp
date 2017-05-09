<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="connect/styles-connect.jsp" %>
    <%@ include file="connect/jstl-connect.jsp" %>
    <title>See orders by product</title>
</head>
<body>
<%@ include file="admin-navbar.jsp" %>

<div class="myContent container col-lg-offset-3">
    <c:if test="${requestScope.orders ne null}">
        <p>Информация о заказах для продукта: ${requestScope.productName}</p>
        <%@include file="orders-table.jsp" %>
    </c:if>
    <c:if test="${requestScope.orders eq null}">
        <h4>Продукт ${requestScope.productName} не включен ни в какие заказы</h4>
    </c:if>
</div>
<%@ include file="connect/js-connect.jsp" %>
</body>
</html>
</div>