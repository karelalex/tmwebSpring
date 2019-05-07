<%@ page import="ru.karelin.tmwebspring.entity.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <title>Регистрация нового пользователя</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="content">
    <h1>Регистрация нового пользователя</h1>
    <%--@elvariable id="userDto" type="ru.karelin.tmwebspring.dto.UserRegDto"--%>
    <form:form modelAttribute="userDto" action="${pageContext.request.contextPath}/reg" method="post"
               enctype="application/x-www-form-urlencoded">
        <div class="prop-cover">
            <div class="prop-name"><p>Логин</p></div>
            <div class="prop-desc">
                <form:input type="text" path="login" placeholder="Логин"/>
                <form:errors path="login"/>
            </div>
        </div>
        <div class="prop-cover">
            <div class="prop-name"><p>Пароль</p></div>
            <div class="prop-desc">
                <form:password path="password" value="" placeholder="Пароль"/>
                <form:errors path="password"/>
            </div>
        </div>
        <div class="prop-cover">
            <div class="prop-name"><p>Повторите пароль</p></div>
            <div class="prop-desc">
                <form:password path="passwordRepeat" placeholder="Пароль ещё раз"/>
                <form:errors path="passwordRepeat"/>
            </div>
        </div>
        <div class="prop-cover">
            <div class="prop-name"><p>Имя пользователя</p></div>
            <div class="prop-desc">
                <form:input type="text" path="userName" placeholder="Иванов Иван Иваныч"/>
                <form:errors path="userName"/>
            </div>
        </div>
        <div class="but-cover margin_10">
            <button type="submit">Отправить</button>
        </div>
    </form:form>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
