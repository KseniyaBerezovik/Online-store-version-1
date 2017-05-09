<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="connect/styles-connect.jsp" %>
    <%@ include file="connect/jstl-connect.jsp" %>
    <title>Products</title>
</head>
<body>
<%@ include file="admin-navbar.jsp" %>

<div class="container-fluid col-lg-6 col-lg-offset-3">
    <h4 class="col-lg-offset-5">Товары</h4>
    <div class="table-responsive">
        <table class="table table-striped table-bordered">
            <thead>
            <tr align="center">
                <th>id</th>
                <th>Название</th>
                <th>Описание</th>
                <th>Цена</th>
                <th>Количество</th>
                <th>Заказы, включающие в себя данный товар</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="product" items="${requestScope.products}">
                <tr>
                    <td>${product.id}</td>
                    <td>${product.name}</td>
                    <td>${product.description}</td>
                    <td>${product.price}</td>
                    <td align="center">${product.amount}</td>
                    <td>
                        <div class="form-group col-lg-4">
                            <a class="btn btn-success" href="/seeOrdersByProduct?id=${product.id}">
                                Просмотреть заказы
                            </a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<%@ include file="connect/js-connect.jsp" %>
</body>
</html>

