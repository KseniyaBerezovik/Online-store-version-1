<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="connect/styles-connect.jsp" %>
    <%@ include file="connect/jstl-connect.jsp" %>
    <title>Clients</title>
</head>
<body>
<%@ include file="admin-navbar.jsp" %>
<div class="container-fluid col-lg-6 col-lg-offset-3">
    <h4 class="sub-header col-lg-offset-4">Пользователи</h4><br>
    <div class="table-responsive">
        <table class="table table-striped table-bordered">
            <thead>
            <tr>
                <th>id</th>
                <th>Имя и фамилия</th>
                <th>Email</th>
                <th>Телефон</th>
                <th>Адрес</th>
                <th>Роль</th>
                <th>Заказы</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="client" items="${requestScope.clients}">
                <tr>
                    <td>${client.id}</td>
                    <td>${client.name} ${client.surname}</td>
                    <td>${client.email}</td>
                    <td>${client.phone}</td>
                    <td>${client.address}</td>
                    <td>${client.role}</td>
                    <td>
                        <div class="form-group col-lg-4">
                            <a class="btn btn-success" href="/seeOrdersByClient?id=${client.id}">
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

