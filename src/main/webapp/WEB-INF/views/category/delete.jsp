<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Delete category</title>
    <c:import url="../fragments/head.jsp"/>
</head>
<body>

    <c:import url="../fragments/header.jsp"/>

    <a href="/category/delete?id=${id}">Confirm</a>
    <br/>
    <a href="/category/list">Return</a>
</body>
</html>
