<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="connect/styles-connect.jsp" %>
    <%@ include file="connect/jstl-connect.jsp" %>
    <title>See orders</title>
</head>
<body>
<%@ include file="navbar.jsp" %>
<div class="container myContent">
    <%@include file="orders-table.jsp"%>
</div>
<%@ include file="connect/js-connect.jsp" %>
</body>
</html>
