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
    <div class="col-lg-2 col-lg-offset-3">
        <div class="panel panel-primary">
            <div class="panel-body">
                Заказ на имя ${sessionScope.fullName} успешно оформлен!
                Номер заказа №${order.id}.
            </div>
        </div>
    </div>

    <div class="navbar-header">
        <a class="navbar-brand btn btn-primary" href="/catalog">
            На главную страницу
        </a>
    </div>
</div>
<%@ include file="connect/js-connect.jsp" %>
</body>
</html>
