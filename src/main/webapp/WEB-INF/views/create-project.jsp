<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.Date" %>
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
    <h1>Создание проекта</h1>
    <%--@elvariable id="project" type="ru.karelin.tmwebspring.entity.Project"--%>
    <form:form modelAttribute="project" action="${pageContext.request.contextPath}/project/create" method="post"
               enctype="application/x-www-form-urlencoded">
        <form:input path="id" type="hidden"/>
        <div class="prop-cover">
            <div class="prop-name"><p>Название</p></div>
            <div class="prop-desc">
                <form:input path="name" placeholder="Название проекта"/></div>
        </div>
        <div class="prop-cover">
            <div class="prop-name"><p>Описание</p></div>
            <div class="prop-desc"><form:textarea rows="3" path="description" placeholder="Описание проекта"/>
            </div>
        </div>
        <div class="prop-cover">
            <div class="prop-name"><p>Дата начала</p></div>
            <div class="prop-desc"><form:input type="date" path="startingDate"/>
            </div>
        </div>
        <div class="prop-cover">
            <div class="prop-name"><p>Дата окончания</p></div>
            <div class="prop-desc"><form:input type="date" path="finishDate"/>
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
