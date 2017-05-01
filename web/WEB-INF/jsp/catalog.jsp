<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="connect/styles-connect.jsp" %>
    <%@ include file="connect/jstl-connect.jsp" %>
    <title>Catalog</title>
</head>
<body>
    <%@ include file="navbar.jsp"%>
    <div class="myContent">
        <c:forEach var="product" items="${requestScope.products}">
            <c:if test="${product.amount > 0}">
                <div class="col-lg-6 col-lg-offset-3">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                <a href="${pageContext.request.contextPath}/viewProductDetails&id=${product.id}">
                                        ${product.name}
                                </a>
                            </h3>
                        </div>
                        <div class="panel-body">
                                ${product.description}
                        </div>
                    </div>
                    <form method="post" action="${pageContext.request.contextPath}/addToCart">
                        <div class="form-group col-lg-2">
                            <label for="productAmount" class="form-label">Количество: </label>
                            <input id="productAmount" type="number" name="productAmount" class="form-control">

                            <input hidden="hidden" id="id" name="id" min="1" value="${product.id}">
                            <button type="submit" class="btn btn-success">Добавить в корзину</button>
                        </div>
                    </form>
                </div>
            </c:if>
        </c:forEach>
    </div>
    <%@ include file="connect/js-connect.jsp" %>
</body>
</html>

