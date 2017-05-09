<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-lg-8">
    <table class="table table-striped table-bordered">
        <thead>
        <tr class="table-warning">
            <th>Номер заказа</th>
            <th>Дата оформления</th>
            <th>Статус заказа</th>
            <th>Состав заказа<br>(Наименование и количество)</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="order" items="${requestScope.orders}">
            <tr class="table-active">
                <td>${order.id}</td>
                <td>${order.dateOfIssue}</td>
                <td>${order.status}</td>
                <td>
                    <c:forEach var="productMap" items="${order.products.keySet()}">
                        <c:set var="value" value="${order.products[productMap]}"></c:set>
                        <p>${productMap.name} : ${value}</p>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
