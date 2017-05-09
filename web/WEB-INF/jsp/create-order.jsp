<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="connect/styles-connect.jsp" %>
    <%@ include file="connect/jstl-connect.jsp" %>
    <title>Create Order</title>
</head>
<body>
<%@ include file="navbar.jsp" %>
<div class="myContent">
    <c:set var="order" value="${requestScope.order}"></c:set>
    <div class="col-lg-3 col-lg-offset-4 ">
        <div class="well">
            Заказ на имя ${sessionScope.fullName} успешно оформлен!<br>
            Номер заказа ${order.id}.
        </div>
        <a class="navbar-brand btn btn-success" href="/catalog">
            На главную страницу
        </a>
    </div>
</div>
</div>
<%@ include file="connect/js-connect.jsp" %>
</body>
</html>
