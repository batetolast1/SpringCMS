<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Articles by category</title>
    <c:import url="fragments/head.jsp"/>
</head>
<body>

    <c:import url="fragments/header.jsp"/>

    <h2>Articles by category <c:out value="${categoryDto.name}"/></h2>

    <table>
        <tr>
            <th>Id</th>
            <th>Title</th>
            <th>Content</th>
            <th>Created on</th>
        </tr>
        <c:forEach var="article" items="${articleDtosByCategory}" varStatus="index">
            <tr>
                <td><c:out value="${index.index + 1}"/></td>
                <td><c:out value="${article.title}"/></td>
                <td><c:out value="${article.content}"/></td>
                <td><c:out value="${article.createdOn}"/></td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
