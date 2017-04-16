<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/registration" method="post">
    <label for="name">Имя: </label>
    <input type="text" name="name" id="name" required="true"><br>

    <label for="surname">Фамилия: </label>
    <input type="text" name="surname" id="surname" required="true"><br>

    <label for="email">Email: </label>
    <input type="text" name="email" id="email" required="true"><br>

    <label for="pass">Пароль: </label>
    <input type="password" name="pass" id="pass" required="true"><br>

    <label for="phone">Телефон: </label>
    <input type="text" name="phone" id="phone" required="true"><br>

    <label for="address">Адрес: </label>
    <input type="text" name="address" id="address" required="true"><br>

    <button type="submit">Зарегистрировать</button>
</form>
</body>
</html>
