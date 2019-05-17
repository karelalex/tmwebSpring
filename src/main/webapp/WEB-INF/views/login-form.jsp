<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%--
  Created by IntelliJ IDEA.
  User: alexk
  Date: 25.04.2019
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="content">

    <h1>Вход в систему</h1>
    <form action="${pageContext.request.contextPath}/spring/login" method="post" enctype="application/x-www-form-urlencoded">
        <div class="prop-cover">
            <div class="prop-name"><p>Логин</p></div>
            <div class="prop-desc">
                <input type="text" name="login" value="" placeholder="Логин"/></div>
        </div>
        <div class="prop-cover">
            <div class="prop-name"><p>Пароль</p></div>
            <div class="prop-desc"><input type="password" name="pass" placeholder="Пароль" />
            </div>
        </div>
        <div class="but-cover margin_10">
            <button type="submit">Отправить</button>
        </div>
    </form>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
