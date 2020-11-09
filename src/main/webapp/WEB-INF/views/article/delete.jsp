<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Delete article</title>
</head>
<body>

    <c:import url="../fragments/header.jsp"/>

    <a href="/article/delete?id=${id}">Confirm</a>
    <br/>
    <a href="/article/list">Return</a>
</body>
</html>
