<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="connect/styles-connect.jsp" %>
    <%@ include file="connect/jstl-connect.jsp" %>
    <title>Catalog</title>
</head>
<body>
<%@ include file="navbar.jsp" %>
<div class="myContent">
    <div class="container-fluid col-lg-offset-3">
        <c:forEach var="product" items="${requestScope.products}">
            <div class="row" style="margin-bottom: 40px">
                <c:if test="${product.amount > 0}">
                    <div class="col-lg-7">
                        <div class="row breadcrumb">
                            <div class="col-lg-10 pull-left">
                                <strong>${product.name}</strong>
                            </div>
                            <div class="col-lg-2">
                                <p class="pull-right"><strong><i>
                                        ${product.price} $
                                </i></strong></p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-4">
                                <img src="${pageContext.request.contextPath}/images/${product.img}"
                                     class="img-rounded img-responsive">
                            </div>
                            <div class="col-lg-8">
                                <p>${product.description}</p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <div class="pull-right">
                                    <button onclick="addToCart(${product.id})" class="btn btn-success">
                                        Добавить в корзину
                                    </button>
                                </div>
                                <div class="pull-right">
                                    <input id="productAmount_${product.id}" type="number" class="form-control"
                                           placeholder="Количество" style="width: 125px; margin-right: 10px;">
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>
        </c:forEach>
    </div>
</div>
<%@ include file="connect/js-connect.jsp" %>
</body>
</html>