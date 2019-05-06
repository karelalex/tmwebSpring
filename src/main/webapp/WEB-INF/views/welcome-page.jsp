<%--
  Created by IntelliJ IDEA.
  User: alexk
  Date: 29.04.2019
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Главная страница</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css" type="text/css" />
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="content">
    <h1>Добро пожаловать на заглавную страницу нашего офигенного сайта</h1>
    <img class="big-logo" src="${pageContext.request.contextPath}/images/clock.png" alt="Часы" />
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
