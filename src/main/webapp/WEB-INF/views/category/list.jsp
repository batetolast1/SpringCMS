<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Category list</title>
</head>
<body>

    <c:import url="../fragments/header.jsp"/>

    <table>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Description</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="category" items="${categoryDtos}" varStatus="index">
            <tr>
                <td><c:out value="${index.index + 1}"/></td>
                <td><c:out value="${category.name}"/></td>
                <td><c:out value="${category.description}"/></td>
                <td>
                    <a href="${pageContext.request.contextPath}/category/edit?id=<c:out value="${category.id}"/>">Edit</a>, <a
                        href="${pageContext.request.contextPath}/category/confirm-delete?id=<c:out value="${category.id}"/>">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
