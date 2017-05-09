<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="connect/styles-connect.jsp" %>
    <%@ include file="connect/jstl-connect.jsp" %>
    <title>Add Product</title>
</head>
<body>
<%@ include file="admin-navbar.jsp" %>
<div class="myContent container">
    <c:if test="${empty requestScope.orders}">
        <h4>В данный момент заказов нет</h4>
    </c:if>
    <c:if test="${not empty requestScope.orders}">
        <p>Информация о всех заказах:</p>
        <%@include file="orders-table.jsp" %>
        <div class="col-lg-3 col-lg-offset-1">
            <form class="form-signin" action="${pageContext.request.contextPath}/deleteOrders" method="post">
                <h2 class="form-signin-heading">Удаление заказов</h2>
                <label for="date" class="sr-only">Дата</label>
                <input type="text" name="date" id="date" class="form-control"
                       placeholder="Введите дату гггг-мм-дд" required autofocus><br>
                <input class=" btn btn-lg btn-primary btn-block" type="submit" value="Удалить">
            </form>
        </div>
    </c:if>
</div>
<%@ include file="connect/js-connect.jsp" %>
</body>
</html>
