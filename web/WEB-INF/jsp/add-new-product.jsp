<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="connect/styles-connect.jsp" %>
    <%@ include file="connect/jstl-connect.jsp" %>
    <title>Add Product</title>
</head>
<body>
<%@ include file="admin-navbar.jsp"%>
<div class="myContent">
    <div class="container">
        <div class="col-lg-4 col-lg-offset-4">
            <form class="form-signin" action="${pageContext.request.contextPath}/addNewProduct" method="post">
                <h2 class="form-signin-heading">Добавление товара</h2>
                <label for="name" class="sr-only">Название</label>
                <input type="text" name="name" id="name" class="form-control" placeholder="Название" required autofocus><br>

                <label for="description" class="sr-only">Описание</label>
                <input type=text" name="description" id="description" class="form-control" placeholder="Описание" required><br>

                <label for="price" class="sr-only">Цена</label>
                <input type="text" name="price" id="price" class="form-control" placeholder="Цена" required><br>

                <label for="amount" class="sr-only">Количество</label>
                <input type="number" name="amount" id="amount" class="form-control" placeholder="Количество" required><br>

                <input class=" btn btn-lg btn-primary btn-block" type="submit" value="Сохранить">
            </form>
        </div>
    </div>
</div>
</div>
</body>
</html>
