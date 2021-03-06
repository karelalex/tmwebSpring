<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="ru.karelin.tmwebspring.entity.Project" %>
<%@ page import="ru.karelin.tmwebspring.enumeration.Status" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.karelin.tmwebspring.entity.Task" %><%--
  Created by IntelliJ IDEA.
  User: alexk
  Date: 25.04.2019
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Создание задачи</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="content">

    <h1>Редактирование задачи</h1>
    <%--@elvariable id="task" type="ru.karelin.tmwebspring.entity.Task"--%>
    <form:form modelAttribute="task" action="${pageContext.request.contextPath}/task/edit" method="post"
               enctype="application/x-www-form-urlencoded">
        <div class="prop-cover">
            <div class="prop-name"><p>Id</p></div>
            <div class="prop-desc">${task.id}</div>
            <form:hidden path="id"/>
        </div>
        <div class="prop-cover">
            <div class="prop-name"><p>Проект</p></div>
            <div class="prop-desc">
                <form:select path="projectId">
                    <%--@elvariable id="projects" type="java.util.List<ru.karelin.tmwebspring.entity.Project>"--%>
                    <form:options items="${projects}" itemValue="id" itemLabel="name"/>
                </form:select></div>
        </div>
        <div class="prop-cover">
            <div class="prop-name"><p>Название</p></div>
            <div class="prop-desc">
                <form:input type="text" path="name" placeholder="Название задачи"/>
            </div>
        </div>
        <div class="prop-cover">
            <div class="prop-name"><p>Описание</p></div>
            <div class="prop-desc"><form:textarea rows="3" path="description"
                                                  placeholder="Описание задачи"/>
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
        <div class="prop-cover">
            <div class="prop-name"><p>Текущий статус</p></div>
            <div class="prop-desc">
                <form:select path="status">
                    <form:options items="${Status.values()}" itemLabel="displayName"/>
                </form:select></div>
        </div>
        <div class="but-cover margin_10">
            <button type="submit">Отправить</button>
        </div>
    </form:form>

</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
