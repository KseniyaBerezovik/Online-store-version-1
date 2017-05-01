<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/admin">
                Home Page
            </a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li role="presentation"><a href="/addProduct">Добавить товар</a></li>
                <li role="presentation"><a href="/seeAllProducts">Просмотреть товары</a></li>
                <li role="presentation"><a href="/seeAllClients">Просмотреть пользователей</a></li>
                <li role="presentation"><a href="/deleteOrders">Удаление заказов</a></li>
                <li class="dropdown">
                    <a href="${pageContext.request.contextPath}/logout" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                        <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                        ${sessionScope.fullName}
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                        <li><a href="${pageContext.request.contextPath}/logout">Выход</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
