<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Drafts list</title>
    <c:import url="../fragments/head.jsp"/>
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
        <c:forEach var="draft" items="${draftDtos}" varStatus="index">
            <tr>
                <td><c:out value="${index.index + 1}"/></td>
                <td><c:out value="${draft.title}"/></td>
                <td><c:out value="${draft.content}"/></td>
                <td><c:out value="${draft.createdOn}"/></td>
                <td><c:out value="${draft.authorDto.fullName}"/></td>
                <td>
                    <c:forEach var="category" items="${draft.categoryDtos}">
                        ${category.name},
                    </c:forEach>
                </td>
                <td>
                    <a href="/draft/edit?id=<c:out value="${draft.id}"/>">Edit</a>, <a
                        href="/draft/confirm-delete?id=<c:out value="${draft.id}"/>">Delete</a>, <a
                        href="/draft/publish?id=<c:out value="${draft.id}"/>">Publish</a>
                </td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
