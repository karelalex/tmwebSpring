<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="ru.karelin.tmwebspring.entity.Task" %>
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
    <title>Описание задачи</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="content">
    <%--@elvariable id="task" type="ru.karelin.tmwebspring.entity.Task"--%>
    <c:if test="${task==null}">
        <h1>Задачи с указанным id  не существует</h1>
    </c:if>
    <c:if test="${task!=null}">
        <h1>Описание задачи</h1>
        <div class="prop-cover">
            <div class="prop-name"><p>Id</p></div>
            <div class="prop-desc">${task.id}</div>
        </div>
        <div class="prop-cover">
            <div class="prop-name"><p>Имя Проекта</p></div>
            <div class="prop-desc"><a href="<%=request.getContextPath()%>/project/show/${task.project.id}"> ${task.project.name}</a></div>
        </div>
        <div class="prop-cover">
            <div class="prop-name"><p>Название</p></div>
            <div class="prop-desc">${task.name}</div>
        </div>
        <div class="prop-cover">
            <div class="prop-name"><p>Описание</p></div>
            <div class="prop-desc">${task.description}</div>
        </div>
        <div class="prop-cover">
            <div class="prop-name"><p>Дата начала</p></div>
            <div class="prop-desc"><fmt:formatDate value="${task.startingDate}" dateStyle="long" type="date" /> </div>
        </div>
        <div class="prop-cover">
            <div class="prop-name"><p>Дата окончания</p></div>
            <div class="prop-desc"><fmt:formatDate value="${task.finishDate}" dateStyle="long" type="date"/></div>
        </div>
        <div class="prop-cover">
            <div class="prop-name"><p>Текущий статус</p></div>
            <div class="prop-desc">${task.status.displayName}</div>
        </div>
        <div class="but-cover margin_10">
            <a href="<%=request.getContextPath()%>/task/edit/${task.id}"> <button type="button">Редактировать</button></a>
            <a href="<%=request.getContextPath()%>/task/remove/${task.id}"> <button type="button">Удалить</button></a>
        </div>
    </c:if>



</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
