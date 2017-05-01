<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="connect/styles-connect.jsp" %>
    <%@ include file="connect/jstl-connect.jsp" %>
    <title>Корзина</title>
</head>
<body>
<%@ include file="navbar.jsp" %>
<div class="myContent">
    <h4>Корзина пользователя: ${sessionScope.fullName}</h4>

    <c:if test="${sessionScope.cost eq 0.0}">
        <h2>В корзине нет товаров!</h2>
    </c:if>
    <c:if test="${sessionScope.cost ne 0.0}">
        <h4>Количество товаров в корзине: ${sessionScope.cartDto.amountProducts}</h4>
        <c:forEach var="entry" items="${sessionScope.cart}">
            <div class="col-lg-6 col-lg-offset-3">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                                ${entry.key.name}: ${entry.value}
                        </h3>
                    </div>
                    <div class="panel-body">
                        <c:set var="price" value="${entry.key.price}"></c:set>
                        <c:set var="amount" value="${entry.value}"></c:set>
                        Стоимость: <c:out value="${price * amount}"/><br>
                    </div>
                </div>
                <form method="post" action="${pageContext.request.contextPath}/deleteFromCart">
                    <div class="form-group col-lg-2">
                        <p><select size="1" name="amount">
                            <c:forEach var="number" begin="1" end="${entry.value}">
                                <option value="${number}">${number}</option>
                            </c:forEach>
                        </select></p>
                        <input hidden="hidden" id="id" name="id" value="${entry.key.id}">
                        <button type="submit" class="btn btn-success">Удалить из корзины</button>
                    </div>
                </form>
            </div>
        </c:forEach>

        <c:forEach var="entry" items="${sessionScope.cartContent}">
            Товар: <c:out value="${entry.key.name}"/>
            Количество: <c:out value="${entry.value}"/>
            <c:set var="price" value="${entry.key.price}"></c:set>
            <c:set var="amount" value="${entry.value}"></c:set>
            Стоимость: <c:out value="${price * amount}"/><br>
        </c:forEach>

        <div class="panel panel-primary col-lg-2 col-lg-offset-3">
            <div class="panel-body">
                Общая стоимость : ${sessionScope.cost}
            </div>
        </div>
        <div class="navbar-header">
                <a class="navbar-brand btn btn-primary" href="/createOrder">Оформить заказ</a>
                <a class="navbar-brand btn btn-primary" href="/clearCart">Очистить корзину</a>
        </div>
    </c:if>
</div>
<%@ include file="connect/js-connect.jsp" %>
</body>
</html>
