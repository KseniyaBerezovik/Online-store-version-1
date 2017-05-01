<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="connect/styles-connect.jsp" %>
    <%@ include file="connect/jstl-connect.jsp" %>
    <title>Add Product</title>
</head>
<body>
<%@ include file="admin-navbar.jsp" %>
<div class="myContent">
    <div class="container col-lg-6">
        <p>Информация о всех заказах:</p>
        <%@include file="orders-table.jsp" %>
    </div>
    <div class="col-lg-3 col-lg-offset-2">
        <form class="form-signin" action="${pageContext.request.contextPath}/deleteOrders" method="post">
            <h2 class="form-signin-heading">Удаление заказов</h2>
            <label for="date" class="sr-only">Дата</label>
            <input type="text" name="date" id="date" class="form-control" placeholder="Введите дату в формате гггг-мм-дд" required autofocus><br>

            <input class=" btn btn-lg btn-primary btn-block" type="submit" value="Удалить">
        </form>
    </div>
</div>
<%@ include file="connect/js-connect.jsp" %>
</body>
</html>
