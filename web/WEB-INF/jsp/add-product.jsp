<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="connect/styles-connect.jsp" %>
    <%@ include file="connect/jstl-connect.jsp" %>
    <title>Add products</title>
</head>
<body>
<%@ include file="admin-navbar.jsp" %>
<div class="container-fluid col-lg-6 col-lg-offset-3">
    <h3 class="col-lg-offset-3">Добавление товаров</h3><br>
    <div class="table-responsive table-bordered">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>id</th>
                <th>Название</th>
                <th>Описание</th>
                <th>Цена</th>
                <th>Количество</th>
                <th>Добавить</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="product" items="${requestScope.products}">
                <tr>
                    <td>${product.id}</td>
                    <td>${product.name}</td>
                    <td>${product.description}</td>
                    <td align="center">${product.price}</td>
                    <td align="center">${product.amount}</td>
                    <td align="center">
                        <form method="post" action="${pageContext.request.contextPath}/addProduct">
                            <div class="form-group col-lg-4">
                                    <span class="input-group-addon">
                                        <label for="amount" class="form-label">Количество: </label>
                                        <input id="amount" type="number" name="amount" min="1" class="form-control"
                                               required>
                                    </span>
                                <span class="input-group-addon">
                                        <input hidden="hidden" id="id" name="id" value="${product.id}">
                                        <button type="submit" class="btn btn-success">Добавить</button>
                                    </span>
                            </div>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <a href="${pageContext.request.contextPath}/addNewProduct">
            <br>
            <input class=" btn btn-lg btn-success col-lg-3 col-lg-offset-4" value="Добавить новый товар">
        </a>
    </div>
</div>
<%@ include file="connect/js-connect.jsp" %>
</body>
</html>