<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Delete draft</title>
    <c:import url="../fragments/head.jsp"/>
</head>
<body>

    <c:import url="../fragments/header.jsp"/>

    <a href="/draft/delete?id=${id}">Confirm</a>
    <br/>
    <a href="/draft/list">Return</a>
</body>
</html>
