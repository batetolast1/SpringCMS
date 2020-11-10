<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Authors list</title>
    <c:import url="../fragments/head.jsp"/>
</head>
<body>

    <c:import url="../fragments/header.jsp"/>

    <table>
        <tr>
            <th>Id</th>
            <th>First name</th>
            <th>Last name</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="author" items="${authorDtos}" varStatus="index">
            <tr>
                <td><c:out value="${index.index + 1}"/></td>
                <td><c:out value="${author.firstName}"/></td>
                <td><c:out value="${author.lastName}"/></td>
                <td>
                    <a href="${pageContext.request.contextPath}/author/edit?id=<c:out value="${author.id}"/>">Edit</a>,
                    <a href="${pageContext.request.contextPath}/author/confirm-delete?id=<c:out value="${author.id}"/>">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
