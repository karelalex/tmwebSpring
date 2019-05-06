<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="ru.karelin.tmwebspring.enumeration.Status" %>
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
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="content">
    <%--@elvariable id="project" type="ru.karelin.tmwebspring.entity.Project"--%>
    <c:if test="${project==null}">
        <h1>Проекта с указанным id не существует</h1>
    </c:if>
    <c:if test="${project!=null}">
        <h1>Редактирование проекта</h1>
        <%--@elvariable id="project" type="ru.karelin.tmwebspring.entity.Project"--%>
        <form:form  modelAttribute="project" action="${pageContext.request.contextPath}/project/edit" method="post" enctype="application/x-www-form-urlencoded">
            <div class="prop-cover">
                <div class="prop-name"><p>Id</p></div>
                <div class="prop-desc">${project.id}</div>
                <form:input path="id" type="hidden" name="pid" />
            </div>
            <div class="prop-cover">
                <div class="prop-name"><p>Название</p></div>
                <div class="prop-desc">
                    <form:input type="text" path="name" class="prop-desc" /></div>
            </div>
            <div class="prop-cover">
                <div class="prop-name"><p>Описание</p></div>
                <div class="prop-desc"><form:textarea rows="3" path="description" />
                </div>
            </div>
            <div class="prop-cover">
                <div class="prop-name"><p>Дата начала</p></div>
                <div class="prop-desc"><form:input type="date"
                                              path="startingDate" />
                </div>
            </div>
            <div class="prop-cover">
                <div class="prop-name"><p>Дата окончания</p></div>
                <div class="prop-desc"><form:input path="finishDate" type="date" />
                </div>
            </div>
            <div class="prop-cover">
                <div class="prop-name"><p>Текущий статус</p></div>
                <div class="prop-desc"><form:select path="status">
                    <c:forEach var="stat" items="<%=Status.values()%>">
                        <option value="${stat.name()}" ${stat.name() == project.status.name() ? 'selected="selected"' : ''}>${stat.displayName}</option>
                    </c:forEach>
                </form:select></div>
            </div>
            <div class="but-cover margin_10">
                <button type="submit">Отправить</button>
            </div>
        </form:form>
    </c:if>


</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
