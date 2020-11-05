<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Add new author</title>
</head>
<body>

    <c:import url="../fragments/header.jsp"/>

    <form:form modelAttribute="authorDto">
        <fieldset>
            <legend>Author data:</legend>
            <form:label path="firstName">First name: </form:label>
            <form:input path="firstName"/>
            <br/>
            <form:label path="lastName">Last name: </form:label>
            <form:input path="lastName"/>
            <br/>
            <input type="submit" value="Submit"/>
        </fieldset>
    </form:form>

</body>
</html>
