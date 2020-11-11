<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Add new author</title>
    <c:import url="../fragments/head.jsp"/>
</head>
<body>

    <c:import url="../fragments/header.jsp"/>

    <form:form modelAttribute="authorDto">
        <fieldset>
            <legend>Author data:</legend>
            <form:label path="firstName">First name: </form:label>
            <form:input path="firstName"/>
            <form:errors path="firstName" cssClass="text-danger"/>
            <br/>
            <form:label path="lastName">Last name: </form:label>
            <form:input path="lastName"/>
            <form:errors path="lastName" cssClass="text-danger"/>
            <br/>
            <input type="submit" value="Add"/>
        </fieldset>
    </form:form>

</body>
</html>
