<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <%@ include file="connect/styles-connect.jsp" %>
    <%@ include file="connect/jstl-connect.jsp" %>
    <title>Вход</title>
    <fmt:setLocale value="${sessionScope.currentLanguage}"/>
    <fmt:setBundle basename="translations"/>
</head>
<body>
<div class="container">
    <div class="col-lg-4 col-lg-offset-4">
        <form class="form-signin" action="${pageContext.request.contextPath}/login" method="post">
            <h2 class="form-signin-heading">
                <fmt:message key="onlineStore"/>
            </h2>
            <input type="email" name="email" id="inputEmail" class="form-control" placeholder="<fmt:message key="email"/>"
                   required autofocus style="margin-bottom: 10px">
            <input type="password" name="pass" id="inputPassword" class="form-control" placeholder="<fmt:message key="password"/>"
                   required><br>
            <input class="btn btn-lg btn-primary btn-block" type="submit" value="<fmt:message key="login"/>">
            <br>
            <a class="btn btn-lg btn-primary btn-block" href="/registration">
                <fmt:message key="registration"/>
            </a>
        </form>
    </div>
    <div class="form-group col-lg-2">
        <form action="${pageContext.request.contextPath}/changeLanguage" method="get">
            <select name="language" onchange="submit()">
                <option value="en_US" ${sessionScope.currentLanguage eq 'en_US' ? 'selected' : ''}>En</option>
                <option value="ru_RU" ${sessionScope.currentLanguage eq 'ru_RU' ? 'selected' : ''}>Ру</option>
            </select>
        </form>
    </div>
</div>
</body>
</html>