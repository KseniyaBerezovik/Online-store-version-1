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
    <h2></h2>
    <h4 class="col-lg-offset-4">Невозможно оформить заказ.<br>На складе не достаточно товаров.
        <br>Информация о недостающих товарах:<br></h4>
    <div class="container-fluid col-lg-5 col-lg-offset-3">
        <table class="table table-striped table-bordered">
            <thead>
            <tr class="table-warning">
                <th></th>
                <th>Товар</th>
                <th>Требуемое количество</th>
                <th>В наличии</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="entry" items="${requestScope.missingProducts}">
                <tr class="table-active">
                    <td class="col-lg-1 col-lg-offset-4">
                        <img src="${pageContext.request.contextPath}/images/${entry.key.img}"
                             class="img-rounded img-responsive">
                    </td>
                    <td class="col-lg-1 col-lg-offset-4">${entry.key.name}</td>
                    <td class="col-lg-1">${entry.value}</td>
                    <td class="col-lg-1">${entry.key.amount}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <a href="${pageContext.request.contextPath}/cart">
        <br>
        <input class=" btn btn-lg btn-success col-lg-3 col-lg-offset-4" value="Назад к корзине">
    </a>
</div>
<%@ include file="connect/js-connect.jsp" %>
</body>
</html>
