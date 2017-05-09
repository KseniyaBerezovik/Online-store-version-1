<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <c:if test="${sessionScope.cost eq 0.0}">
        <h3 class="col-lg-offset-4">В корзине нет товаров</h3>
    </c:if>
    <c:if test="${sessionScope.cost ne 0.0}">
        <div class="container-fluid col-lg-offset-3">
            <div class="row" style="margin-bottom: 20px">
                <c:forEach var="entry" items="${sessionScope.cart}">
                    <div class="col-lg-7">
                        <div class="row breadcrumb">
                            <div class="col-lg-8 pull-left">
                                <strong>${entry.key.name}</strong>
                            </div>
                            <div class="col-lg-4">
                                <p class="bg-warning pull-right"><strong><i>
                                    <c:if test="${entry.value ne 1}">
                                        <c:set var="price" value="${entry.key.price}"/>
                                        <c:set var="amount" value="${entry.value}"/>
                                        ${amount} * ${price}$ = ${price * amount}$
                                    </c:if>
                                    <c:if test="${entry.value eq 1}">
                                        ${entry.key.price}$
                                    </c:if>
                                </i></strong></p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-4">
                                <c:set var="image" value="${entry.key.img}"></c:set>
                                <img src="${pageContext.request.contextPath}/images/${image}"
                                     class="img-rounded img-responsive">
                            </div>
                            <div class="col-lg-8">
                                <p>${entry.key.description}</p>
                            </div>
                        </div>
                        <div class="row pull-right">
                            <div class="form-group col-lg-10">
                                <form method="post" action="${pageContext.request.contextPath}/deleteFromCart">
                                    <p><select size="1" name="amount">
                                        <c:forEach var="number" begin="1" end="${entry.value}">
                                            <option value="${number}">${number}</option>
                                        </c:forEach>
                                    </select></p>
                                    <input hidden="hidden" id="id" name="id" value="${entry.key.id}">
                                    <button type="submit" class="btn btn-warning">Удалить из корзины</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="row breadcrumb col-lg-8">
                <p><strong><i>
                    <b><h4>Общая стоимость : ${sessionScope.cost}$</h4></b>
                </i></strong></p>
                <div class="row">
                    <div class="col-lg-offset-3 col-lg-3">
                        <a class="btn btn-success" href="/createOrder">Оформить заказ</a>
                    </div>
                    <div class="col-lg-3" style="margin-right: 10px">
                        <a class="btn btn-danger" href="/clearCart">Очистить корзину</a>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
</div>
<%@ include file="connect/js-connect.jsp" %>