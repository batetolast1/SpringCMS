<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Spring CMS - home page</title>
</head>
<body>

    <c:import url="fragments/header.jsp"/>

    <table>
        <tr>
            <th>Id</th>
            <th>Title</th>
            <th>Content</th>
            <th>Created on</th>
        </tr>
        <c:forEach var="article" items="${articleDtos}" varStatus="index">
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
