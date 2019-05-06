<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="ru.karelin.tmwebspring.entity.Project" %><%--
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
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="content">
    <%--@elvariable id="project" type="ru.karelin.tmwebspring.entity.Project"--%>
    <c:if test="${project==null}">
        <h1>Проекта с указанным id  не существует</h1>
    </c:if>
    <c:if test="${project!=null}">
        <h1>Описание проекта</h1>
        <div class="prop-cover">
            <div class="prop-name"><p>Id</p></div>
            <div class="prop-desc">${project.id}</div>
        </div>
        <div class="prop-cover">
            <div class="prop-name"><p>Название</p></div>
            <div class="prop-desc">${project.name}</div>
        </div>
        <div class="prop-cover">
            <div class="prop-name"><p>Описание</p></div>
            <div class="prop-desc">${project.description}</div>
        </div>
        <div class="prop-cover">
            <div class="prop-name"><p>Дата начала</p></div>
            <div class="prop-desc"><fmt:formatDate value="${project.startingDate}" dateStyle="long" type="date" /> </div>
        </div>
        <div class="prop-cover">
            <div class="prop-name"><p>Дата окончания</p></div>
            <div class="prop-desc"><fmt:formatDate value="${project.finishDate}" dateStyle="long" type="date"/></div>
        </div>
        <div class="prop-cover">
            <div class="prop-name"><p>Текущий статус</p></div>
            <div class="prop-desc">${project.status.displayName}</div>
        </div>
        <div class="but-cover margin_10">
            <a href="<%=request.getContextPath()%>/task/show/pid/${project.id}"> <button type="button">Задачи</button></a>
            <a href="<%=request.getContextPath()%>/project/edit/${project.id}"> <button type="button">Редактировать</button></a>
            <a href="<%=request.getContextPath()%>/project/remove/${project.id}"> <button type="button">Удалить</button></a>
        </div>
    </c:if>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
