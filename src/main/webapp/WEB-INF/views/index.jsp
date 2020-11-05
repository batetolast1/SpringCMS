<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Spring CMS - home page</title>
</head>
<body>
    <table>
        <tr>
            <th>Id</th>
            <th>Title</th>
            <th>Content</th>
            <th>Created on</th>
        </tr>
        <c:forEach var="article" items="${lastArticles}" varStatus="index">
            <tr>
                <td>${index.index + 1}</td>
                <td>${article.title}</td>
                <td>${article.content}</td>
                <td>${article.createdOn}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
