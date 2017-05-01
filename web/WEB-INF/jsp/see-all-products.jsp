<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="connect/styles-connect.jsp" %>
    <%@ include file="connect/jstl-connect.jsp" %>
    <title>Products</title>
    <style>
        .nav-sidebar > .active > a,
        .nav-sidebar > .active > a:hover,
        .nav-sidebar > .active > a:focus {
            color: #fff;
            background-color: #428bca;
        }
    </style>
</head>
<body>
<%@ include file="admin-navbar.jsp"%>
<div class="myContent">
    <div style="margin-top: 60px">
        <div class="container-fluid col-lg-10">
            <h3 class="sub-header">Товары</h3>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
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
                            <td>${product.amount}</td>
                            <td>
                                <div class="form-group col-lg-4">
                                    <a class="btn btn-info" href="/seeOrdersByProduct?id=${product.id}">
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
    </div>
</div>
<%@ include file="connect/js-connect.jsp" %>
</body>
</html>

