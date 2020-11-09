<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Articles list</title>
</head>
<body>

    <c:import url="../fragments/header.jsp"/>

    <table>
        <tr>
            <th>Id</th>
            <th>Title</th>
            <th>Content</th>
            <th>Created on</th>
            <th>Author</th>
            <th>Categories</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="article" items="${articleDtos}" varStatus="index">
            <tr>
                <td><c:out value="${index.index + 1}"/></td>
                <td><c:out value="${article.title}"/></td>
                <td><c:out value="${article.content}"/></td>
                <td><c:out value="${article.createdOn}"/></td>
                <td><c:out value="${article.authorDto.fullName}"/></td>
                <td>
                    <c:forEach var="category" items="${article.categoryDtos}">
                        ${category.name},
                    </c:forEach>
                </td>
                <td>
                    <a href="/article/edit?id=<c:out value="${article.id}"/>">Edit</a>,
                    <a href="/article/confirm-delete?id=<c:out value="${article.id}"/>">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
