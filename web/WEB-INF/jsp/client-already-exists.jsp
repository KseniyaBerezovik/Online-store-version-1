<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="connect/styles-connect.jsp" %>
    <%@ include file="connect/jstl-connect.jsp" %>
    <title>Registration</title>
</head>
<body>
<%@ include file="navbar.jsp" %>
<div class="myContent">
    <div class="col-lg-3 col-lg-offset-4 well">
        <h3>Клиент с таким email уже существует</h3>
        <a href="${pageContext.request.contextPath}/registration" class="btn btn-info">Вернуться к регистрации</a>
    </div>
</div>
<%@ include file="connect/js-connect.jsp" %>
</body>
</html>