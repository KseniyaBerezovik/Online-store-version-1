<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show Client</title>
</head>
<body>
    <h3>Информация о клиенте:</h3>
    <h3>Имя: ${requestScope.client.name}</h3>
    <h3>Фамилия: ${requestScope.client.surname}</h3>
    <h3>Email: ${requestScope.client.email}</h3>
    <h3>Phone: ${requestScope.client.phone}</h3>
    <h3>Address: ${requestScope.client.address}</h3>
</body>
</html>
