<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Edit category</title>
    <c:import url="../fragments/head.jsp"/>
</head>
<body>

    <c:import url="../fragments/header.jsp"/>

    <form:form modelAttribute="categoryDto">
        <fieldset>
            <legend>Category data:</legend>
            <form:label path="name">Name: </form:label>
            <form:input path="name"/>
            <form:errors path="name" cssClass="text-danger"/>
            <br/>
            <form:label path="description">Description: </form:label>
            <form:input path="description"/>
            <form:errors path="description" cssClass="text-danger"/>
            <br/>
            <input type="submit" value="Edit"/>
        </fieldset>
    </form:form>

</body>
</html>
