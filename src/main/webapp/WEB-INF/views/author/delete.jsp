<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Delete author</title>
</head>
<body>

    <c:import url="../fragments/header.jsp"/>

    <a href="/author/delete?id=${id}">Confirm</a>
    <br/>
    <a href="/author/list">Return</a>
</body>
</html>
