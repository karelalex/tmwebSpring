<%@ page import="java.util.List" %>
<%@ page import="ru.karelin.tmwebspring.entity.Project" %><%--
  Created by IntelliJ IDEA.
  User: alexk
  Date: 24.04.2019
  Time: 11:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css"/>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="content">
    <h1>Список проектов</h1>
    <table>
        <colgroup>
            <col class="number">
            <col class="id">
            <col class="name">
            <col class="desc">
            <col class="action">
        </colgroup>
        <tr>
            <th>№</th>
            <th>ID</th>
            <th>Имя</th>
            <th>Описание</th>
            <th>Действия</th>
        </tr>
        <%--@elvariable id="projects" type="java.util.List"--%>
        <c:forEach items="${projects}" var="p" varStatus="iter">
            <tr>
                <td>${iter.count}</td>
                <td>${p.id}</td>
                <td>${p.name}</td>
                <td>${p.description}</td>
                <td class="action">
                    <a href="<%=request.getContextPath()%>/project/show/${p.id}"><i class="fas fa-receipt"></i></a>&nbsp;
                    <a href="<%=request.getContextPath()%>/project/edit/${p.id}"><i class="fas fa-edit"></i></a>&nbsp;
                    <a href="<%=request.getContextPath()%>/project/remove/${p.id}"><i class="fas fa-trash-alt"></i></a>&nbsp;
                    <a href="<%=request.getContextPath()%>/task/show/pid/${p.id}"><i class="fas fa-list-alt"></i></a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div class="but-cover margin_5">
        <a href="${pageContext.request.contextPath}/project/create"> <button type="button">Создать</button></a>
    </div>

</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
