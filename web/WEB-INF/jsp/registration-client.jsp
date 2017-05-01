<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="connect/styles-connect.jsp" %>
    <%@ include file="connect/jstl-connect.jsp" %>
    <title>Registration</title>
</head>
<body>
    <div class="container">
        <div class="col-lg-4 col-lg-offset-4">
            <form class="form-signin" action="${pageContext.request.contextPath}/registration" method="post">
                <h2 class="form-signin-heading">Регистрация</h2>
                <label for="name" class="sr-only">Имя</label>
                <input type="text" name="name" id="name" class="form-control" placeholder="Имя" required autofocus><br>

                <label for="surname" class="sr-only">Фамилия</label>
                <input type=text" name="surname" id="surname" class="form-control" placeholder="Фамилия" required><br>

                <label for="email" class="sr-only">Email</label>
                <input type="email" name="email" id="email" class="form-control" placeholder="Email" required><br>

                <label for="pass" class="sr-only">Пароль</label>
                <input type="password" name="pass" id="pass" class="form-control" placeholder="Password" required><br>

                <label for="phone" class="sr-only">Телефон</label>
                <input type="number" name="phone" id="phone" class="form-control" placeholder="Телефон" required><br>

                <label for="address" class="sr-only">Адрес</label>
                <input type="text" name="address" id="address" class="form-control" placeholder="Адрес" required><br>

                <input class=" btn btn-lg btn-primary btn-block" type="submit" value="Регистрация">

            </form>
        </div>
    </div>
</div>
</body>
</html>
