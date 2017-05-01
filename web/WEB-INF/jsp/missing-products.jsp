<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="connect/styles-connect.jsp" %>
    <%@ include file="connect/jstl-connect.jsp" %>
    <title>See missing products</title>
</head>
<body>
<%@ include file="navbar.jsp" %>
<div class="myContent">
    <div class="container">
        <h2></h2>
        <p>Невозможно оформить заказ. На складе не достаточно товаров.</p>
        <p>Информация о недостающих товарах:</p>
        <table class="table table-bordered">
            <thead>
            <tr class="table-warning">
                <th>Товар</th>
                <th>Требуемое количество</th>
                <th>В наличии</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="entry" items="${requestScope.missingProducts}">
                <tr class="table-active">
                    <td class="col-lg-1 col-lg-offset-4">${entry.key.name}</td>
                    <td class="col-lg-1">${entry.value}</td>
                    <td class="col-lg-1">${entry.key.amount}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<%@ include file="connect/js-connect.jsp" %>
</body>
</html>
