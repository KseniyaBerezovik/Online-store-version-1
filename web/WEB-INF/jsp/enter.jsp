<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вход</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/enter" method="post">
    <label for="email">Email: </label>
    <input type="text" name="email" id="email" required="true"><br>

    <label for="pass">Пароль: </label>
    <input type="password" name="pass" id="pass" required="true"><br>

    <button type="submit">Вход</button>
</form>
</body>
</html>
