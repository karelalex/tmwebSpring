<%@ page import="ru.karelin.tmwebspring.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: alexk
  Date: 24.04.2019
  Time: 12:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="header">
    <div class="subheader">
        <h1>Планировщик задач</h1>
        <%--@elvariable id="user" type="ru.karelin.tmwebspring.entity.User"--%>
        <c:if test="${user!=null}">
        <p>Приветствуем в системе, ${user.username}</p>
        </c:if>
    </div>
    <ul>
        <li><a href="<%=request.getContextPath()%>/">Главная</a></li>
        <c:if test="${user==null}">
            <li><a href="<%=request.getContextPath()%>/login">Вход</a></li>
            <li><a href="<%=request.getContextPath()%>/reg">Регистрация</a></li>
        </c:if>
        <c:if test="${user!=null}">
            <li><a href="<%=request.getContextPath()%>/project/show">Проекты</a></li>
            <li><a href="${pageContext.request.contextPath}/task/show">Задачи</a></li>
            <li><a href="<%=request.getContextPath()%>/logout">Выход</a></li>
        </c:if>
    </ul>
</div>
