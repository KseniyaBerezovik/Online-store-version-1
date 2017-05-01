<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="connect/styles-connect.jsp" %>
    <%@ include file="connect/jstl-connect.jsp" %>
    <title>Вход</title>
</head>
<body>
<div class="container">
    <div class="col-lg-4 col-lg-offset-4">
        <form class="form-signin" action="${pageContext.request.contextPath}/login" method="post">
            <h2 class="form-signin-heading">Вход</h2>
            <label for="inputEmail" class="sr-only">Email address</label>
            <input type="email" name="email" id="inputEmail" class="form-control" placeholder="Email address" required
                   autofocus>
            <label for="inputPassword" class="sr-only">Password</label>
            <input type="password" name="pass" id="inputPassword" class="form-control" placeholder="Password"
                   required><br>
            <input class=" btn btn-lg btn-primary btn-block" type="submit" value="Вход">

            <a href="${pageContext.request.contextPath}/registration">
                <br>
                <br>
                <input class="btn btn-lg btn-success col-lg-12" value="Регистрация нового пользователя">
            </a>
        </form>
    </div>
</div>
</body>
</html>
