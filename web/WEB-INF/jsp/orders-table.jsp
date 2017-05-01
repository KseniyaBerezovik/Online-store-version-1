<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table class="table table-bordered">
    <thead>
    <tr class="table-warning">
        <th>Номер заказа</th>
        <th>Дата оформления</th>
        <th>Статус заказа</th>
        <th>
            Состав заказа<br>(Наименование и количество)
        </th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="order" items="${requestScope.orders}">
        <tr class="table-active">
            <td class="col-lg-1 col-lg-offset-4">${order.id}</td>
            <td class="col-lg-1">${order.dateOfIssue}</td>
            <td class="col-lg-1">${order.status}</td>
            <td class="col-lg-2">
                <c:forEach var="productMap" items="${order.products.keySet()}">
                    <c:set var="value" value="${order.products[productMap]}"></c:set>
                    <p>${productMap.name} : ${value}</p>
                </c:forEach>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
