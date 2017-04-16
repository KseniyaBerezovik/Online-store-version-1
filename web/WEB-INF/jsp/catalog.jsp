<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Catalog</title>
</head>
<body>
    <h2>Здесь будет каталог</h2>
    <h2>Клиент зашел под именем: ${sessionScope.fullName}</h2>
    <h2>Роль: ${sessionScope.role}</h2>

    <a href="${pageContext.request.contextPath}/exit">Выйти из учетной записи</a>
</body>
</html>
